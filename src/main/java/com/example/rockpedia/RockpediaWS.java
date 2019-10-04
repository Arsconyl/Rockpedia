package com.example.rockpedia;

import com.example.rockpedia.dao.BandDAO;
import com.example.rockpedia.entity.Band;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/bands")
public class RockpediaWS {

    @Autowired
    private BandDAO bandDAO;

    public RockpediaWS() {
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> Get(@PathVariable Long id){
        Band bandtoget = bandDAO.findById(id).isPresent() ? bandDAO.findById(id).get(): bandDAO.findById(id).get();
        JSONObject entity = bandtoget.toJSON();
        return new ResponseEntity<Object>(entity, HttpStatus.OK);
    }

    @RequestMapping("/")
    public ResponseEntity<Object> Get(){
        Iterable<Band> listOfBands = bandDAO.findAll();
        List<JSONObject> entities = new ArrayList<JSONObject>();
        for (Band bandtoget : listOfBands)
            entities.add(bandtoget.toJSON());
        return new ResponseEntity<Object>(entities, HttpStatus.OK);
    }


    @PostMapping(path = "/add", consumes = "application/json")
    public Band newBand(@RequestBody Band band)
    {
        return bandDAO.save(band);
    }

    //@GetMapping("/bands/search")
    //@ResponseBody
    //public ResponseEntity<Object> getBandsBySearch(@RequestParam(value = "name") String name, @RequestParam(value = "members") String members, @RequestParam(value = "label") String label,
    //                                               @RequestParam(value="style") String style, @RequestParam(value="town") String town, @RequestParam(value="year") int year)
    //{
    //    Iterable<Band> listOfBands = bandDAO.findAll();
    //    List<JSONObject> entities = new ArrayList<JSONObject>();
    //    for (Band bandtoget : listOfBands)
    //        if(bandtoget.getName().contains(name) || bandtoget.getMembers().contains(members) || bandtoget.getLabel().contains(label)
    //                || bandtoget.getStyle().contains(style) || bandtoget.getTownoforigin().contains(town) || year == bandtoget.getYearofcreation())
    //            entities.add(bandtoget.toJSON());
    //    return new ResponseEntity<Object>(entities, HttpStatus.OK);
    //}

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

    @RequestMapping("/byName/{name}")
    public ResponseEntity<Object> byName(@PathVariable(value = "name") String name)
    {
        Band bandtoget = bandDAO.findBandByNameContainingIgnoreCase(name).isPresent() ? bandDAO.findBandByNameContainingIgnoreCase(name).get(): bandDAO.findBandByNameContainingIgnoreCase(name).get();
        JSONObject entity = bandtoget.toJSON();
        return new ResponseEntity<Object>(entity, HttpStatus.OK);
    }

    @RequestMapping("/byMember/{member}")
    public ResponseEntity<Object> byMember(@PathVariable(value="member") String member)
    {
        Band bandtoget = bandDAO.findBandByMembersContainingIgnoreCase(member).isPresent() ? bandDAO.findBandByMembersContainingIgnoreCase(member).get(): bandDAO.findBandByMembersContainingIgnoreCase(member).get();
        JSONObject entity = bandtoget.toJSON();
        return new ResponseEntity<Object>(entity, HttpStatus.OK);
    }

    @RequestMapping("/byLabel/{label}")
    public ResponseEntity<Object> byLabel(@PathVariable(value="label")String label)
    {
        Band bandtoget = bandDAO.findBandByLabelContainingIgnoreCase(label).isPresent() ? bandDAO.findBandByLabelContainingIgnoreCase(label).get(): bandDAO.findBandByLabelContainingIgnoreCase(label).get();
        JSONObject entity = bandtoget.toJSON();
        return new ResponseEntity<Object>(entity, HttpStatus.OK);
    }

    @RequestMapping("/byTownOfOrigin/{town}")
    public ResponseEntity<Object> byTown(@PathVariable(value="town") String town)
    {
        Band bandtoget = bandDAO.findBandByTownoforiginContainingIgnoreCase(town).isPresent() ? bandDAO.findBandByTownoforiginContainingIgnoreCase(town).get(): bandDAO.findBandByTownoforiginContainingIgnoreCase(town).get();
        JSONObject entity = bandtoget.toJSON();
        return new ResponseEntity<Object>(entity, HttpStatus.OK);
    }

    @RequestMapping("/byStyle/{style}")
    public ResponseEntity<Object> byStyle(@PathVariable(value="style") String style)
    {
        Band bandtoget = bandDAO.findBandByStyleContainingIgnoreCase(style).isPresent() ? bandDAO.findBandByStyleContainingIgnoreCase(style).get() : bandDAO.findBandByStyleContainingIgnoreCase(style).get();
        JSONObject entity = bandtoget.toJSON();
        return new ResponseEntity<Object>(entity, HttpStatus.OK);
    }
}
