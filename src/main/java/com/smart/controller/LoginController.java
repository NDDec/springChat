package com.smart.controller;

import com.smart.service.CookieAndSessionService;
import com.smart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
    @Resource
    private UserService userService;
    @Resource
    private CookieAndSessionService cookieAndSessionService;


    @RequestMapping(value="/login.html")
    public ModelAndView getLoginPage(HttpServletRequest request,HttpServletResponse response){
        ModelAndView loginPage = new ModelAndView("login");
        if(request.getParameter("error").equals("uptime")) {
            loginPage.addObject("error","uptime");
        }
        return loginPage;
    }

    @RequestMapping(value="/logincheck.html")
    @ResponseBody
    public void loginCheck(HttpServletRequest request, HttpServletResponse response){
        try {
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            //判断是否输入为空
            if (userName.length() == 0 || password.length() == 0) {
                response.sendRedirect("login.html");
            }
            if (userService.checkUser(userName, password)) {
                //将信息存入本地cookie
                cookieAndSessionService.setCookie(request, response, userName, password);
                cookieAndSessionService.setSession(request,response,userName);
                if (userService.getIdentity(userName) == 0) {
                    response.sendRedirect("NormalUser.html");
                } else if (userService.getIdentity(userName) == 1) {
                    response.sendRedirect("mainSuperUser.html");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }
}
