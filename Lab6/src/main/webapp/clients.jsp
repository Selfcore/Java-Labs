<%@ page import="Models.Client" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Clients</title></head>
<body>
<h1>Clients List</h1>

<a href="add-client">Add client</a>

<table border="1" cellpadding="8" width="100%">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Email</th>
    </tr>

    <%
        List<Client> clients = (List<Client>) request.getAttribute("clients");
        if (clients == null || clients.isEmpty()) {
    %>
    <tr>
        <td colspan="3" style="text-align:center; color:gray;">Немає записів</td>
    </tr>
    <%
    } else {
        for (Client c : clients) {
    %>
    <tr>
        <td><%= c.getId() %></td>
        <td><%= c.getName() %></td>
        <td><%= c.getEmail() %></td>
    </tr>
    <%
            }
        }
    %>
</table>

<a href="index.jsp">Back to main</a>

</body>
</html>