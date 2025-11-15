<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Guess a number</title>
</head>
<body>
    <h2>Гра "Вгадай число"</h2>

    <form action="guess" method="post">
        <label>Загадай число від 0 до 100 і введи його сюди:</label><br>
        <input type="number" name="secret" min="0" max="100" required><br><br>
        <input type="submit" value="Почати гру">
    </form>

    <a href="index.jsp">Назад</a>
</body>
</html>
