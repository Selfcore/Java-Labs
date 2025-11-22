<%@ page import="Models.Worker" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Workers</title></head>
<body>
<h1>Workers List</h1>

<a href="add-worker">Add worker</a>

<table border="1" cellpadding="8" width="100%">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Email</th>
    </tr>

    <%
        List<Worker> workers = (List<Worker>) request.getAttribute("workers");
        if (workers == null || workers.isEmpty()) {
    %>
    <tr>
        <td colspan="3" style="text-align:center; color:gray;">Немає записів</td>
    </tr>
    <%
    } else {
        for (Worker w : workers) {
    %>
    <tr>
        <td><%= w.getId() %></td>
        <td><%= w.getName() %></td>
        <td><%= w.getEmail() %></td>
    </tr>
    <%
            }
        }
    %>
</table>

<a href="index.jsp">Back to main</a>

</body>
</html>