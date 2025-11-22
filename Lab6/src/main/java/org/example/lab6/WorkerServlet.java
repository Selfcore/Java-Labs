package org.example.lab6;

import Models.Worker;
import Persistence.Repositories.RepositoryImp;
import Persistence.Repositories.WorkerRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "WorkerServlet", value = "/workers")
public class WorkerServlet extends HttpServlet {
    private RepositoryImp<Worker> repository;

    @Override
    public void init() {
        repository = new WorkerRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Worker> workers = repository.getAll();
        req.setAttribute("workers", workers);
        req.getRequestDispatcher("/workers.jsp").forward(req, resp);
    }
}
