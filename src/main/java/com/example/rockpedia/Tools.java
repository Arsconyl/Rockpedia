package com.example.rockpedia;

import com.example.rockpedia.band.Band;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Tools {

    //Static class
    private Tools() {
    }

    public static Optional<List<Band>> mapToSortedList(Map<Integer, Band> bands)
    {
        Map<Integer, Band> sortedBands = new TreeMap<>(bands);
        return Optional.of(new ArrayList<>(sortedBands.values()));
    }

    public static List<Band> iterableToList(Iterable<Band> listOfBands)
    {
        List<Band> bands = new ArrayList<>();
        for (Band band : listOfBands) {
            bands.add(band);
        }
        return bands;
    }

    public static List<Character> stringToCharList(String string)
    {
        return string.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
    }

    // Generic function to concatenate multiple lists in Java
    @SafeVarargs
    public static<T> List<T> concatenate(List<T>... lists)
    {
        List<T> result = new ArrayList<>();
        Stream.of(lists).forEach(result::addAll);

        return result;
    }
}
