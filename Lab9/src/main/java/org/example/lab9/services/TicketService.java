package org.example.lab9.services;

import jakarta.transaction.Transactional;
import org.example.lab9.domain.Ticket;
import org.example.lab9.domain.User;
import org.example.lab9.repositories.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> getUserTickets(User user) {
        return ticketRepository.findByCustomer(user);
    }

    @Transactional
    public void buyTicket(Long ticketId, User user) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        if (ticket.getStatus() == Ticket.Status.SOLD) {
            throw new RuntimeException("Ticket already sold");
        }

        ticket.setStatus(Ticket.Status.SOLD);
        ticket.setCustomer(user);

        ticketRepository.save(ticket);
    }
}
