package org.example.lab9.controllers;

import org.example.lab9.domain.User;
import org.example.lab9.services.EventService;
import org.example.lab9.services.TicketService;
import org.example.lab9.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;
    private final TicketService ticketService;
    private final UserService userService;

    @Autowired
    public EventController(EventService eventService,
                           TicketService ticketService,
                           UserService userService) {
        this.eventService = eventService;
        this.ticketService = ticketService;
        this.userService = userService;
    }

    @GetMapping
    public String events(Model model) {
        model.addAttribute("events", eventService.getAllEvents());
        return "events";
    }



    @PostMapping("/buy/{id}")
    public String buyTicket(@PathVariable Long id,
                            Authentication authentication) {

        User user = userService.findByUsername(authentication.getName());

        ticketService.buyTicket(id, user);

        return "redirect:/my/tickets";
    }
}
