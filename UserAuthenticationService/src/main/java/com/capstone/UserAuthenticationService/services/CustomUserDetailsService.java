package com.capstone.UserAuthenticationService.services;

import com.capstone.UserAuthenticationService.models.CustomUserDetails;
import com.capstone.UserAuthenticationService.models.User;
import com.capstone.UserAuthenticationService.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(username);

        if(optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("NOT FOUND");
        }

        return new CustomUserDetails(optionalUser.get());

    }
}