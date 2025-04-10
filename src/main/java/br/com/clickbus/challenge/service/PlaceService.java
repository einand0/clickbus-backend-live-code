package br.com.clickbus.challenge.service;


import br.com.clickbus.challenge.dto.PlaceDTO;
import br.com.clickbus.challenge.entity.Place;
import br.com.clickbus.challenge.repository.PlaceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.apache.commons.lang3.NotImplementedException;

@Service
@AllArgsConstructor
public class PlaceService {

    private PlaceRepository repository;

    public List<Place> findAll() {
        return repository.findAll();
    }

    public Optional<Place> findById(@NotNull Long id) {
        return repository.findById(id);
    }

    public Place save(@NotNull Place place) {
        return repository.save(place);
    }

    public List<Place> findByName(@NotNull String name) {
        return repository.findByName(name);
    }

    public Place alter(@NotNull Place place, @NotNull PlaceDTO placeDTO) {
        Optional<Place> foundedPlace = repository.findById(place.getId());
        if(foundedPlace.isEmpty()){
            throw new NoSuchElementException("Place não encontrado!");
        }

        Place updatedPlace = foundedPlace.get();

        foundedPlace.get().setName(placeDTO.getName());
        foundedPlace.get().setCity(placeDTO.getCity());
        foundedPlace.get().setSlug(placeDTO.getSlug());
        foundedPlace.get().setState(placeDTO.getState());
        foundedPlace.get().setUpdatedAt(LocalDateTime.now());

        return repository.save(updatedPlace);
    }
}
