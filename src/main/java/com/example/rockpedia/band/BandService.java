package com.example.rockpedia.band;

import org.springframework.stereotype.Service;

import java.util.*;

import static com.example.rockpedia.Tools.*;

@Service
public class BandService {

    private final BandRepository bandRepository;

    public BandService(BandRepository bandRepository) {
        this.bandRepository = bandRepository;
    }

    public List<Band> searchBand(String query)
    {
        List<Band> byName = searchBandName(query);
        List<Band> byGenre = searchBandGenre(query);
        List<Band> byTheme = searchBandTheme(query);
        List<Band> byLocation = searchBandLocation(query);
        List<Band> byCountry = searchBandCountry(query);

        return concatenate(byName, byGenre, byTheme, byLocation, byCountry);
    }

    public List<Band> searchBandName(String query)
    {
        Optional<Iterable<Band>> listOfBands = bandRepository.findAllByNameContainingIgnoreCase(query);
        List<Band> bands = new ArrayList<>();
        if(listOfBands.isPresent())
            bands = iterableToList(listOfBands.get());
        return bands;
    }

    public List<Band> searchBandGenre(String query)
    {
        Optional<Iterable<Band>> listOfBands = bandRepository.findAllByGenreContainingIgnoreCase(query);
        List<Band> bands = new ArrayList<>();
        if(listOfBands.isPresent())
            bands = iterableToList(listOfBands.get());
        return bands;
    }

    public List<Band> searchBandTheme(String query)
    {
        Optional<Iterable<Band>> listOfBands = bandRepository.findAllByThemesContainingIgnoreCase(query);
        List<Band> bands = new ArrayList<>();
        if(listOfBands.isPresent())
            bands = iterableToList(listOfBands.get());
        return bands;
    }

    public List<Band> searchBandLocation(String query)
    {
        Optional<Iterable<Band>> listOfBands = bandRepository.findAllByLocationContainingIgnoreCase(query);
        List<Band> bands = new ArrayList<>();
        if(listOfBands.isPresent())
            bands = iterableToList(listOfBands.get());
        return bands;
    }

    public List<Band> searchBandCountry(String query)
    {
        Optional<Iterable<Band>> listOfBands = bandRepository.findAllByCountryContainingIgnoreCase(query);
        List<Band> bands = new ArrayList<>();
        if(listOfBands.isPresent())
            bands = iterableToList(listOfBands.get());
        return bands;
    }

    public List<Band> searchBandLabel(String query)
    {
        Optional<Iterable<Band>> listOfBands = bandRepository.findAllByLabelContainingIgnoreCase(query);
        List<Band> bands = new ArrayList<>();
        if(listOfBands.isPresent())
            bands = iterableToList(listOfBands.get());
        return bands;
    }

    public List<Band> searchBandStatus(String query)
    {
        Optional<Iterable<Band>> listOfBands = bandRepository.findAllByStatusContainingIgnoreCase(query);
        List<Band> bands = new ArrayList<>();
        if(listOfBands.isPresent())
            bands = iterableToList(listOfBands.get());
        return bands;
    }

    public List<Band> searchBandFormed(int query)
    {
        Optional<Iterable<Band>> listOfBands = bandRepository.findAllByFormed(query);
        List<Band> bands = new ArrayList<>();
        if(listOfBands.isPresent())
            bands = iterableToList(listOfBands.get());
        return bands;
    }
}
