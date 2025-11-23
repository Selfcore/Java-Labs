package Servlets;

import Models.*;
import Persistence.Repositories.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(value = "/add-order")
public class AddOrderServlet extends HttpServlet {
    private RepositoryImp<Client> clientRepository;
    private RepositoryImp<Worker> workerRepository;
    private RepositoryImp<Order> orderRepository;
    private RepositoryImp<Product> productRepository;

    @Override
    public void init() {
        clientRepository = new ClientRepository();
        workerRepository = new WorkerRepository();
        orderRepository = new OrderRepository();
        productRepository = new ProductRepository();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("clients", clientRepository.getAll());
        request.setAttribute("workers", workerRepository.getAll());
        request.setAttribute("products", productRepository.getAll());

        request.getRequestDispatcher("addOrderForm.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int clientId = Integer.parseInt(request.getParameter("client_id"));
        int workerId = Integer.parseInt(request.getParameter("worker_id"));

        Client client = clientRepository.getById(clientId);
        Worker worker = workerRepository.getById(workerId);

        Order order = new Order();
        order.setClient(client);
        order.setWorker(worker);
        order.setDate(new Date());

        List<Product> products = productRepository.getAll();
        for (Product p : products) {
            String param = request.getParameter("product_" + p.getId());
            if (param == null || param.isEmpty()) continue;

            int qty = Integer.parseInt(param);
            if (qty > 0) {
                OrderLine line = new OrderLine();
                line.setOrder(order);
                line.setProduct(p);
                line.setQuantity(qty);
                order.getOrderLines().add(line);
            }
        }

        orderRepository.save(order);

        response.sendRedirect("orders");
    }
}
