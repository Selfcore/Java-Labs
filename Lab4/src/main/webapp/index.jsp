<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lab4</title>
</head>
<body>
    <a href="hello-servlet">Hello Martin Fowler</a>

    <h2>Таблиця множення</h2>
    <form action="table-servlet" method="get">
        <label>Введіть число:</label>
        <input type="number" name="number" required>
        <input type="submit" value="Показати таблицю множення">
    </form>

    <a href="laptops" style="font-size: 14pt;">Laptops page</a>
    <br>
    <a href="calculate.jsp" style="font-size: 14pt;">Calculate numbers</a>
    <br>
    <a href="guess.jsp" style="font-size: 14pt;">Guess a numbers</a>
</body>
</html>