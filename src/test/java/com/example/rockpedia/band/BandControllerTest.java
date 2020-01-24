package com.example.rockpedia.band;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

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
    public void testDeleteBand()
    {
        ResponseEntity<String> firstResult = bandController.deleteBand(34L);
        assertEquals(HttpStatus.OK, firstResult.getStatusCode());
        ResponseEntity<Object> secondResult = bandController.byId(34L);
        assertEquals(HttpStatus.NO_CONTENT, secondResult.getStatusCode());
        assertEquals("{\n\t\"message\": \"There is no band by id 34\"\n" + "}", secondResult.getBody());
    }

    @Test
    @Order(3)
    public void testAddBand()
    {
        BandTemplate band = new BandTemplate();
        band.setName("Altkönig");
        band.setGenre("Black");
        band.setThemes("Nature, Paganism, Philosophy, War");
        band.setLocation("Frankfurt, Hesse");
        band.setCountry("Germany");
        band.setLabel("Unsigned/independent");
        band.setStatus("Split-up");
        band.setFormed(2008);

        bandController.newBand(band);

        Band bandToVerify = new Band(band);
        bandToVerify.setId(188L);

        assertEquals(bandToVerify, bandController.byId(188L).getBody());
    }

    @Test
    @Order(4)
    public void testUpdateBand()
    {
        BandTemplate band = new BandTemplate();
        band.setName("Altkönig");
        band.setGenre("Black");
        band.setThemes("Nature, Paganism, Philosophy, War");
        band.setLocation("Frankfurt, Hesse");
        band.setCountry("Germany");
        band.setLabel("Unsigned/independent");
        band.setStatus("Split-up");
        band.setFormed(2008);

        bandController.replaceBand(band, 120L);

        Band bandToVerify = new Band(band);
        bandToVerify.setId(120L);

        assertEquals(bandToVerify, bandController.byId(120L).getBody());
    }
}
