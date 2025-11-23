<%@ page import="Models.Client" %>
<%@ page import="Models.Order" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Client Details</title></head>
<body>

<h1>Client Details</h1>

<%
    Client client = (Client) request.getAttribute("client");
    List<Order> orders = (List<Order>) request.getAttribute("lastOrders");

    if (client == null) {
%>
<p style="color:red;">Client not found</p>
<% } else { %>
<p><strong>Name:</strong> <%= client.getName() %></p>
<p><strong>Email:</strong> <%= client.getEmail() != null ? client.getEmail() : "(empty)" %></p>
<p><strong>Phone:</strong> <%= client.getPhoneNumber() %></p>

<h2>Last 10 Orders</h2>
<table border="1" cellpadding="8" width="100%">
    <tr>
        <th>Order ID</th>
        <th>Worker</th>
        <th>Date</th>
    </tr>
    <% if (orders.isEmpty()) { %>
    <tr>
        <td colspan="3" style="text-align:center; color:gray;">No orders</td>
    </tr>
    <% } else {
        for (Order o : orders) { %>
    <tr>
        <td><a href="order?id=<%= o.getId() %>"><%= o.getId() %></a></td>
        <td><%= o.getWorker() != null ? o.getWorker().getName() : "(empty)" %></td>
        <td><%= o.getDate() %></td>
    </tr>
    <%  } } %>
</table>
<% } %>

<a href="clients">Back to Clients</a>

</body>
</html>