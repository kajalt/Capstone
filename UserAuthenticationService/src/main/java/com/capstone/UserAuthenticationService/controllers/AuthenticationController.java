package com.capstone.UserAuthenticationService.controllers;

import com.capstone.UserAuthenticationService.dtos.*;
import com.capstone.UserAuthenticationService.models.User;
import com.capstone.UserAuthenticationService.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignupRequestDto signupRequestDto) {
        try {
            User user = authService.signUp(signupRequestDto.getEmail(), signupRequestDto.getPassword());
            return new ResponseEntity<>(getUserDto(user),HttpStatus.OK);
        }catch(Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        try {
            Pair<User, MultiValueMap<String,String>> bodyWithHeaders = authService.login(loginRequestDto.getEmail(),loginRequestDto.getPassword());
            return new ResponseEntity<>(getUserDto(bodyWithHeaders.a),bodyWithHeaders.b, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<UserDto> logout(@RequestBody LogoutRequestDto logoutRequestDto) {
        return null;
    }

    private UserDto getUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setRoleSet(user.getRoleSet());
        return userDto;
    }

    @PostMapping("/validate")
    public ResponseEntity<Boolean> validateToken(@RequestBody ValidateTokenRequestDto validateTokenRequestDto) {
        return new ResponseEntity<>(authService.validateToken(validateTokenRequestDto.getToken(),validateTokenRequestDto.getUserId()),HttpStatus.OK);
    }
}