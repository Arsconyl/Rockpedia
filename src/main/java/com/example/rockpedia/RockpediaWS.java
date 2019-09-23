package com.example.rockpedia;

import com.example.rockpedia.dao.BandDAO;
import com.example.rockpedia.entity.Band;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class RockpediaWS {

    @Autowired
    private BandDAO bandDAO;

    public RockpediaWS() {
    }

    @GetMapping("/bands/{id}")
    public ResponseEntity<Info> Get(@PathVariable Long id){
        Band bandtoget = bandDAO.findById(id).get();
        Info.Builder builder = new Info.Builder();
        builder.withDetail("name", bandtoget.getName());
        builder.withDetail("members", bandtoget.getMembers());
        builder.withDetail("style", bandtoget.getStyle());
        builder.withDetail("members", bandtoget.getMembers());
        builder.withDetail("yearofcreation", bandtoget.getYearofcreation());
        builder.withDetail("townoforigin", bandtoget.getTownoforigin());
        builder.withDetail("label", bandtoget.getLabel());
        final Info bandJSON = builder.build();
        return ResponseEntity.ok(bandJSON);
    }

    @GetMapping("/bands")
    public ResponseEntity<Info> Get(){
        Iterable<Band> listOfBands = bandDAO.findAll();
        Info.Builder builder = new Info.Builder();
        for(Band bandtoget: listOfBands)
        {
            builder.withDetail("Name", bandtoget.getName());
            builder.withDetail("members", bandtoget.getMembers());
            builder.withDetail("style", bandtoget.getStyle());
            builder.withDetail("members", bandtoget.getMembers());
            builder.withDetail("yearofcreation", bandtoget.getYearofcreation());
            builder.withDetail("townoforigin", bandtoget.getTownoforigin());
            builder.withDetail("label", bandtoget.getLabel());
        }
        final Info bandJSON = builder.build();
        return ResponseEntity.ok(bandJSON);
    }

    @PostMapping(path = "/addband", consumes = "application/json")
    public Band newBand(@RequestBody Band band)
    {
        return bandDAO.save(band);
    }

    @PutMapping(path = "/update/{id}", consumes = "application/json")
    public Band replaceBand(@RequestBody Band band, @PathVariable Long id)
    {
        band.setId(id);
        return bandDAO.save(band);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteBand(@PathVariable Long id)
    {
        bandDAO.deleteById(id);
    }
}
