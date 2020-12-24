<%@ page import="ru.itis.dto.UserDto" %><%--
  Created by IntelliJ IDEA.
  User: Programmer
  Date: 25.11.2020
  Time: 5:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UserDto currentUser = (UserDto)request.getSession().getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Page</title>
    <link rel="stylesheet" href="../css/main.css">
    <link rel="stylesheet" href="../css/user.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
<jsp:include page="header.jsp"/>

<div id="userProfile">
    <h1><%= currentUser.getFirstName() + " " + currentUser.getLastName() %></h1>
    <h2><%= currentUser.getEmail() %></h2>
</div>
</body>
</html>