package Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Product {
    @Id
    private int id;

    private String title;
    private double price;

    @OneToMany(mappedBy = "product")
    private List<OrderLine> orderLines = new ArrayList<>();
}
