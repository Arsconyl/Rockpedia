package com.example.rockpedia.band;

import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Service
public class BandExcelService {
    private static final String FILENAME = "bands.csv";

    public InputStreamResource exportCSV(List<Band> bands) throws IOException {
        try (Writer excelWriter = new FileWriter(FILENAME)) {
            excelWriter.write("ID,NAME,GENRE,THEMES,LOCATION,COUNTRY,LABEL,STATUS,DATE OF FORMATION\n");
            for (Band band : bands)
            {
                excelWriter.write(band.toCSV() + "\n");
            }
        }

        return new InputStreamResource(new FileInputStream(FILENAME));
    }
}


