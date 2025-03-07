package com.capstone.productservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {

    @GetMapping
    public static String welcome(){
        return "Welcome to First Controller";
    }
}
