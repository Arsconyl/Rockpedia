package com.example.rockpedia.dao;

import com.example.rockpedia.entity.Band;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BandDAO extends CrudRepository<Band, Long> {
    public Optional<Band> findById(Long id);
    public Optional<Band> findBandByNameContainingIgnoreCase(String name);
    public Optional<Band> findBandByMembersContainingIgnoreCase(String name);
    public Optional<Band> findBandByLabelContainingIgnoreCase(String name);
    public Optional<Band> findBandByTownoforiginContainingIgnoreCase(String name);
    public Optional<Band> findBandByStyleContainingIgnoreCase(String name);
}
