package com.example.rockpedia.band;

import com.example.rockpedia.Pair;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Stream;

import static com.example.rockpedia.band.SearchBand.matchScore;

@Service
public class BandRESTService {

    private static final String MESSAGEBEGIN = "{\n\t\"message\": \"";
    private static final String NOTPROVIDED = " has not been provided\"\n}";

    private final BandRepository bandRepository;

    public BandRESTService(BandRepository bandRepository) {
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
            throw new IllegalArgumentException(MESSAGEBEGIN + valueNull + NOTPROVIDED);
        return bandRepository.save(band);
    }

    public Band insert(Long id, Band band)
    {
        String valueNull = band.valueIsNull();
        if(valueNull != null) {
            throw new IllegalArgumentException(MESSAGEBEGIN + valueNull + NOTPROVIDED);
        }
            band.setId(id);
        return bandRepository.save(band);
    }

    public String deleteById(Long id)
    {
        try {
            bandRepository.deleteById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException(MESSAGEBEGIN + e.getMessage() + "\"\n}");
        }
        return "{\n\t\"message\": \"Band " + id + " has been deleted succesfully.\"\n}";
    }

    public List<Band> searchBand(String query) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        List<Band> byName = searchBandName(query);
        List<Band> byGenre = searchBandGenre(query);
        List<Band> byTheme = searchBandTheme(query);
        List<Band> byLocation = searchBandLocation(query);
        List<Band> byCountry = searchBandCountry(query);

        return concatenate(byName, byGenre, byTheme, byLocation, byCountry);
    }

    public List<Band> searchBandName(String query) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return searchAdvanced(query, "name");
    }

    public List<Band> searchBandGenre(String query) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return searchAdvanced(query, "genre");
    }


    public List<Band> searchBandTheme(String query) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return searchAdvanced(query, "themes");
    }

    public List<Band> searchBandLocation(String query) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return searchAdvanced(query, "location");
    }

    public List<Band> searchBandCountry(String query) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return searchAdvanced(query, "country");
    }

    public List<Band> searchBandLabel(String query) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return searchAdvanced(query, "label");
    }

    public List<Band> searchBandStatus(String query) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return searchAdvanced(query, "status");
    }

    public List<Band> searchBandFormed(int query)
    {
        Optional<Iterable<Band>> listOfBands = bandRepository.findAllByFormed(query);
        List<Band> bands = new ArrayList<>();
        if(listOfBands.isPresent())
            bands = iterableToList(listOfBands.get());
        return bands;
    }

    private List<Band> searchAdvanced(String query, String memberToSearch) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return matchScoreBand(getAll(), query, memberToSearch);
    }

    public static List<Band> iterableToList(Iterable<Band> listOfBands)
    {
        List<Band> bands = new ArrayList<>();
        for (Band band : listOfBands) {
            bands.add(band);
        }
        return bands;
    }

    @SafeVarargs
    public static<T> List<T> concatenate(List<T>... lists)
    {
        List<T> result = new ArrayList<>();
        Stream.of(lists).forEach(result::addAll);

        return result;
    }

    public static List<Band> matchScoreBand(List<Band> all, String query, String memberToSearch) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Pair<Integer, Band>> allSorted = new ArrayList<>();
        String memberGetter = "get"+memberToSearch.substring(0, 1).toUpperCase() + memberToSearch.substring(1).toLowerCase();
        for(Band band: all)
        {
            String value = band.getClass().getMethod(memberGetter).invoke(band).toString();
            int score = matchScore(value, query);
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
