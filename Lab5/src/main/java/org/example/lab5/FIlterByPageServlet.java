package org.example.lab5;

import Models.Notebook;
import Persistence.NotebookDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/filter-by-pages")
public class FIlterByPageServlet extends HttpServlet {
    private NotebookDAO repository = new NotebookDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pages = Integer.parseInt(req.getParameter("pages"));
        List<Notebook> notebooks = repository.findByPages(pages);

        req.setAttribute("maxCountry", repository.findCountryWithMaxNotebooks());
        req.setAttribute("minCountry", repository.findCountryWithMinNotebooks());
        req.setAttribute("maxCompanyName", repository.findCompanyWithMaxNotebooks());
        req.setAttribute("minCompanyName", repository.findCompanyWithMinNotebooks());

        req.setAttribute("notebooks", notebooks);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
