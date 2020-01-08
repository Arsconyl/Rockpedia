package com.example.rockpedia.band;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BandRepository extends CrudRepository<Band, Long> {
    public Optional<Band> findById(Long id);
    public Optional<Iterable<Band>> findAllByNameContainingIgnoreCase(String name);
    public Optional<Iterable<Band>> findAllByGenreContainingIgnoreCase(String genre);
    public Optional<Iterable<Band>> findAllByThemesContainingIgnoreCase(String theme);
    public Optional<Iterable<Band>> findAllByLocationContainingIgnoreCase(String location);
    public Optional<Iterable<Band>> findAllByCountryContainingIgnoreCase(String country);
    public Optional<Iterable<Band>> findAllByLabelContainingIgnoreCase(String label);
    public Optional<Iterable<Band>> findAllByStatusContainingIgnoreCase(String status);
    public Optional<Iterable<Band>> findAllByFormed(int formed);
}
