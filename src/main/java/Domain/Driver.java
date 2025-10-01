package Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Driver {
    private String name;
    private int experience;
    private Car car;
    private double balance;

    private OrderRequest currentOrder;
    private DriverObserver driverObserver;

    public Driver(String name, int experience, Car car) {
        this.name = name;
        this.experience = experience;
        this.car = car;
        this.balance = 0.0;
    }

    public void completeOrder() {
        currentOrder.setCompleted(true);

        if (driverObserver != null) {
            driverObserver.notify(currentOrder, this);
        }

        currentOrder = null;
    }
}
