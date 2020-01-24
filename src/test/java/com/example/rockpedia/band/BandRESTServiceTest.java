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

    @Test
    @Order(8)
    public void testBandReturnedBySearch() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        List<Band> listOfBands = bandRESTService.searchBand("met");
        assert listOfBands != null;
        System.out.println(listOfBands);

        assertEquals(8, listOfBands.size());

        Band bandToVerify1 = listOfBands.get(1);
        Band bandToVerify2 = listOfBands.get(7);

        System.out.println(bandToVerify1);
        System.out.println(bandToVerify2);

        Band band = new Band();
        band.setId(16L);
        band.setName("Metaphoris");
        band.setGenre("Thrash/Death");
        band.setThemes("Hate, Chaos");
        band.setLocation("Vantaa");
        band.setCountry("Finland");
        band.setLabel("Unsigned/independent");
        band.setStatus("Changed name");
        band.setFormed(2007);

        assertEquals(band, bandToVerify1);

        band = new Band();
        band.setId(2L);
        band.setName("S.U.T.U.R.E.");
        band.setGenre("Black/Crust");
        band.setThemes("Misanthropy");
        band.setLocation("Metz, Grand Est");
        band.setCountry("France");
        band.setLabel("Black Pandemie Production");
        band.setStatus("Active");
        band.setFormed(2016);

        assertEquals(band, bandToVerify2);
    }

    @Test
    @Order(9)
    public void testBandAdvancedSearch() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        List<Band> list1 = bandRESTService.searchBand("profeta");
        List<Band> list2 = bandRESTService.searchBand("essencia");
        List<Band> list3 = bandRESTService.searchBand("altkonig");

        assert list1 != null;
        Iterator<Band> bands1 = list1.iterator();
        assertTrue(bands1.hasNext());

        Band bandtoverify = bands1.next();

        Band band = new Band();
        band.setId(7L);
        band.setName("Corazón Profeta");
        band.setGenre("Heavy");
        band.setThemes("Personal and social issues, Heavy");
        band.setLocation("Necochea, Buenos Aires");
        band.setCountry("Argentina");
        band.setLabel("Unsigned/independent");
        band.setStatus("Active");
        band.setFormed(2000);

        assertEquals(band, bandtoverify);

        assert list2 != null;
        Iterator<Band> bands2 = list2.iterator();
        assertTrue(bands2.hasNext());

        bandtoverify = bands2.next();

        band = new Band();
        band.setId(12L);
        band.setName("Essência Insana");
        band.setGenre("Thrash/Black");
        band.setThemes("Social Criticism, Carnage, Misanthropy");
        band.setLocation("Campos Sales, Ceará");
        band.setCountry("Brazil");
        band.setLabel("Unsigned/independent");
        band.setStatus("Active");
        band.setFormed(2012);

        assertEquals(band, bandtoverify);

        assert list3 != null;
        Iterator<Band> bands3 = list3.iterator();
        assertTrue(bands3.hasNext());

        bandtoverify = bands3.next();

        band = new Band();
        band.setId(104L);
        band.setName("Altkönig");
        band.setGenre("Black");
        band.setThemes("Nature, Paganism, Philosophy, War");
        band.setLocation("Frankfurt, Hesse");
        band.setCountry("Germany");
        band.setLabel("Unsigned/independent");
        band.setStatus("Split-up");
        band.setFormed(2008);

        assertEquals(band, bandtoverify);
    }

}
