<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add car</title>
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/exit.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<div id="exitContent">
    <form action="/admin" method="post" enctype="multipart/form-data">
        <input type="text" name="titleCar" placeholder="Enter brand car">
        <input type="text" name="modelCar" placeholder="Enter model car">
        <input type="text" name="desc" placeholder="Enter description">
        <input type="text" name="priceCar" placeholder="Enter price Car">
        <input type="text" name="countryCar" placeholder="Enter country Car">
        <input type="file" name="file">
        <input type="submit" value="Add car">
    </form>
</div>
</body>
</html>