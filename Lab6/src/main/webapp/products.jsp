<%@ page import="Models.Product" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Products</title>
</head>
<body>

<h1>Products List</h1>

<a href="add-product">Add product</a>

<table border="1" cellpadding="8" width="100%">
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Price</th>
    </tr>

    <%
        List<Product> products = (List<Product>) request.getAttribute("products");
        if (products == null || products.isEmpty()) {
    %>
    <tr>
        <td colspan="3" style="text-align:center; color:gray;">Немає записів</td>
    </tr>
    <%
    } else {
        for (Product p : products) {
    %>
    <tr>
        <td><%= p.getId() %></td>
        <td><%= p.getTitle() %></td>
        <td><%= p.getPrice() %></td>
    </tr>
    <%
            }
        }
    %>
</table>

<a href="index.jsp">Back to main</a>

</body>
</html>
