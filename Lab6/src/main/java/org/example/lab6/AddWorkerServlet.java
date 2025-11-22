package org.example.lab6;

import Models.Worker;
import Persistence.Repositories.RepositoryImp;
import Persistence.Repositories.WorkerRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(value = "/add-worker")
public class AddWorkerServlet extends HttpServlet {
    private RepositoryImp<Worker> repository;

    @Override
    public void init() {
        repository = new WorkerRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("addWorkerForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Worker worker = new Worker();
        worker.setName(req.getParameter("name"));
        worker.setEmail(req.getParameter("email"));
        worker.setPhoneNumber(req.getParameter("phone"));

        repository.save(worker);

        resp.sendRedirect("workers");
    }
}
