package org.example.lab9.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Event {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDate eventDate;
    private String name;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Ticket> tickets;

    @ManyToOne
    private Place place;
}
