package Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Car {
    private String model;
    private double carryingCapacity;
    private List<CargoType> supportedCargoTypes = new ArrayList<>();
    private boolean isAvailable;

    public boolean canCarry(CargoType cargoType, double weight) {
        return supportedCargoTypes.contains(cargoType) && carryingCapacity >= weight;
    }
}
