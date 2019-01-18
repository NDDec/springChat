package com.smart.service;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Service
public class CookieAndSessionService {
    @Resource
    private UserService userService;
    public void setCookie(HttpServletRequest request, HttpServletResponse response, String userName, String password) {
        String keep = request.getParameter("keep");
        if(keep != null){
            Cookie nameCookie = new Cookie("myname",userName);
            Cookie passwdCookie = new Cookie("mypasswd",password);
            nameCookie.setMaxAge(Integer.parseInt(keep)*7*24*3600);
            passwdCookie.setMaxAge(Integer.parseInt(keep)*7*24*3600);
            response.addCookie(nameCookie);
            response.addCookie(passwdCookie);
        }
    }

    public void setSession(HttpServletRequest request, HttpServletResponse response, String userName) {
        HttpSession hs = request.getSession(true);
        hs.setMaxInactiveInterval(6);
        hs.setAttribute("name",userName);
    }

    public boolean judge(HttpServletRequest request,HttpServletResponse response){
        HttpSession hs = request.getSession(true);
        String name = (String)hs.getAttribute("name");
        //判断session中是否存有信息
        if(name != null){
            return true;
        }
        else {
            try{
                //判断cookie
                String name_cookie = null;
                String passwd_cookie = null;
                Cookie [] allCookies = request.getCookies();
                if(allCookies != null){
                    for(Cookie tmp : allCookies){
                        if(tmp.getName().equals("myname")){
                            name_cookie = tmp.getValue();
                        }
                        if(tmp.getName().equals("mypasswd")){
                            passwd_cookie = tmp.getValue();
                        }
                    }
                    if(userService.checkUser(name_cookie,passwd_cookie)){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }
}
