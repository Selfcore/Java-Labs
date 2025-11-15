package org.example.lab4;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CalculateServlet", value = "/calculate")
public class CalculateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        double num1 = Double.parseDouble(req.getParameter("num1"));
        double num2 = Double.parseDouble(req.getParameter("num2"));
        double num3 = Double.parseDouble(req.getParameter("num3"));
        String operation = req.getParameter("operation");

        double result = 0;

        switch (operation) {
            case "min":
                result = Math.min(num1, Math.min(num2, num3));
                break;
            case "max":
                result = Math.max(num1, Math.max(num2, num3));
                break;
            case "avg":
                result = (num1 + num2 + num3) / 3.0;
                break;
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Результат: " + result + "</h2>");
        out.println("<a href=\"calculate.jsp\">Повернутись назад</a>");
        out.println("</body></html>");
    }
}
