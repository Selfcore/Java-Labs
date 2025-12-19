package org.example.lab9.controllers;

import org.example.lab9.domain.Event;
import org.example.lab9.domain.Place;
import org.example.lab9.services.EventService;
import org.example.lab9.services.PlaceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final EventService eventService;
    private final PlaceService placeService;

    public AdminController(EventService eventService, PlaceService placeService) {
        this.eventService = eventService;
        this.placeService = placeService;
    }

    @GetMapping("/events/new")
    public String newEventForm(Model model) {
        model.addAttribute("event", new Event());
        model.addAttribute("places", placeService.getAll());
        return "admin/event-form";
    }

    @PostMapping("/events")
    public String createEvent(@ModelAttribute Event event,
                              @RequestParam int ticketCount,
                              @RequestParam double ticketPrice) {

        eventService.saveEventWithTickets(event, ticketCount, ticketPrice);
        return "redirect:/events";
    }

    @GetMapping("/places")
    public String places(Model model) {
        model.addAttribute("places", placeService.getAll());
        return "admin/places";
    }

    @GetMapping("/places/new")
    public String newPlace(Model model) {
        model.addAttribute("place", new Place());
        return "admin/place-form";
    }

    @PostMapping("/places")
    public String savePlace(@ModelAttribute Place place) {
        placeService.save(place);
        return "redirect:/admin/places";
    }
}
