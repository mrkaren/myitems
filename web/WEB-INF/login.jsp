<%--
  Created by IntelliJ IDEA.
  User: karen
  Date: 26.02.22
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<a href="/home">Back</a>
<form action="/login" method="post">
    <input type="text" name="email"/>
    <input type="password" name="password"/>
    <input type="submit" value="login">
</form>
</body>
</html>
