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
import java.io.InputStream;

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

            ClassPathResource ressource = new ClassPathResource("static/bands.json");
            InputStream input = ressource.getInputStream();

            try (JsonReader jsonReader = Json.createReader(input);
                 Jsonb jsonb = JsonbBuilder.create()) {

                JsonArray bands = jsonReader.readArray();

                for (JsonValue value : bands) {
                    Band band = jsonb.fromJson(value.toString(), Band.class);
                    System.out.println(band);
                    bandRepository.save(band);
                }
            }
        }
    }
}
