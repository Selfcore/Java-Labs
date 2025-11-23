package Servlets;

import Models.Order;
import Persistence.Repositories.OrderRepository;
import Persistence.Repositories.RepositoryImp;
import Services.OrderService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(value = "/orders")
public class OrderServlet extends HttpServlet {
    private RepositoryImp<Order> repository;
    private OrderService service;

    @Override
    public void init() throws ServletException {
        repository = new OrderRepository();
        service = new OrderService(repository);
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
            return;
        }

        String from = req.getParameter("from");
        String to = req.getParameter("to");

        List<Order> orders = service.getBetweenDate(from, to);

        req.setAttribute("orders", orders);
        req.getRequestDispatcher("orders.jsp").forward(req, resp);
    }
}
