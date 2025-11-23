package Services;

import Models.Order;
import Models.Worker;
import Persistence.Repositories.RepositoryImp;
import Persistence.Repositories.WorkerRepository;

import java.util.Comparator;
import java.util.List;

public class WorkerService {
    private RepositoryImp<Worker> repository;

    public WorkerService() {
        repository = new WorkerRepository();
    }

    public List<Order> getLastOrders(int workerId, int limit) {
        Worker worker = repository.getById(workerId);

        if (worker == null)
            return List.of();

        return worker.getOrders()
                .stream()
                .sorted(Comparator.comparing(Order::getId).reversed())
                .limit(limit)
                .toList();
    }
}
