package org.example.lab6;

import Models.Client;
import Persistence.Repositories.ClientRepository;
import Persistence.Repositories.RepositoryImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/add-client")
public class AddClientServlet extends HttpServlet {
    private RepositoryImp<Client> repository;

    @Override
    public void init() {
        repository = new ClientRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("addClientForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Client client = new Client();
        client.setName(req.getParameter("name"));
        client.setEmail(req.getParameter("email"));
        client.setPhoneNumber(req.getParameter("phone"));

        repository.save(client);

        resp.sendRedirect("clients");
    }
}
