<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>Країни-виробники блокнотів</h2>
    <a href="notebooks">Повернутися до списку блокнотів</a>
    <hr>

    <table border="1" cellpadding="5">
        <tr>
            <th>Країна</th>
            <th>Кількість блокнотів</th>
        </tr>

        <%
            List<Object[]> stats = (List<Object[]>) request.getAttribute("countryStats");

            if (stats != null && !stats.isEmpty()) {
                for (Object[] row : stats) {
        %>
        <tr>
            <td><%= row[0] %></td>
            <td><%= row[1] %></td>
        </tr>
        <%
            }
        } else {
        %>
        <tr><td colspan="2" align="center">Немає даних у базі</td></tr>
        <%
            }
        %>
    </table>
</body>
</html>
