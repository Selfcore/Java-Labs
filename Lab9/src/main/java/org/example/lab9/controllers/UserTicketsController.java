package org.example.lab9.controllers;

import org.example.lab9.domain.Ticket;
import org.example.lab9.domain.User;
import org.example.lab9.services.TicketService;
import org.example.lab9.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/my")
public class UserTicketsController {

    private final TicketService ticketService;
    private final UserService userService;

    @Autowired
    public UserTicketsController(TicketService ticketService,
                                 UserService userService) {
        this.ticketService = ticketService;
        this.userService = userService;
    }

    @GetMapping("/tickets")
    public String myTickets(Model model, Principal principal) {

        User user = userService.findByUsername(principal.getName());
        List<Ticket> tickets = ticketService.getUserTickets(user);

        model.addAttribute("tickets", tickets);
        model.addAttribute("username", user.getUsername());

        return "my-tickets";
    }

}

