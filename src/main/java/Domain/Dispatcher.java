package Domain;

import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Scanner;

@NoArgsConstructor
public class Dispatcher implements DriverObserver {
    private DepotRepository depotRepository;

    public Dispatcher(DepotRepository depotRepository) {
        this.depotRepository = depotRepository;
    }

    public void assignOrderToDriver() {
        List<OrderRequest> availableOrders = depotRepository.getUnassignedOrders();

        System.out.println("Available orders: " + availableOrders);

        Scanner scanner = new Scanner(System.in);
        int choose = scanner.nextInt();

        OrderRequest targetOrder = availableOrders.get(choose);

        List<Car> cars = depotRepository.getSuitableCars(targetOrder.getWeight(), targetOrder.getCargoType());
        List<Driver> availableDrivers = depotRepository.getAvailableSuitableDrivers(targetOrder.getNeededExperience());

        System.out.println("Drivers available: " + availableDrivers);

        choose =  scanner.nextInt();

        Driver targetDriver = availableDrivers.get(choose);

        System.out.println("Cars available: " + cars);

        choose = scanner.nextInt();

        Car targetCar = cars.get(choose);

        targetDriver.setCar(targetCar);
        targetDriver.setDriverObserver(this);
        targetDriver.setCurrentOrder(targetOrder);

        targetOrder.setAssigned(true);

        System.out.println("Assigned order to driver: " + targetDriver.getName() + ", order: " + targetOrder.getDestination());
    }

    @Override
    public void notify(OrderRequest order, Driver driver) {
        System.out.println("Driver: " + driver.getName() + " complete " + order.getDestination() + " order");
    }
}
