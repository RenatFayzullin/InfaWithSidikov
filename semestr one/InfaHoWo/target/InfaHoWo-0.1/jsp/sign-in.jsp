<%--
  Created by IntelliJ IDEA.
  User: Programmer
  Date: 25.11.2020
  Time: 5:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Exit</title>
    <link rel="stylesheet" href="../css/main.css">
    <link rel="stylesheet" href="../css/exit.css">
</head>
<body>
<jsp:include page="header.jsp"/>

<div id="exitContent">
    <form action="/sign-in" method="post">
        <input type="text" name="email" placeholder="Enter email">
        <input type="password" name="password" placeholder="Enter password">
        <input type="submit" value="Sign in">
    </form>
</div>
</body>
</html>
