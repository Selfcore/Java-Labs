package Domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class DepotRepository {
    private List<Driver> drivers = new ArrayList<>();
    private List<Car> cars = new ArrayList<>();
    private List<OrderRequest> orderRequests = new ArrayList<>();

    public List<Car> getSuitableCars(int weight, CargoType cargoType) {
        return cars.stream().filter(car -> car.canCarry(cargoType, weight))
                .sorted((c1, c2) -> {
                  double excess1 = c1.getCarryingCapacity() - weight;
                  double excess2 = c2.getCarryingCapacity() - weight;

                  return Double.compare(excess1, excess2);
                }).toList();
    }

    public List<Driver> getAvailableSuitableDrivers(int experience) {
        return drivers.stream().filter(driver -> driver.getCurrentOrder() == null && driver.getExperience() >= experience)
                .toList();
    }

    public List<OrderRequest> getUnassignedOrders() {
        return orderRequests.stream()
                .filter(order -> !order.isAssigned())
                .toList();
    }

    public void addOrder(@NonNull OrderRequest order) {
        orderRequests.add(order);
    }

    public void addCar(@NonNull Car car) {
        cars.add(car);
    }

    public void addDriver(@NonNull Driver driver) {
        drivers.add(driver);
    }
}
