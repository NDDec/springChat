<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>修改界面</title>
</head>
<body>
<h1>欢迎管理员登录！</h1>
<center>
    <h2>修改用户</h2>
    <form action="updateInfo.html">
        <table border="1">
            <tr><td>id</td><td><input readonly name = id type = text value = ${requestScope.userId}></td></tr>
            <tr><td>name</td><td><input readonly name = name type = text value = ${requestScope.userName}></td></tr>
            <tr><td>grade</td><td><input name = newgrade type = text value = ${requestScope.grade}></td></tr>
            <tr><td>password</td><td><input name = newpasswd type = text value = ${requestScope.password}></td></tr>
            <tr><td>email</td><td><input name = newemail type = text value = ${requestScope.email}></td></tr>
            <tr><td colspan="2"><input type = submit value = "修改用户"></td></tr>
        </table>
    </form>
</center>
</body>
</html>