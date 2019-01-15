package com.smart.service;

import com.smart.dao.UserDao;
import com.smart.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Service
public class UserService {
    @Resource
    private UserDao userDao;
    @Autowired
    public void  setUserDao(UserDao userDao){
        this.userDao = userDao;
    }

    //验证用户是否合法
    public boolean checkUser(String userName, String password) {
        String passwd = userDao.getUserPasswordByName(userName);
        if(passwd.equals(password)){
            return true;
        }
        else{
            return false;
        }
    }
    //判断用户身份：管理员-普通用户
    public Integer getIdentity(String userName) {
        return userDao.getIdentity(userName);
    }

    //获取所有用户信息
    public List<UserInfo> getUserInfo() {
        return userDao.getUserInfo();
    }

    //获取总页数
    public Integer numOfPages(Integer numEachPage){
        //获取总记录数
        Integer numOfInfo = userDao.numOfInfo();
        //总页数
        Integer numOfPages = 0;
        if(numOfInfo%numEachPage == 0){
            numOfPages = numOfInfo/numEachPage;
        }
        else{
            numOfPages = numOfInfo/numEachPage+1;
        }
        return numOfPages;
    }

    public int getPageNow(String spageNow, HttpServletResponse response, Integer numOfPages, int pageNow) throws IOException {
        if(spageNow != null){
            Integer pageTmp = 0;
            //输入不是数字
            try{
                pageTmp = Integer.valueOf(spageNow);
            }
            catch(Exception e){
                response.sendRedirect("errorInput.html");
            }
            if(pageTmp <= 0){
                response.sendRedirect("errorInput.html");
            }
            if(pageTmp > numOfPages){
                pageNow = numOfPages;
            }
            else{
                pageNow = (int)pageTmp;
            }
        }
        return pageNow;
    }

    public boolean delUser(String id) {
        return userDao.delUser(id);
    }

    public UserInfo getUserById(String id) {
        return userDao.getUserById(id);
    }

    public boolean updateInfo(String id, String newgrade, String newpasswd, String newemail) {
        return userDao.updateInfo(id, newgrade, newpasswd, newemail);
    }
}
