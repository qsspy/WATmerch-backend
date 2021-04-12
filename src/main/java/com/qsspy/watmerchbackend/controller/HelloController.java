package com.qsspy.watmerchbackend.controller;

import com.qsspy.watmerchbackend.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public User helloWorld() {

        return new User();
    }
}
