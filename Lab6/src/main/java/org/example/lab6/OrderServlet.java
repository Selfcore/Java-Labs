package org.example.lab6;

import Models.Order;
import Persistence.Repositories.OrderRepository;
import Persistence.Repositories.RepositoryImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/orders")
public class OrderServlet extends HttpServlet {
    private RepositoryImp<Order> repository;

    @Override
    public void init() throws ServletException {
        repository = new OrderRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");

        if(idParam != null) {
            int orderId = Integer.parseInt(idParam);
            Order order = repository.getById(orderId);

            if (order == null) {
                resp.sendRedirect("orders");
                return;
            }

            req.setAttribute("order", order);
            req.getRequestDispatcher("/order-details.jsp").forward(req, resp);

        } else{

            List<Order> orders = repository.getAll();

            req.setAttribute("orders", orders);

            req.getRequestDispatcher("orders.jsp").forward(req, resp);
        }
    }
}
