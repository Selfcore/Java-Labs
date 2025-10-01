package Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private String destination;
    private int quantity;
    private CargoType cargoType;
    private int weight;
    private int neededExperience;

    private boolean isAssigned;
    private boolean isCompleted;
}
