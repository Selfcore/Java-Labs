package org.example.lab5;

import Persistence.NotebookDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/countries")
public class CountryServlet extends HttpServlet {
    private NotebookDAO repository = new NotebookDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Object[]> countryStats = repository.getCountryStatistics();

        req.setAttribute("countryStats", countryStats);
        req.getRequestDispatcher("countries.jsp").forward(req, resp);
    }
}
