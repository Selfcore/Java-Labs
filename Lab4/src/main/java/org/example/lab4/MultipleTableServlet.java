package org.example.lab4;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "multipleTableServlet", value = "/multiple-table")
public class MultipleTableServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        String numberParam = req.getParameter("number");
        out.println("<html><body>");
        out.println("<h1>Таблиця множення</h1>");

        if (numberParam != null && !numberParam.isEmpty()) {
            int number = Integer.parseInt(numberParam);
            out.println("<h2>Для числа: " + number + "</h2>");
            out.println("<table border='1' cellpadding='5'>");
            out.println("<tr><th>Множник</th><th>Результат</th></tr>");

            for (int i = 1; i <= number; i++) {
                out.println("<tr><td>" + number + " × " + i + "</td><td>" + (number * i) + "</td></tr>");
            }

            out.println("</table>");
        } else {
            out.println("<p style='color:red;'>Ви не ввели число!</p>");
        }

        out.println("<br><a href='index.jsp'>Назад</a>");
        out.println("</body></html>");
    }
}
