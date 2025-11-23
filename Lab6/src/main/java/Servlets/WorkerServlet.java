package Servlets;

import Models.Order;
import Models.Worker;
import Persistence.Repositories.RepositoryImp;
import Persistence.Repositories.WorkerRepository;
import Services.WorkerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.id.IncrementGenerator;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "WorkerServlet", value = "/workers")
public class WorkerServlet extends HttpServlet {
    private RepositoryImp<Worker> repository;
    private WorkerService workerService;

    @Override
    public void init() {
        repository = new WorkerRepository();
        workerService = new WorkerService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        if(id != null){
            Worker worker = repository.getById(Integer.parseInt(id));
            List<Order> lastOrders = workerService.getLastOrders(Integer.parseInt(id), 10);

            req.setAttribute("worker", worker);
            req.setAttribute("lastOrders", lastOrders);
            req.getRequestDispatcher("/worker-details.jsp").forward(req, resp);
        }
        else{
            List<Worker> workers = repository.getAll();
            req.setAttribute("workers", workers);
            req.getRequestDispatcher("/workers.jsp").forward(req, resp);
        }
    }
}
