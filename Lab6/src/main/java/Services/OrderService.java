package Services;

import Models.Order;
import Persistence.Repositories.OrderRepository;
import Persistence.Repositories.RepositoryImp;

import java.time.LocalDate;
import java.util.List;

public class OrderService {
    private RepositoryImp<Order> repository;

    public OrderService(RepositoryImp<Order> repository) {
        this.repository = repository;
    }

    public List<Order> getBetweenDate(String fromStr, String toStr) {

        LocalDate from = parseDate(fromStr);
        LocalDate to = parseDate(toStr);

        if (from == null && to == null) {
            return repository.getAll();
        }

        if (from == null)
            from = LocalDate.of(1970, 1, 1);
        if (to == null)
            to = LocalDate.of(3000, 1, 1);

        OrderRepository orderRepository = (OrderRepository) repository;

        return orderRepository.getByDateRange(from, to);
    }

    private LocalDate parseDate(String dateStr) {
        if (dateStr == null || dateStr.isEmpty()) {
            return null;
        }
        return LocalDate.parse(dateStr);
    }
}
