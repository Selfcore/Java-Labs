package org.example.lab9.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Ticket {

    public enum Status { FREE, SOLD }

    @Id
    @GeneratedValue
    private Long id;

    private Double cost;
    private Integer number;

    @Enumerated(EnumType.STRING)
    private Status status = Status.FREE;

    @ManyToOne
    private User customer;

    @ManyToOne
    private Event event;
}
