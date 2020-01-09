package com.example.rockpedia.band;

import com.example.rockpedia.band.Band;
import com.example.rockpedia.band.BandController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BandControllerTest {

    @Autowired
    BandController bandController;

    @Test
    public void testBandReturnedById()
    {
        Band bandtoverify = bandController.byId(2L).getBody();

        Band band = new Band();
        band.setId(2L);
        band.setName("S.U.T.U.R.E.");
        band.setGenre("Black/Crust");
        band.setThemes("Misanthropy");
        band.setLocation("Metz, Grand Est");
        band.setCountry("France");
        band.setLabel("Black Pandemie Production");
        band.setStatus("Active");
        band.setformed(2016);

        assertEquals(band, bandtoverify);
    }

    @Test
    public void testBandReturnedByName()
    {
        List listOfBands = bandController.byName("sten").getBody();
        assert listOfBands != null;
        Iterator bands = listOfBands.iterator();
        assertTrue(bands.hasNext());

        Band bandtoverify = (Band) bands.next();

        Band band = new Band();
        band.setId(135L);
        band.setName("Fragile Existence");
        band.setGenre("Death");
        band.setThemes("Death, Gore");
        band.setLocation("Toronto, Ontario");
        band.setCountry("Canada");
        band.setLabel("Unsigned/independent");
        band.setStatus("Active");
        band.setformed(2007);

        assertEquals(band, bandtoverify);
    }

    @Test
    public void testBandReturnedByGenre()
    {
        List listOfBands = bandController.byGenre("sl").getBody();
        assert listOfBands != null;
        Iterator bands = listOfBands.iterator();
        assertTrue(bands.hasNext());

        Band bandtoverify = (Band) bands.next();

        Band band = new Band();
        band.setId(49L);
        band.setName("Kratic");
        band.setGenre("Sludge");
        band.setThemes("Life, Humanity");
        band.setLocation("Oslo/Bærum");
        band.setCountry("Norway");
        band.setLabel("Unsigned/independent");
        band.setStatus("Split-up");
        band.setformed(2005);

        assertEquals(band, bandtoverify);
    }

    @Test
    public void testBandReturnedByTheme()
    {
        List listOfBands = bandController.byTheme("xie").getBody();
        assert listOfBands != null;
        Iterator bands = listOfBands.iterator();
        assertTrue(bands.hasNext());

        Band bandtoverify = (Band) bands.next();

        Band band = new Band();
        band.setId(52L);
        band.setName("11th Dimension");
        band.setGenre("Progressive");
        band.setThemes("Isolation, Society, Anxiety, Hope, Self-Discovery");
        band.setLocation("Lisbon, Lisbon");
        band.setCountry("Portugal");
        band.setLabel("Unsigned/independent");
        band.setStatus("Active");
        band.setformed(2013);

        assertEquals(band, bandtoverify);
    }

    @Test
    public void testBandReturnedByLocation()
    {
        List listOfBands = bandController.byLocation("ingen").getBody();
        assert listOfBands != null;
        Iterator bands = listOfBands.iterator();
        assertTrue(bands.hasNext());

        Band bandtoverify = (Band) bands.next();

        Band band = new Band();
        band.setId(169L);
        band.setName("Eclipse");
        band.setGenre("Blackened Death");
        band.setThemes("Apocalypse, Horror, Fun");
        band.setLocation("Tübingen, Baden-Württemberg");
        band.setCountry("Germany");
        band.setLabel("Unsigned/independent");
        band.setStatus("Active");
        band.setformed(2008);

        assertEquals(band, bandtoverify);
    }

    @Test
    public void testBandReturnedByCountry()
    {
        List listOfBands = bandController.byCountry("gar").getBody();
        assert listOfBands != null;
        Iterator bands = listOfBands.iterator();
        assertTrue(bands.hasNext());

        Band bandtoverify = (Band) bands.next();

        Band band = new Band();
        band.setId(5L);
        band.setName("Dimholt");
        band.setGenre("Black");
        band.setThemes("Darkness, Life, Death");
        band.setLocation("Burgas, Burgas");
        band.setCountry("Bulgaria");
        band.setLabel("Unsigned/independent");
        band.setStatus("Active");
        band.setformed(2003);

        assertEquals(band, bandtoverify);
    }

    @Test
    public void testBandReturnedByLabel()
    {
        List listOfBands = bandController.byLabel("pand").getBody();
        assert listOfBands != null;
        Iterator bands = listOfBands.iterator();
        assertTrue(bands.hasNext());

        Band bandtoverify = (Band) bands.next();

        Band band = new Band();
        band.setId(2L);
        band.setName("S.U.T.U.R.E.");
        band.setGenre("Black/Crust");
        band.setThemes("Misanthropy");
        band.setLocation("Metz, Grand Est");
        band.setCountry("France");
        band.setLabel("Black Pandemie Production");
        band.setStatus("Active");
        band.setformed(2016);

        assertEquals(band, bandtoverify);
    }

    @Test
    public void testBandReturnedByStatus()
    {
        List listOfBands = bandController.byStatus("ho").getBody();
        assert listOfBands != null;
        Iterator bands = listOfBands.iterator();
        assertTrue(bands.hasNext());
        bands.next();
        assertTrue(bands.hasNext());
        bands.next();
        assertTrue(bands.hasNext());

        Band bandtoverify = (Band) bands.next();

        Band band = new Band();
        band.setId(35L);
        band.setName("Frostthrone");
        band.setGenre("Black");
        band.setThemes("Death, Winter, Satan, Mysticism");
        band.setLocation("Rainelle, West Virginia");
        band.setCountry("United States");
        band.setLabel("Unsigned/independent");
        band.setStatus("On hold");
        band.setformed(2006);

        assertEquals(band, bandtoverify);
    }

    @Test
    public void testBandReturnedByFormed()
    {
        List listOfBands = bandController.byFormed(1979).getBody();
        assert listOfBands != null;
        Iterator bands = listOfBands.iterator();
        assertTrue(bands.hasNext());

        Band bandtoverify = (Band) bands.next();

        Band band = new Band();
        band.setId(25L);
        band.setName("The Gentiles");
        band.setGenre("Heavy");
        band.setThemes("Christianity");
        band.setLocation("Salem, Oregon");
        band.setCountry("United States");
        band.setLabel("Unsigned/independent");
        band.setStatus("Changed name");
        band.setformed(1979);

        assertEquals(band, bandtoverify);
    }
}
