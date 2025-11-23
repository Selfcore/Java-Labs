<%@ page import="Models.Order" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head><title>Orders</title></head>
<body>

<h1>Orders List</h1>

<a href="add-order">Add order</a>

<form method="get" action="orders" style="margin-bottom:20px;">
    <label>From:
        <input type="date" name="from" value="<%= request.getParameter("from") != null ? request.getParameter("from") : "" %>">
    </label>

    <label>To:
        <input type="date" name="to" value="<%= request.getParameter("to") != null ? request.getParameter("to") : "" %>">
    </label>

    <button type="submit">Фільтрувати</button>
</form>



<table border="1" cellpadding="8" width="100%">
    <tr>
        <th>ID</th>
        <th>Client</th>
        <th>Date</th>
        <th>Details</th>
    </tr>

    <%
        List<Order> orders = (List<Order>) request.getAttribute("orders");
        if (orders == null || orders.isEmpty()) {
    %>
    <tr>
        <td colspan="5" style="text-align:center; color:gray;">Немає записів</td>
    </tr>
    <%
    } else {
        for (Order o : orders) {
    %>
    <tr>
        <td><%= o.getId() %></td>
        <td><%= o.getClient() != null ? o.getClient().getName() : "(empty)" %></td>
        <td><%= o.getDate() %></td>
        <td><a href="orders?id=<%= o.getId() %>">View</a></td>
    </tr>
    <%
            }
        }
    %>
</table>

<a href="index.jsp">Back to main</a>

</body>
</html>
