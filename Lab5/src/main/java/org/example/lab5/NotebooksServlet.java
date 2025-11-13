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

@WebServlet("/notebooks")
public class NotebooksServlet extends HttpServlet {
    private NotebookDAO repository = new NotebookDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pagesParam = req.getParameter("pages");
        String circulationParam = req.getParameter("circulation");

        List<Notebook> notebooks;

        if (pagesParam != null && !pagesParam.isEmpty()) {
            int pages = Integer.parseInt(pagesParam);
            notebooks = repository.findByPages(pages);
        } else if (circulationParam != null && !circulationParam.isEmpty()) {
            int circulation = Integer.parseInt(circulationParam);
            notebooks = repository.findByCirculation(circulation);
        } else {
            notebooks = repository.findAll();
        }

        Object[] maxCountry = repository.findCountryWithMaxNotebooks();
        Object[] minCountry = repository.findCountryWithMinNotebooks();

        Object[] maxCompanyName = repository.findCompanyWithMaxNotebooks();
        Object[] minCompanyName = repository.findCompanyWithMinNotebooks();

        req.setAttribute("notebooks", notebooks);
        req.setAttribute("maxCountry", maxCountry);
        req.setAttribute("minCountry", minCountry);
        req.setAttribute("maxCompanyName", maxCompanyName);
        req.setAttribute("minCompanyName", minCompanyName);

        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");
        if (action == null || action.isEmpty()) {
            handleCreate(req, resp);
            return;
        }

        switch (action) {
            case "delete" -> handleDelete(req, resp);
            case "update" -> showUpdateForm(req, resp);
            case "updateSubmit" -> handleUpdate(req, resp);
            default -> resp.sendRedirect("notebooks");
        }
    }

    private void handleCreate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Notebook notebook = new Notebook();
        notebook.setManufacturer(req.getParameter("manufacturer"));
        notebook.setName(req.getParameter("name"));
        notebook.setPages(Integer.parseInt(req.getParameter("pages")));
        notebook.setCoverType(req.getParameter("coverType"));
        notebook.setCountry(req.getParameter("country"));
        notebook.setCirculation(Integer.parseInt(req.getParameter("circulation")));
        notebook.setPageStyle(req.getParameter("pageStyle"));

        repository.save(notebook);
        resp.sendRedirect("notebooks");
    }

    private void handleDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        repository.delete(id);
        resp.sendRedirect("notebooks");
    }

    private void showUpdateForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Notebook notebook = repository.findById(id);

        if (notebook == null) {
            resp.sendRedirect("notebooks");
            return;
        }

        req.setAttribute("notebook", notebook);
        req.getRequestDispatcher("edit-notebook.jsp").forward(req, resp);
    }

    private void handleUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Notebook notebook = new Notebook();
        notebook.setId(id);
        notebook.setManufacturer(req.getParameter("manufacturer"));
        notebook.setName(req.getParameter("name"));
        notebook.setPages(Integer.parseInt(req.getParameter("pages")));
        notebook.setCoverType(req.getParameter("coverType"));
        notebook.setCountry(req.getParameter("country"));
        notebook.setCirculation(Integer.parseInt(req.getParameter("circulation")));
        notebook.setPageStyle(req.getParameter("pageStyle"));

        repository.update(notebook);
        resp.sendRedirect("notebooks");
    }
}
