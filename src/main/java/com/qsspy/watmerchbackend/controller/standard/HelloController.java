package com.qsspy.watmerchbackend.controller.standard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/")
    public String helloWorld() {

        return "redirect:/crm/users";
    }
}
