package Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Workers")
public class Worker {
    @Id
    private int id;

    private String name;
    private String email;
    private String phoneNumber;

    @OneToMany(mappedBy = "worker", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();
}
