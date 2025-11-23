<%@ page import="Models.Order" %>
<%@ page import="Models.OrderLine" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head><title>Order Details</title></head>
<body>

<h1>Order Details</h1>

<%
    Order order = (Order) request.getAttribute("order");
    if (order == null) {
%>
<p style="color:red;">Замовлення не знайдено</p>
<%
} else {
%>
<p><strong>Order ID:</strong> <%= order.getId() %></p>
<p><strong>Client:</strong> <%= order.getClient() != null ? order.getClient().getName() : "(empty)" %></p>| <a href="clients?id=<%= order.getClient().getId() %>">View</a>
<p><strong>Worker:</strong> <%= order.getWorker() != null ? order.getWorker().getName() : "(empty)" %></p> | <a href="workers?id=<%= order.getWorker().getId() %>">View</a>
<p><strong>Date:</strong> <%= order.getDate() %></p>

<h2>Order Lines</h2>
<table border="1" cellpadding="8" width="100%">
    <tr>
        <th>Product</th>
        <th>Quantity</th>
    </tr>
    <%
        if (order.getOrderLines().isEmpty()) {
    %>
    <tr>
        <td colspan="2" style="text-align:center; color:gray;">Немає записів</td>
    </tr>
    <%
    } else {
        for (OrderLine ol : order.getOrderLines()) {
    %>
    <tr>
        <td><%= ol.getProduct().getTitle() %></td>
        <td><%= ol.getQuantity() %></td>
    </tr>
    <%
            }
        }
    %>
</table>
<%
    }
%>

<a href="orders">Back to Orders</a>

</body>
</html>
