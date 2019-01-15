<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>管理员界面</title>
</head>
<body>
<h1>欢迎管理员登录！</h1>
<center>
    <h2>管理用户</h2>
    <table border = 1>
        <tr bgcolor = silver><th>id</th><th>name</th><th>grade</th><th>password</th><th>email</th><th>ip</th><th>create_time</th><th>last_visit_time</th><th>删除用户</th><th>修改用户</th></tr>

        <c:forEach items = "${requestScope.userInfo}" var="u"
                   begin="${(requestScope.pageNow-1)*requestScope.numEachPage}"
                   end="${requestScope.pageNow*requestScope.numEachPage-1}">
            <tr>
                <td>${u.userId}</td>
                <td>${u.userName}</td>
                <td>${u.grade}</td>
                <td>${u.password}</td>
                <td>${u.email}</td>
                <td>${u.lastIp}</td>
                <td>${u.createTime}</td>
                <td>${u.lastVisitTime}</td>
                <td><a href="delUser.html?uid=${u.userId}">删除用户</a></td>
                <td><a href="updateUser.html?uid=${u.userId}">修改用户</a></td>
            </tr>
        </c:forEach>
    </table>

    <c:if test="${requestScope.pageNow>1}">
        <a href="manageSuperUser.html?pageNow=${requestScope.pageNow-1}">上一页</a>
    </c:if>
    <c:forEach var="page" begin="${requestScope.pageNow}" end="${requestScope.pageNow+4}">
        <c:if test="${page<=requestScope.numOfPages}">
            <a href="manageSuperUser.html?pageNow=${page}">${page}</a>
        </c:if>
    </c:forEach>
    <c:if test="${requestScope.pageNow<requestScope.numOfPages}">
        <a href="manageSuperUser.html?pageNow=${requestScope.pageNow+1}">下一页</a>
    </c:if>

    <form action="manageSuperUser.html">
        <input type = text name="pageNow">
        <input type = submit value="go">
    </form>

    <hr>
    您的ip=${requestScope.ip}&nbsp;&nbsp;&nbsp;
    您的机器名=${requestScope.host}
    <br>
    <a href="login.html">重新登录</a>

</center>
</body>
</html>