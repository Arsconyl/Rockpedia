package com.example.rockpedia.band;

import org.junit.Test;

import static com.example.rockpedia.Tools.matchScore;
import static org.junit.Assert.assertEquals;

public class SearchTest {

    @Test
    public void testMatch()
    {
        assertEquals(17, matchScore("Mál akÁœ", "malaka"));
        assertEquals(34, matchScore("São Paulo", "Sao Paulo"));
        assertEquals(18, matchScore("Bærum", "Baerum"));
        assertEquals(10, matchScore("Isolation, Society, Anxiety, Hope, Self-Discovery", "xie"));
        assertEquals(15, matchScore("Fragile Existence", "sten"));
        assertEquals(-2000000007, matchScore("Death, Torture (early), Suicide, Death, Misanthropy, Insanity (later)", "xie"));
    }
}
