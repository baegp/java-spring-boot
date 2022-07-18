package com.example.java5.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class DashboardController {

    @GetMapping("/dashboard")
    public String home() {
        System.out.println("go ");
        return "dashboard";
    }
}
