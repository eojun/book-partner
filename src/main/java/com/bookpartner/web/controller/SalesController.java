package com.bookpartner.web.controller;

import com.bookpartner.common.UserDetailsImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/sales")
public class SalesController extends BaseController{

    @GetMapping("/salesListDay")
    public String salesListDay(Model model){

        UserDetailsImpl user =  getSecurityContextUser();

        Map requestParam = getParameterMap();
        model.addAttribute("requestParam", requestParam);

        return "sales/salesListDay";
    }

}
