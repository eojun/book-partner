package com.bookpartner.web.controller;

import com.bookpartner.service.AdminPartnerService;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminPartnerController {

    @GetMapping("/")
    public String index(){
        return "/home/index";
    }

    @GetMapping("/adminPartner/login")
    public String login(){
        return "/adminPartner/loginForm";
    }

}
