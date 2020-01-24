package com.example.rockpedia.band;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BandRESTServiceTest {

    @Autowired
    BandRESTService bandRESTService;

    @Test
    @Order(1)
    public void testBandReturnedByName() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        List<Band> listOfBands = bandRESTService.searchBandName("sten");
        assert listOfBands != null;
        System.out.println(listOfBands);
        Iterator<Band> bands = listOfBands.iterator();
        assertTrue(bands.hasNext());

        Band bandtoverify = bands.next();

        Band band = new Band();
        band.setId(135L);
        band.setName("Fragile Existence");
        band.setGenre("Death");
        band.setThemes("Death, Gore");
        band.setLocation("Toronto, Ontario");
        band.setCountry("Canada");
        band.setLabel("Unsigned/independent");
        band.setStatus("Active");
        band.setFormed(2007);

        assertEquals(band, bandtoverify);
    }

    @Test
    @Order(3)
    public void testBandReturnedByGenre() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        List<Band> listOfBands = bandRESTService.searchBandGenre("sl");
        assert listOfBands != null;
        System.out.println(listOfBands);
        Iterator<Band> bands = listOfBands.iterator();
        assertTrue(bands.hasNext());

        Band bandtoverify = bands.next();

        Band band = new Band();
        band.setId(49L);
        band.setName("Kratic");
        band.setGenre("Sludge");
        band.setThemes("Life, Humanity");
        band.setLocation("Oslo/Bærum");
        band.setCountry("Norway");
        band.setLabel("Unsigned/independent");
        band.setStatus("Split-up");
        band.setFormed(2005);

        assertEquals(band, bandtoverify);
    }

    @Test
    @Order(2)
    public void testBandReturnedByTheme() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        List<Band> listOfBands = bandRESTService.searchBandTheme("xie");
        assert listOfBands != null;
        Iterator<Band> bands = listOfBands.iterator();
        assertTrue(bands.hasNext());

        Band bandtoverify = bands.next();

        Band band = new Band();
        band.setId(52L);
        band.setName("11th Dimension");
        band.setGenre("Progressive");
        band.setThemes("Isolation, Society, Anxiety, Hope, Self-Discovery");
        band.setLocation("Lisbon, Lisbon");
        band.setCountry("Portugal");
        band.setLabel("Unsigned/independent");
        band.setStatus("Active");
        band.setFormed(2013);

        assertEquals(band, bandtoverify);
    }

    @Test
    @Order(3)
    public void testBandReturnedByLocation() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        List<Band> listOfBands = bandRESTService.searchBandLocation("ingen");
        assert listOfBands != null;
        System.out.println(listOfBands);
        Iterator<Band> bands = listOfBands.iterator();
        assertTrue(bands.hasNext());

        Band bandtoverify = bands.next();

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

        assertEquals(band, bandtoverify);
    }

    @Test
    @Order(4)
    public void testBandReturnedByCountry() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        List<Band> listOfBands = bandRESTService.searchBandCountry("gar");
        assert listOfBands != null;
        Iterator<Band> bands = listOfBands.iterator();
        assertTrue(bands.hasNext());

        Band bandtoverify = bands.next();

        Band band = new Band();
        band.setId(5L);
        band.setName("Dimholt");
        band.setGenre("Black");
        band.setThemes("Darkness, Life, Death");
        band.setLocation("Burgas, Burgas");
        band.setCountry("Bulgaria");
        band.setLabel("Unsigned/independent");
        band.setStatus("Active");
        band.setFormed(2003);

        assertEquals(band, bandtoverify);
    }

    @Test
    @Order(5)
    public void testBandReturnedByLabel() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        List<Band> listOfBands = bandRESTService.searchBandLabel("pand");
        assert listOfBands != null;
        Iterator<Band> bands = listOfBands.iterator();
        assertTrue(bands.hasNext());

        Band bandtoverify = bands.next();

        Band band = new Band();
        band.setId(2L);
        band.setName("S.U.T.U.R.E.");
        band.setGenre("Black/Crust");
        band.setThemes("Misanthropy");
        band.setLocation("Metz, Grand Est");
        band.setCountry("France");
        band.setLabel("Black Pandemie Production");
        band.setStatus("Active");
        band.setFormed(2016);

        assertEquals(band, bandtoverify);
    }

    @Test
    @Order(6)
    public void testBandReturnedByStatus() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        List<Band> listOfBands = bandRESTService.searchBandStatus("ho");
        assert listOfBands != null;
        Iterator<Band> bands = listOfBands.iterator();
        assertTrue(bands.hasNext());
        bands.next();
        assertTrue(bands.hasNext());
        bands.next();
        assertTrue(bands.hasNext());

        Band bandtoverify = bands.next();

        Band band = new Band();
        band.setId(35L);
        band.setName("Frostthrone");
        band.setGenre("Black");
        band.setThemes("Death, Winter, Satan, Mysticism");
        band.setLocation("Rainelle, West Virginia");
        band.setCountry("United States");
        band.setLabel("Unsigned/independent");
        band.setStatus("On hold");
        band.setFormed(2006);

        assertEquals(band, bandtoverify);
    }

    @Test
    @Order(7)
    public void testBandReturnedByFormed()
    {
        List<Band> listOfBands = bandRESTService.searchBandFormed(1979);
        assert listOfBands != null;
        Iterator<Band> bands = listOfBands.iterator();
        assertTrue(bands.hasNext());

        Band bandtoverify = bands.next();

        Band band = new Band();
        band.setId(25L);
        band.setName("The Gentiles");
        band.setGenre("Heavy");
        band.setThemes("Christianity");
        band.setLocation("Salem, Oregon");
        band.setCountry("United States");
        band.setLabel("Unsigned/independent");
        band.setStatus("Changed name");
        band.setFormed(1979);

        assertEquals(band, bandtoverify);
    }

}
