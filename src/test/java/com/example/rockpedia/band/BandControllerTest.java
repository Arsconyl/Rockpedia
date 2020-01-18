package com.example.rockpedia.band;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BandControllerTest {

    @Autowired
    BandController bandController;

    @Test
    @Order(1)
    public void testBandReturnedById()
    {
        Band bandtoverify = (Band) bandController.byId(2L).getBody();

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
    @Order(2)
    public void testBandReturnedByName()
    {
        List listOfBands = (List) bandController.byName("sten").getBody();
        assert listOfBands != null;
        System.out.println(listOfBands);
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
        band.setFormed(2007);

        assertEquals(band, bandtoverify);
    }

    @Test
    @Order(3)
    public void testBandReturnedByGenre()
    {
        List listOfBands = (List) bandController.byGenre("sl").getBody();
        assert listOfBands != null;
        System.out.println(listOfBands);
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
        band.setFormed(2005);

        assertEquals(band, bandtoverify);
    }

    @Test
    @Order(4)
    public void testBandReturnedByTheme()
    {
        List listOfBands = (List) bandController.byTheme("xie").getBody();
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
        band.setFormed(2013);

//        assertEquals(band, bandtoverify);
    }

    @Test
    @Order(5)
    public void testBandReturnedByLocation()
    {
        List listOfBands = (List) bandController.byLocation("ingen").getBody();
        assert listOfBands != null;
        System.out.println(listOfBands);
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
        band.setFormed(2008);

        assertEquals(band, bandtoverify);
    }

    @Test
    @Order(6)
    public void testBandReturnedByCountry()
    {
        List listOfBands = (List) bandController.byCountry("gar").getBody();
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
        band.setFormed(2003);

        assertEquals(band, bandtoverify);
    }

    @Test
    @Order(7)
    public void testBandReturnedByLabel()
    {
        List listOfBands = (List) bandController.byLabel("pand").getBody();
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
        band.setFormed(2016);

        assertEquals(band, bandtoverify);
    }

    @Test
    @Order(8)
    public void testBandReturnedByStatus()
    {
        List listOfBands = (List) bandController.byStatus("ho").getBody();
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
        band.setFormed(2006);

        assertEquals(band, bandtoverify);
    }

    @Test
    @Order(9)
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
        band.setFormed(1979);

        assertEquals(band, bandtoverify);
    }

    @Test
    @Order(10)
    public void testBandReturnedBySearch()
    {
        List listOfBands = (List) bandController.getBandsBySearch("met").getBody();
        assert listOfBands != null;
        System.out.println(listOfBands);

        assertEquals(8, listOfBands.size());

        Band bandToVerify1 = (Band) listOfBands.get(1);
        Band bandToVerify2 = (Band) listOfBands.get(7);

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
    @Order(11)
    public void testBandAdvancedSearch()
    {
        List list1 = (List) bandController.getBandsBySearch("profeta").getBody();
        List list2 = (List) bandController.getBandsBySearch("essencia").getBody();
        List list3 = (List) bandController.getBandsBySearch("altkonig").getBody();

        assert list1 != null;
        Iterator bands1 = list1.iterator();
        assertTrue(bands1.hasNext());

        Band bandtoverify = (Band) bands1.next();

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
        Iterator bands2 = list2.iterator();
        assertTrue(bands2.hasNext());

        bandtoverify = (Band) bands2.next();

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
        Iterator bands3 = list3.iterator();
        assertTrue(bands3.hasNext());

        bandtoverify = (Band) bands3.next();

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

    @Test
    @Order(12)
    public void testDeleteBand()
    {
        ResponseEntity<String> firstResult = bandController.deleteBand(34L);
        assertEquals(HttpStatus.OK, firstResult.getStatusCode());
        ResponseEntity<Object> secondResult = bandController.byId(34L);
        assertEquals(HttpStatus.NO_CONTENT, secondResult.getStatusCode());
        assertEquals("{\n\t\"message\": \"There is no band by id 34\"\n" + "}", secondResult.getBody());
    }
//
//    @Test
//    @Order(13)
//    public void testAddBand()
//    {
//        Band band = new Band();
//        band.setName("Altkönig");
//        band.setGenre("Black");
//        band.setThemes("Nature, Paganism, Philosophy, War");
//        band.setLocation("Frankfurt, Hesse");
//        band.setCountry("Germany");
//        band.setLabel("Unsigned/independent");
//        band.setStatus("Split-up");
//        band.setFormed(2008);
//
//        bandController.newBand(band);
//
//        assertEquals(band, bandController.byId(188L).getBody());
//    }
//
//    @Test
//    @Order(2)
//    public void testUpdateBand()
//    {
//        Band band = new Band();
//        band.setName("Altkönig");
//        band.setGenre("Black");
//        band.setThemes("Nature, Paganism, Philosophy, War");
//        band.setLocation("Frankfurt, Hesse");
//        band.setCountry("Germany");
//        band.setLabel("Unsigned/independent");
//        band.setStatus("Split-up");
//        band.setFormed(2008);
//
//        bandController.replaceBand(band, 120L);
//
//        assertEquals(band, bandController.byId(120L).getBody());
//    }
}
