<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Calculate</title>
</head>
<body>
<h2>Введіть три числа і виберіть операцію</h2>
    <form action="calculate" method="post">
        Число 1: <input type="number" name="num1" required><br>
        Число 2: <input type="number" name="num2" required><br>
        Число 3: <input type="number" name="num3" required><br><br>

        <input type="radio" name="operation" value="min" required> Мінімум<br>
        <input type="radio" name="operation" value="max"> Максимум<br>
        <input type="radio" name="operation" value="avg"> Середнє арифметичне<br><br>

        <input type="submit" value="Обчислити">
    </form>

    <a href="index.jsp">Назад</a>
</body>
</html>
