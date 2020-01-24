package com.example.rockpedia.band;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BandExcelServiceTest {

    @Autowired
    BandRESTService bandRESTService;

    @Autowired
    BandExcelService bandExcelService;

    @Test
    public void exportCSVTest() throws IOException {
        List<Band> bands = bandRESTService.getAll();
        bandExcelService.exportCSV(bands);

        File csv = new File("bands.csv");
        try(BufferedReader CSVReader = new BufferedReader(new FileReader(csv)))
        {
            assertEquals("ID,NAME,GENRE,THEMES,LOCATION,COUNTRY,LABEL,STATUS,DATE OF FORMATION", CSVReader.readLine());
            assertEquals("1,\"Metallica\",\"Thrash (early), Hard Rock (mid), Heavy/Thrash (later)\",\"Corruption, Death, Life, Internal struggles, Anger\",\"Los Angeles/San Francisco, California\",\"United States\",\"Blackened Recordings\",\"Active\",1981", CSVReader.readLine());
        }
    }
}
