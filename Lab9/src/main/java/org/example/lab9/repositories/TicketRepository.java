package org.example.lab9.repositories;

import org.example.lab9.domain.Event;
import org.example.lab9.domain.Ticket;
import org.example.lab9.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByCustomer(User customer);
    List<Ticket> findByEventAndStatus(Event event, Ticket.Status status);
}
