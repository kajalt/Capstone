package com.capstone.UserAuthenticationService.controllers;

import com.capstone.UserAuthenticationService.dtos.UserDto;
import com.capstone.UserAuthenticationService.models.User;
import com.capstone.UserAuthenticationService.services.UserService;
import jakarta.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{uid}")
    public UserDto getUserById(@PathVariable Long uid) {
        User user = userService.getUserById(uid);
        return getUserDto(user);
    }

    private UserDto getUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}