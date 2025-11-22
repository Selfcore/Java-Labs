<%@ page import="Models.Client" %>
<%@ page import="Models.Worker" %>
<%@ page import="Models.Product" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Create Order</title>
</head>
<body>

<h1>Create New Order</h1>

<form action="add-order" method="post">

    <!-- CLIENT -->
    <label for="client_id">Client:</label><br>
    <select name="client_id" id="client_id" required>
        <option value="">-- Select client --</option>
        <%
            for (Client c : (List<Client>) request.getAttribute("clients")) {
        %>
        <option value="<%= c.getId() %>"><%= c.getName() %></option>
        <% } %>
    </select>
    <br><br>

    <!-- WORKER -->
    <label for="worker_id">Worker:</label><br>
    <select name="worker_id" id="worker_id" required>
        <option value="">-- Select worker --</option>
        <%
            for (Worker w : (List<Worker>) request.getAttribute("workers")) {
        %>
        <option value="<%= w.getId() %>"><%= w.getName() %></option>
        <% } %>
    </select>

    <br><br>

    <!-- PRODUCTS -->
    <h3>Select Products</h3>

    <table border="1" cellpadding="8" width="100%">
        <tr>
            <th>Product</th>
            <th>Price</th>
            <th>Quantity</th>
        </tr>

        <%
            List<Product> products = (List<Product>) request.getAttribute("products");
            for (Product p : products) {
        %>
        <tr>
            <td><%= p.getTitle() %></td>
            <td><%= p.getPrice() %></td>
            <td>
                <input type="number"
                       name="product_<%= p.getId() %>"
                       min="0"
                       placeholder="0"
                       style="width: 60px;">
            </td>
        </tr>
        <% } %>
    </table>

    <br>
    <button type="submit">Create Order</button>
</form>

<br>
<a href="orders">Back to Orders</a>

</body>
</html>
