<%@ page import="ru.itis.dto.CarDto" %><%--
  Created by IntelliJ IDEA.
  User: Programmer
  Date: 27.11.2020
  Time: 0:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    CarDto car = (CarDto)request.getAttribute("currentCar");
%>
<html>
<head>
    <meta charset="UTF-8">
    <title><%= car.getTitle() %></title>
    <link rel="stylesheet" href="../css/main.css">
    <link rel="stylesheet" href="../css/car.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
<jsp:include page="header.jsp"/>
<div id="carContent">
    <img src="http://localhost:8080/files?id=<%= car.getId() %>" alt="" width="700">
<%--    <img src="../images/carPhoto/<%= car.getId() %>.jpg" alt="" width="700">--%>
    <img src="../images/marks/<%= car.getMarkLogoId() %>.png" height="100" alt="">
    <h1><%= car.getTitle() %></h1>
    <p><%= car.getModel() %></p>
    <p><%= car.getCountry() %></p>
    <p><%= car.getDescription() %></p>
    <p style="margin-bottom: 50px">Price: <%= car.getPrice() %> $</p>
</div>
</body>
</html>
