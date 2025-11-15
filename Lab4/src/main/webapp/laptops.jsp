<%@ page import="Models.Laptop" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Laptops</title>
</head>
<body>
<h1>Список ноутбуків</h1>
<hr>

<%
    List<Laptop> laptops = (List<Laptop>) request.getAttribute("laptops");
    if (laptops != null) {
        for (Laptop laptop : laptops) {
%>
<div style="border:1px solid #ccc; padding:10px; margin-bottom:10px;">
    <h2><%= laptop.getName() %></h2>
    <img src="<%= laptop.getImage() %>" alt="<%= laptop.getName() %>" width="200">
    <p><%= laptop.getDescription() %></p>

    <form action="laptops" method="post" style="margin-top:10px;">
        <input type="hidden" name="action" value="remove">
        <input type="hidden" name="id" value="<%= laptop.getId() %>">
        <button type="submit" style="background-color:#fab32a;color:white;width: 120px;height: 40px;border-radius: 10px;
            font-size: 14pt; font-weight: bold">Remove</button>
    </form>
</div>
<%
    }
} else {
%>
<p style="color:red;">Дані про ноутбуки не знайдено.</p>
<%
    }
%>

<a href="index.jsp">Назад</a>
</body>
</html>
