package com.example.rockpedia.band;

import com.example.rockpedia.Pair;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.example.rockpedia.Tools.*;

@Service
public class BandService {

    private final BandRepository bandRepository;

    public BandService(BandRepository bandRepository) {
        this.bandRepository = bandRepository;
    }

    public List<Band> getAll()
    {
        List<Band> bands = new ArrayList<>();
        bandRepository.findAll().forEach(bands::add);
        return bands;
    }

    public Band byId(Long id)
    {
        Optional<Band> bandToGet = this.bandRepository.findById(id);
        return bandToGet.orElse(null);
    }

    public Band add(Band band)
    {
        String valueNull = band.valueIsNull();
        if(valueNull != null)
            throw new IllegalArgumentException("{\n\t\"message\": \"" + valueNull + " has not been provided\"\n}");
        return bandRepository.save(band);
    }

    public Band insert(Long id, Band band)
    {
        String valueNull = band.valueIsNull();
        if(valueNull != null) {
            throw new IllegalArgumentException("{\n\t\"message\": \"" + valueNull + " has not been provided\"\n}");
        }
            band.setId(id);
        return bandRepository.save(band);
    }

    public String deleteById(Long id)
    {
        try {
            bandRepository.deleteById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException("{\n\t\"message\": \"" + e.getMessage() + "\"\n}");
        }
        return "{\n\t\"message\": \"Band " + id + " has been deleted succesfully.\"\n}";
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

    public List<Band> searchAdvanced(String query)
    {
        List<Band> all = getAll();
        List<Pair<Integer, Band>> allSorted = new ArrayList<>();
        for(Band band: all)
        {
            String name = band.getName();
            int score = matchScore(name, query);
            if(score > 0)
                allSorted.add(new Pair<>(score, band));
        }

        allSorted.sort((o1, o2) -> {
            if (o1.getKey() < o2.getKey())
                return 1;
            else if (o1.getKey().equals(o2.getKey()))
                return 0;
            else if (o1.getKey() > o2.getKey())
                return -1;
            return 0;
        });

        all.clear();

        for(Pair<Integer, Band> band: allSorted)
            all.add(band.getValue());
        return all;
    }
}
