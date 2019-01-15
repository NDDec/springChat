package com.smart.controller;

import com.smart.domain.UserInfo;
import com.smart.service.CookieAndSessionService;
import com.smart.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class SuperUserController {
    @Resource
    private UserService userService;
    @Resource
    private CookieAndSessionService cookieAndSessionService;
    @RequestMapping(value="/manageSuperUser.html")
    public ModelAndView getUserInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView mav = new ModelAndView("manageSuperUser");
        //每页的记录数
        final Integer numEachPage = 10;
        //所有用户信息
        List<UserInfo> users = userService.getUserInfo();
        //总页数
        Integer numOfPages = userService.numOfPages(numEachPage);
        //ip
        String ip = request.getRemoteAddr();
        //域名地址
        String host = request.getRemoteHost();
        //当前所在页,默认为第一页
        int pageNow = 1;
        String spageNow = request.getParameter("pageNow");
        pageNow = userService.getPageNow(spageNow,response,numOfPages,pageNow);
        mav.addObject("numOfPages",numOfPages);
        mav.addObject("userInfo",users);
        mav.addObject("numEachPage",numEachPage);
        mav.addObject("pageNow",pageNow);
        mav.addObject("ip",ip);
        mav.addObject("host",host);
        return mav;
    }
    @RequestMapping(value="updateUser.html")
    public ModelAndView updateUser(HttpServletRequest request,HttpServletResponse response){
        ModelAndView mav = new ModelAndView("updateUser");
        String id = request.getParameter("uid");
        UserInfo userInfo = userService.getUserById(id);
        mav.addObject("userId",userInfo.getUserId());
        mav.addObject("userName",userInfo.getUserName());
        mav.addObject("grade",userInfo.getGrade());
        mav.addObject("password",userInfo.getPassword());
        mav.addObject("email",userInfo.getEmail());
        return mav;
    }
    @RequestMapping(value="updateInfo.html")
    public ModelAndView updateInfo(HttpServletRequest request,HttpServletResponse response) throws IOException {
        if(userService.updateInfo(request.getParameter("id"),
                request.getParameter("newgrade"),
                request.getParameter("newpasswd"),
                request.getParameter("newemail"))){
            return new ModelAndView("operateOk");
        }
        else{
            return new ModelAndView("operateNo");
        }
    }
    @RequestMapping(value="errorInput.html")
    public ModelAndView errorInput(){
        return new ModelAndView("errorInput");
    }
    @RequestMapping(value="mainSuperUser.html")
    public ModelAndView mainSuperUser(HttpServletRequest request,HttpServletResponse response){
        if(cookieAndSessionService.judge(request,response)) {
            return new ModelAndView("mainSuperUser");
        }
        else{
            return new ModelAndView("login");
        }
    }
    @RequestMapping(value="delUser.html")
    public ModelAndView delUser(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("uid");
        if(userService.delUser(id)){
            return new ModelAndView("operateOk");
        }
        else{
            return new ModelAndView("operateNo");
        }
    }
}
