package com.example.rockpedia;

import com.example.rockpedia.band.Band;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Stream;

public class Tools {

    //Static class
    private Tools() {
    }

    public static List<Band> iterableToList(Iterable<Band> listOfBands)
    {
        List<Band> bands = new ArrayList<>();
        for (Band band : listOfBands) {
            bands.add(band);
        }
        return bands;
    }

    // Generic function to concatenate multiple lists in Java
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
                return -1;
            else if (o1.getKey().equals(o2.getKey()))
                return 0;
            else if (o1.getKey() > o2.getKey())
                return 1;
            return 0;
        });

        all.clear();

        for(Pair<Integer, Band> band: allSorted)
            all.add(band.getValue());
        return all;
    }

    public static int matchScore(String input, String pattern)
    {
        return matchScore(input, pattern, false, input.contains("œ") || input.contains("Œ") || input.contains("æ") || input.contains("Æ") || input.contains("ß"));
    }
    private static int matchScore(String input, String pattern, boolean found, boolean noCheckLength)
    {
        do
        {
            int inputLength = input.length();
            int patternLength = pattern.length();
            if(inputLength < patternLength && !noCheckLength) {
                return -2000000000;
            }
            if (inputLength == 0 || patternLength == 0)
                return patternLength != 0 ? -2000000000 : 0;
            else
            {
                char in = input.charAt(0);
                char pat = pattern.charAt(0);
                if (in == 'œ' || in == 'Œ' || in == 'æ' || in == 'Æ' || in == 'ß')
                {
                    String spe = replaceSpecialChar(in);
                    String spePat = pattern.substring(0,2);
                    if (spe.equals(spePat))
                        return 2 + matchScore(input.substring(1), pattern.substring(2), true, noCheckLength);
                }
                if (in == pat)
                    return 4 + matchScore(input.substring(1), pattern.substring(1), true, noCheckLength);
                else if (Character.toLowerCase(in) == Character.toLowerCase(pat))
                    return 3 + matchScore(input.substring(1), pattern.substring(1), true, noCheckLength);
                else if (in == ' ')
                    return -1 + matchScore(input.substring(1), pattern, false, noCheckLength);
                else if (pat == ' ')
                    return -1 + matchScore(input, pattern.substring(1), false, noCheckLength);
                else {
                    in = StringUtils.stripAccents(String.valueOf(in)).toCharArray()[0];
                    pat = StringUtils.stripAccents(String.valueOf(pat)).toCharArray()[0];
                    if (in == pat)
                        return 2 + matchScore(input.substring(1), pattern.substring(1), true, noCheckLength);
                    else if (Character.toLowerCase(in) == Character.toLowerCase(pat))
                        return 1 + matchScore(input.substring(1), pattern.substring(1), true, noCheckLength);
                }
            }
            input = input.substring(1);
        }
        while(!found);
        return -2000000000;
    }

    private static String replaceSpecialChar(char ch)
    {
        switch (ch) {
            case 'œ':
                return "oe";
            case 'Œ':
                return "OE";
            case 'æ':
                return "ae";
            case 'Æ':
                return "AE";
            case 'ß':
                return "ss";
            default:
                return "";
        }
    }
}
