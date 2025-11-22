package org.example.lab6;

import Models.Client;
import Models.Worker;
import Persistence.Repositories.ClientRepository;
import Persistence.Repositories.RepositoryImp;
import Persistence.Repositories.WorkerRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/clients")
public class ClientServlet extends HttpServlet {
    private RepositoryImp<Client> repository;

    @Override
    public void init() {
        repository = new ClientRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Client> clients = repository.getAll();
        req.setAttribute("clients", clients);
        req.getRequestDispatcher("/clients.jsp").forward(req, resp);
    }
}
