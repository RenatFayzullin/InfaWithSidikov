
<header>
    <a>
    </a>
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