package com.example.rockpedia;

import com.example.rockpedia.dao.BandDAO;
import com.example.rockpedia.entity.Band;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
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
    public ResponseEntity<Object> Get(@PathVariable Long id){
        Band bandtoget = bandDAO.findById(id).get();
        JSONObject entity = new JSONObject();
        entity.put("id", bandtoget.getId());
        entity.put("Name", bandtoget.getName());
        entity.put("members", bandtoget.getMembers());
        entity.put("style", bandtoget.getStyle());
        entity.put("members", bandtoget.getMembers());
        entity.put("yearofcreation", bandtoget.getYearofcreation());
        entity.put("townoforigin", bandtoget.getTownoforigin());
        entity.put("label", bandtoget.getLabel());
        return new ResponseEntity<Object>(entity, HttpStatus.OK);
    }

    @GetMapping("/bands")
    public ResponseEntity<Object> Get(){
        Iterable<Band> listOfBands = bandDAO.findAll();

        List<JSONObject> entities = new ArrayList<JSONObject>();
        for (Band bandtoget : listOfBands) {
            JSONObject entity = new JSONObject();
            entity.put("id", bandtoget.getId());
            entity.put("Name", bandtoget.getName());
            entity.put("members", bandtoget.getMembers());
            entity.put("style", bandtoget.getStyle());
            entity.put("members", bandtoget.getMembers());
            entity.put("yearofcreation", bandtoget.getYearofcreation());
            entity.put("townoforigin", bandtoget.getTownoforigin());
            entity.put("label", bandtoget.getLabel());
            entities.add(entity);
        }
        return new ResponseEntity<Object>(entities, HttpStatus.OK);
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
