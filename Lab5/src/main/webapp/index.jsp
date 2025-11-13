<%@ page import="Models.Notebook" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Блокноти</title>
</head>
<body>
    <h2>Список блокнотів</h2>
    <a href="countries">Переглянути статистику по країнах</a> |
    <a href="add-notebook.jsp">Додати блокнот</a> |
    <a href="notebooks">Скинути фільтри</a>

    <form action="filter-by-pages" method="get" style="margin-bottom:10px;">
        <label>Кількість сторінок:</label>
        <input type="number" name="pages" min="1" required>
        <button type="submit">Застосувати</button>
    </form>

    <form action="filter-by-circulation" method="get" style="margin-bottom:20px;">
        <label>Тираж:</label>
        <input type="number" name="circulation" min="1" required>
        <button type="submit">Застосувати</button>
    </form>

    <hr>

    <table border="1" width="100%" style="text-align: center">
        <tr>
            <th>ID</th><th>Фірма</th><th>Назва</th><th>Сторінки</th><th>Обкладинка</th>
            <th>Країна</th><th>Тираж</th><th>Сторінка</th><th>Дії</th>
        </tr>
        <%
            List<Notebook> list = (List<Notebook>) request.getAttribute("notebooks");
            if (list != null && !list.isEmpty()) {
                for (Notebook notebook : list) {
        %>
        <tr>
            <td><%= notebook.getId() %></td>
            <td><%= notebook.getManufacturer() %></td>
            <td><%= notebook.getName() %></td>
            <td><%= notebook.getPages() %></td>
            <td><%= notebook.getCoverType() %></td>
            <td><%= notebook.getCountry() %></td>
            <td><%= notebook.getCirculation() %></td>
            <td><%= notebook.getPageStyle() %></td>
            <td>
                <!-- Редагувати -->
                <form action="notebooks" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="id" value="<%= notebook.getId() %>">
                    <button type="submit"
                            style="background:none;border:none;color:blue;text-decoration:underline;cursor:pointer;padding:0;">
                        Редагувати
                    </button>
                </form>

                |

                <form action="notebooks" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="id" value="<%= notebook.getId() %>">
                    <button type="submit"
                            style="background:none;border:none;color:red;text-decoration:underline;cursor:pointer;padding:0;">
                        Видалити
                    </button>
                </form>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr><td colspan="9">Немає записів у базі</td></tr>
        <%
            }
        %>
    </table>

    <hr>
    <h3>Загальна статистика:</h3>
    <%
        Object[] maxCountry = (Object[]) request.getAttribute("maxCountry");
        Object[] minCountry = (Object[]) request.getAttribute("minCountry");

        Object[] maxCompanyName = (Object[]) request.getAttribute("maxCompanyName");
        Object[] minCompanyName = (Object[]) request.getAttribute("minCompanyName");
        if (maxCountry != null && minCountry != null) {
    %>
    <p><b>Країна з найбільшою кількістю блокнотів:</b> <%= maxCountry[0] %> (<%= maxCountry[1] %>)</p>
    <p><b>Країна з найменшою кількістю блокнотів:</b> <%= minCountry[0] %> (<%= minCountry[1] %>)</p>
    <p><b>Виробник з найбільшою кількістю блокнотів:</b> <%= maxCompanyName[0] %> (<%= maxCompanyName[1] %>)</p>
    <p><b>Виробник з найменшою кількістю блокнотів:</b> <%= minCompanyName[0] %> (<%= minCompanyName[1] %>)</p>
    <%
    } else {
    %>
    <p>Недостатньо даних для статистики.</p>
    <%
        }
    %>
</body>
</html>