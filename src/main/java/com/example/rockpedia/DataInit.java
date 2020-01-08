package com.example.rockpedia;

import com.example.rockpedia.band.BandRepository;
import com.example.rockpedia.band.Band;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

@Component
public class DataInit implements ApplicationRunner {

    private BandRepository bandRepository;

    @Autowired
    public DataInit(BandRepository bandRepository) {
        this.bandRepository = bandRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        long count = bandRepository.count();
        if(count == 0)
        {
//            Band b1 = new Band();
//            Band b2 = new Band();
//
//            b1.setName("Rammstein");
//            b1.setLabel("Universal Music Group");
//            b1.setStyle("Industrial German Metal");
//            b1.setYearofcreation(1994);
//            b1.setTownoforigin("Berlin");
//
//            b2.setName("The Beatles");
//            b2.setLabel("Apple Records");
//            b2.setMembers("Paul McCartney, John Lennon, Ringo Star, George Harrisson");
//            b2.setStyle("Rock");
//            b2.setYearofcreation(1960);
//            b2.setTownoforigin("Liverpool");
//
//            bandRepository.save(b1);
//            bandRepository.save(b2);

            ClassPathResource ressource = new ClassPathResource("static/bands.json");
            InputStream input = ressource.getInputStream();

            // Create JsonReader object
            JsonReader jsonReader = Json.createReader(input);

            Jsonb jsonb = JsonbBuilder.create();

            // Get JsonObject (root object).
            JsonArray bands = jsonReader.readArray();

            for(JsonValue value : bands)
            {
                Band band = jsonb.fromJson(value.toString(), Band.class);
                System.out.println(band);
                bandRepository.save(band);
            }
        }
    }
}
