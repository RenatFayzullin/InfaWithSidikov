<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Reg</title>
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/exit.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<div id="exitContent">
    <form action="/registration" method="post">
        <input type="text" name="firstName" placeholder="Enter name">
        <input type="text" name="lastName" placeholder="Enter lastname">
        <input type="text" name="email" placeholder="Enter email">
        <input type="password" name="password" placeholder="Enter password">
        <input type="submit" value="Sign up">
    </form>
</div>
</body>
</html>