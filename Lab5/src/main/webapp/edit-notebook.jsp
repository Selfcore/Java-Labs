<%@ page import="Models.Notebook" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    Notebook notebook = (Notebook) request.getAttribute("notebook");
    if (notebook == null) {
        response.sendRedirect("notebooks");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Редагувати блокнот</title>
</head>
<body>
<h2>Редагування блокноту: <%= notebook.getName() %></h2>
<form action="notebooks" method="post">
    <input type="hidden" name="action" value="updateSubmit">
    <input type="hidden" name="id" value="<%= notebook.getId() %>">

    <label>Фірма:</label><br>
    <input type="text" name="manufacturer" value="<%= notebook.getManufacturer() %>" required><br><br>

    <label>Назва:</label><br>
    <input type="text" name="name" value="<%= notebook.getName() %>" required><br><br>

    <label>Кількість сторінок:</label><br>
    <input type="number" name="pages" value="<%= notebook.getPages() %>" min="1" required><br><br>

    <label>Обкладинка:</label><br>
    <input type="text" name="coverType" value="<%= notebook.getCoverType() %>" required><br><br>

    <label>Країна:</label><br>
    <input type="text" name="country" value="<%= notebook.getCountry() %>" required><br><br>

    <label>Тираж:</label><br>
    <input type="number" name="circulation" value="<%= notebook.getCirculation() %>" min="1" required><br><br>

    <label>Сторінка:</label><br>
    <input type="text" name="pageStyle" value="<%= notebook.getPageStyle() %>" required><br><br>

    <button type="submit">Оновити</button>
    <a href="notebooks">Скасувати</a>
</form>
</body>
</html>
