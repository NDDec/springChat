package com.smart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
    @RequestMapping(value="/login.html")
    public ModelAndView getLoginPage(HttpServletResponse response){
        ModelAndView loginPage = new ModelAndView("login");
        return loginPage;
    }
}
