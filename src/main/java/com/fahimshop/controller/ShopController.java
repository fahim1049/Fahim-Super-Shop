package com.fahimshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShopController {
    @GetMapping(path = "")
    public String front() {

        return "home";
    }
    @GetMapping(path = "/register")
    public String register() {

        return "register";
    }

    @GetMapping(path =  "/login")
    public String login() {

        return "login";
    }
}