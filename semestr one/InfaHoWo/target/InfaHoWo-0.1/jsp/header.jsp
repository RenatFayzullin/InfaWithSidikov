<%@ page import="ru.itis.dto.UserDto" %><%--
  Created by IntelliJ IDEA.
  User: Programmer
  Date: 25.11.2020
  Time: 6:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UserDto currentUser = (UserDto) request.getSession().getAttribute("user");
%>
<header>
    <a href="/"><img src="../images/logo.jpg" alt="" height="50"></a>
    <ul>
        <li>Element1</li>
        <li><a href="/admin">Добавить</a> </li>
        <% if (currentUser == null) { %>
        <li><a href="/registration">Регистрация</a></li>
        <li><a href="/sign-in">Вход</a></li>
        <% } else { %>
        <li id="logout">Выход
            <form action="/" method="post" style="display:none;">
                <input type="hidden" name="logout">
            </form>
        </li>
        <li>
            <a href="/profile">Мой профиль</a>
        </li>
        <% } %>
    </ul>
</header>
<script>
    $(document).ready(function () {
        $("#logout").click(function () {
            $(this).children("form").submit();
        });
    });
</script>