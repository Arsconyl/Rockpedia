package com.example.rockpedia.band;

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
        ResponseEntity<Band> band = bandController.byId(2L);
        assertEquals("Band{id=2, name='S.U.T.U.R.E.', genre='Black Metal/Crust', themes='Misanthropy', location='Metz, Grand Est', country='France', label='Black Pandemie Production', status='Active', formed=2016}", Objects.requireNonNull(band.getBody()).toString());
        ResponseEntity<List> listOfBands = bandController.byFormed(1979);
        Iterator bands = Objects.requireNonNull(listOfBands.getBody()).iterator();
        assertTrue(bands.hasNext());
        assertEquals("Band{id=25, name='The Gentiles', genre='Heavy Metal', themes='Christianity', location='Salem, Oregon', country='United States', label='Unsigned/independent', status='Changed name', formed=1979}", bands.next().toString());
    }
}
