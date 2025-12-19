package org.example.lab9.services;

import jakarta.transaction.Transactional;
import org.example.lab9.domain.Event;
import org.example.lab9.domain.Ticket;
import org.example.lab9.repositories.EventRepository;
import org.example.lab9.repositories.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final TicketRepository ticketRepository;

    public EventService(EventRepository eventRepository,
                        TicketRepository ticketRepository) {
        this.eventRepository = eventRepository;
        this.ticketRepository = ticketRepository;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public List<Ticket> getFreeTickets(Event event) {
        return ticketRepository.findByEventAndStatus(event, Ticket.Status.FREE);
    }

    public void saveEvent(Event event) {
        eventRepository.save(event);
    }

    @Transactional
    public void saveEventWithTickets(Event event, int ticketCount, double ticketPrice) {
        eventRepository.save(event);

        for (int i = 1; i <= ticketCount; i++) {
            Ticket ticket = new Ticket();
            ticket.setEvent(event);
            ticket.setNumber(i);
            ticket.setCost(ticketPrice);
            ticket.setStatus(Ticket.Status.FREE);

            ticketRepository.save(ticket);
        }
    }
}
