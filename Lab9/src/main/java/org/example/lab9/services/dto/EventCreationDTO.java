package org.example.lab9.services.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class EventCreationDTO {
    private LocalDate eventDate;
    private String name;
    private PlaceDTO place;
    private List<TicketPackDTO> ticketPacks;
}