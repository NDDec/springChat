package com.smart.controller;

import com.smart.service.CookieAndSessionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class NormalUserController {
    @Resource
    private CookieAndSessionService cookieAndSessionService;
    @RequestMapping(value="NormalUser.html")
    public String normal(HttpServletRequest request, HttpServletResponse response){
        if(cookieAndSessionService.judge(request,response)) {
            return "mainNormalUser";
        }
        else{
            return "login";
        }
    }
}
