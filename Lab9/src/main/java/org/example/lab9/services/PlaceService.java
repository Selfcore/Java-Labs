package org.example.lab9.services;

import org.example.lab9.domain.Place;
import org.example.lab9.repositories.PlaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceService {
    private final PlaceRepository placeRepository;

    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public List<Place> getAll() {
        return placeRepository.findAll();
    }

    public void save(Place place) {
        placeRepository.save(place);
    }
}
