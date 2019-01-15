package com.smart.dao;

import com.smart.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<UserInfo> getUserInfo(){
        String sqlStr = "select id,grade,user_name,password,email,ip,create_time,last_visit_time from chatUsers";
        final List res = new ArrayList();
        jdbcTemplate.query(sqlStr, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                UserInfo userInfo = new UserInfo();
                userInfo.setUserId(resultSet.getInt("id"));
                userInfo.setGrade(resultSet.getInt("grade"));
                userInfo.setUserName(resultSet.getString("user_name"));
                userInfo.setPassword(resultSet.getString("password"));
                userInfo.setEmail(resultSet.getString("email"));
                userInfo.setLastIp(resultSet.getString("ip"));
                userInfo.setCreateTime(resultSet.getString("create_time"));
                userInfo.setLastVisitTime(resultSet.getString("last_visit_time"));
                res.add(userInfo);
            }
        });
        return res;
    }

    public UserInfo getUserById(String id){
        System.out.println(id);
        String sqlStr = "select id,grade,user_name,password,email,ip,create_time,last_visit_time from chatUsers where id = ?";
        final UserInfo userInfo = new UserInfo();
        jdbcTemplate.query(sqlStr, new Object[]{id}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                userInfo.setUserId(resultSet.getInt("id"));
                userInfo.setGrade(resultSet.getInt("grade"));
                userInfo.setUserName(resultSet.getString("user_name"));
                userInfo.setPassword(resultSet.getString("password"));
                userInfo.setEmail(resultSet.getString("email"));
                userInfo.setLastIp(resultSet.getString("ip"));
                userInfo.setCreateTime(resultSet.getString("create_time"));
                userInfo.setLastVisitTime(resultSet.getString("last_visit_time"));
            }
        } );
        return userInfo;
    }

    public String getUserPasswordByName(String userName) {
        String sqlStr = "select password from chatUsers where user_name = ? limit 1";
        String passwd = jdbcTemplate.queryForObject(sqlStr,new Object[]{userName},java.lang.String.class);
        return passwd;
    }

    public Integer getIdentity(String userName) {
        String sqlStr = "select grade from chatUsers where user_name = ? limit 1";
        Integer grade = jdbcTemplate.queryForObject(sqlStr,new Object[]{userName},java.lang.Integer.class);
        return grade;
    }

    public Integer numOfInfo() {
        String sqlStr = "select count(*) from chatUsers";
        Integer res = jdbcTemplate.queryForObject(sqlStr,Integer.class);
        return res;
    }

    public boolean delUser(String id) {
        String sqlStr = "delete from chatUsers where id = ?";
        int res = jdbcTemplate.update(sqlStr,new Object[] {id});
        if(res > 0){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean updateInfo(String id, String newgrade, String newpasswd, String newemail) {
        String sqlStr = "update chatUsers set grade = ?,password=?,email=? where id = ?";
        int res = jdbcTemplate.update(sqlStr,new Object[]{newgrade,newpasswd,newemail,id});
        if(res > 0){
            return true;
        }
        else{
            return false;
        }
    }
}
