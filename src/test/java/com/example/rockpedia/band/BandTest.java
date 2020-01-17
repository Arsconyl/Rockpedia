package com.example.rockpedia.band;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BandTest {

    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @Test
    public void testBand()
    {
        Band band = new Band();
        band.setId(169L);
        band.setName("Eclipse");
        band.setGenre("Blackened Death");
        band.setThemes("Apocalypse, Horror, Fun");
        band.setLocation("Tübingen, Baden-Württemberg");
        band.setCountry("Germany");
        band.setLabel("Unsigned/independent");
        band.setStatus("Active");
        band.setFormed(2008);

        assertEquals(new Long(169), band.getId());
        assertEquals("Eclipse", band.getName());
        assertEquals("Blackened Death", band.getGenre());
        assertEquals("Apocalypse, Horror, Fun", band.getThemes());
        assertEquals("Tübingen, Baden-Württemberg", band.getLocation());
        assertEquals("Germany", band.getCountry());
        assertEquals("Unsigned/independent", band.getLabel());
        assertEquals("Active", band.getStatus());
        assertEquals(2008, band.getFormed());
    }
}
