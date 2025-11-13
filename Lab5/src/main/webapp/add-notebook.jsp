<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add notebook</title>
</head>
<body>
    <h2>Додати новий блокнот</h2>

    <form action="notebooks" method="post">
        <label>Фірма: <input type="text" name="manufacturer" required></label><br>
        <label>Назва: <input type="text" name="name" required></label><br>
        <label>Сторінки: <input type="number" name="pages" required></label><br>
        <label>Обкладинка: <input type="text" name="coverType" required></label><br>
        <label>Країна: <input type="text" name="country" required></label><br>
        <label>Тираж: <input type="number" name="circulation" required></label><br>
        <label>Сторінка: <input type="text" name="pageStyle" required></label><br><br>
        <input type="submit" value="Додати">
    </form>

    <a href="notebooks">Назад до списку</a>
</body>
</html>
