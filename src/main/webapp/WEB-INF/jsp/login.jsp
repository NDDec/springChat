<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>登录界面</title>
</head>
<body>
<center>
    <form action="logincheck.html" method="post">
        <h1>登录界面</h1>
        <br>
    用户名:
    <input type="text" name="userName">
    <br>
    密 码：
    <input type="password" name="password">
    <br>
        <input type="checkbox" name="keep" value="2">两周内不再登录<br><br>
    <input type="submit" value="登录" />
    </form>
</center>
</body>
</html>