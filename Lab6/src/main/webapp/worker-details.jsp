<%@ page import="Models.Worker" %>
<%@ page import="Models.Order" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Worker Details</title></head>
<body>

<h1>Worker Details</h1>

<%
    Worker worker = (Worker) request.getAttribute("worker");
    List<Order> orders = (List<Order>) request.getAttribute("lastOrders");

    if (worker == null) {
%>
<p style="color:red;">Worker not found</p>
<% } else { %>
<p><strong>Name:</strong> <%= worker.getName() %></p>
<p><strong>Email:</strong> <%= worker.getEmail() != null ? worker.getEmail() : "(empty)" %></p>
<p><strong>Phone:</strong> <%= worker.getPhoneNumber() %></p>

<h2>Last 10 proceeded orders</h2>
<table border="1" cellpadding="8" width="100%">
    <tr>
        <th>Order ID</th>
        <th>Client</th>
        <th>Date</th>
    </tr>
    <% if (orders.isEmpty()) { %>
    <tr>
        <td colspan="3" style="text-align:center; color:gray;">No orders</td>
    </tr>
    <% } else {
        for (Order o : orders) { %>
    <tr>
        <td><a href="orders?id=<%= o.getId() %>"><%= o.getId() %></a></td>
        <td><%= o.getClient() != null ? o.getClient().getName() : "(empty)" %></td>
        <td><%= o.getDate() %></td>
    </tr>
    <%  } } %>
</table>
<% } %>

<a href="workers">Back to Workers</a>

</body>
</html>
