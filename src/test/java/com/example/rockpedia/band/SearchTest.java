package com.example.rockpedia.band;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.text.Normalizer;

import static com.example.rockpedia.Tools.matchScore;
import static org.junit.Assert.assertEquals;

public class SearchTest {

    @Test
    public void testMatch()
    {
        assertEquals(17, matchScore("Mál akÁœ", "malaka"));
        assertEquals(34, matchScore("São Paulo", "Sao Paulo"));
        assertEquals(18, matchScore("Bærum", "Baerum"));
    }
}
