package com.capstone.UserAuthenticationService.services;

import com.capstone.UserAuthenticationService.models.User;
import com.capstone.UserAuthenticationService.repositories.SessionRepository;
import com.capstone.UserAuthenticationService.repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private SessionRepository sessionRepository;

    public User signUp(String email, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            return null;
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save(user);
        return user;
    }

    public Pair<User, MultiValueMap<String,String>> login(String email, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if(optionalUser.isEmpty()) {
            return null;
        }

        if(!bCryptPasswordEncoder.matches(password,
                optionalUser.get().getPassword())) {
            return null;
        }


        Map<String,Object> claims = new HashMap<String,Object>();
        claims.put("email",optionalUser.get().getEmail());
        claims.put("roles",optionalUser.get().getRoleSet());
        long nowInMillis = System.currentTimeMillis();
        claims.put("iat",new Date(nowInMillis));
        claims.put("exp",new Date(nowInMillis+1000000));


        MacAlgorithm algorithm = Jwts.SIG.HS256;
        SecretKey secretKey = algorithm.key().build();

        String token = Jwts.builder().claims(claims).signWith(secretKey).compact();

        //Storing Session for validation purpose
        Session session = new Session();
        session.setUser(optionalUser.get());
        session.setSessionState(SessionState.ACTIVE);
        session.setToken(token);
        sessionRepository.save(session);

        MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
        headers.add(HttpHeaders.SET_COOKIE,token);

        return new Pair<User,MultiValueMap<String,String>>(optionalUser.get(),headers);
    }
}
