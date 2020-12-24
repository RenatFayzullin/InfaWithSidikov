<%@ page import="ru.itis.dto.CarDto" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Programmer
  Date: 25.11.2020
  Time: 5:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<CarDto> cars = (List<CarDto>) request.getAttribute("cars");
%>
<html>
<head>
    <title>Cars</title>
    <link rel="stylesheet" href="../css/main.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>

<jsp:include page="header.jsp"/>

<div id="subheader">
    <div id="siteDesc">
        <h1>Лучший сайт для поиска автомобилей</h1>
        <p>Данный сайт предназначен для просмотра автомобилей. Вы можете найти для себя подходящий транспорт и узнать
            информацию о нем</p>
    </div>
    <img src="../images/subheader.png" alt="" width="300">

</div>
<div id="search">
    <input name="search" placeholder="Find car by brand">
</div>
<div id="content">
    <ul>
        <% for (CarDto car : cars) {%>
        <li data-id="<%= car.getId() %>">
            <div class="car" style="background: url(http://localhost:8080/files?id=<%= car.getId() %>)">
                <div class="modelHight">
                    <h2><%= car.getModel() %>
                    </h2>
                </div>
            </div>
            <div class="modelBottom">
                <img src="../images/marks/<%= car.getMarkLogoId() %>.png" height="20" alt="">
                <h4><%= car.getTitle() %>
                </h4>
            </div>
            <form action="/car" method="post" style="display: none;">
                <input type="hidden" name="carId" value="<%= car.getId() %>">
            </form>
        </li>
        <% } %>
    </ul>

</div>
<footer></footer>

<script>
    $(document).ready(function() {
        $("#content ul").on('click', 'li',function() {
            $(this).children("form").submit();
        });

        $("#search input").on('input', function() {
            let str = this.value.trim().toLowerCase();
            let currentStr = str.length !== 0 ? str[0].toUpperCase() + str.slice(1) : "";

            $.ajax({
                method: "POST",
                data: {curStr: currentStr},
                success: function(data) {
                    let jsonData = JSON.parse(data);
                    if (jsonData.length !== 0) {
                        $("#content ul li").css('display','none');
                        jsonData.forEach(function (car) {
                            $("#content ul li[data-id=" + car.id + "]").fadeIn(300);
                        });
                    }
                    else if (currentStr.length === 0) {
                        $("#content ul li").fadeIn(300);
                    } else {
                        $("#content ul li").fadeOut(300);
                    }
                }
            });
        });
    });
</script>
</body>
</html>
