package com.example.rockpedia.band;

import com.example.rockpedia.band.Band;
import com.example.rockpedia.band.BandRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.swagger.annotations.Api;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/bands")
public class BandController {

    @Autowired
    private BandRepository bandRepository;

    public BandController() {
    }

    /**
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> Get(@PathVariable Long id){
        Band bandtoget = bandRepository.findById(id).isPresent() ? bandRepository.findById(id).get(): bandRepository.findById(id).get();
        JSONObject entity = bandtoget.toJSON();
        return new ResponseEntity<Object>(entity, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Object> Get(){
        Iterable<Band> listOfBands = bandRepository.findAll();
        List<JSONObject> entities = new ArrayList<JSONObject>();
        for (Band bandtoget : listOfBands)
            entities.add(bandtoget.toJSON());
        return new ResponseEntity<Object>(entities, HttpStatus.OK);
    }


    @PostMapping(path = "/add", consumes = "application/json")
    public Band newBand(@RequestBody Band band)
    {
        return bandRepository.save(band);
    }

    //@GetMapping("/bands/search")
    //@ResponseBody
    //public ResponseEntity<Object> getBandsBySearch(@RequestParam(value = "name") String name, @RequestParam(value = "members") String members, @RequestParam(value = "label") String label,
    //                                               @RequestParam(value="style") String style, @RequestParam(value="town") String town, @RequestParam(value="year") int year)
    //{
    //    Iterable<Band> listOfBands = bandRepository.findAll();
    //    List<JSONObject> entities = new ArrayList<JSONObject>();
    //    for (Band bandtoget : listOfBands)
    //        if(bandtoget.getName().contains(name) || bandtoget.getMembers().contains(members) || bandtoget.getLabel().contains(label)
    //                || bandtoget.getStyle().contains(style) || bandtoget.getTownoforigin().contains(town) || year == bandtoget.getYearofcreation())
    //            entities.add(bandtoget.toJSON());
    //    return new ResponseEntity<Object>(entities, HttpStatus.OK);
    //}

    /**
     * @param band
     * @param id
     * @return
     */
    @PutMapping(path = "/update/{id}", consumes = "application/json")
    public Band replaceBand(@RequestBody Band band, @PathVariable Long id)
    {
        band.setId(id);
        return bandRepository.save(band);
    }

    /**
     * @param id
     */
    @DeleteMapping(path = "/delete/{id}")
    public void deleteBand(@PathVariable Long id)
    {
        bandRepository.deleteById(id);
    }

    /**
     * @param name
     * @return
     */
    @GetMapping("/byName/{name}")
    public ResponseEntity<Object> byName(@PathVariable(value = "name") String name)
    {
        Band bandtoget = bandRepository.findBandByNameContainingIgnoreCase(name).isPresent() ? bandRepository.findBandByNameContainingIgnoreCase(name).get(): bandRepository.findBandByNameContainingIgnoreCase(name).get();
        JSONObject entity = bandtoget.toJSON();
        return new ResponseEntity<Object>(entity, HttpStatus.OK);
    }

    /**
     * @param member
     * @return
     */
    @GetMapping("/byMember/{member}")
    public ResponseEntity<Object> byMember(@PathVariable(value="member") String member)
    {
        Band bandtoget = bandRepository.findBandByMembersContainingIgnoreCase(member).isPresent() ? bandRepository.findBandByMembersContainingIgnoreCase(member).get(): bandRepository.findBandByMembersContainingIgnoreCase(member).get();
        JSONObject entity = bandtoget.toJSON();
        return new ResponseEntity<Object>(entity, HttpStatus.OK);
    }

    /**
     * @param label
     * @return
     */
    @GetMapping("/byLabel/{label}")
    public ResponseEntity<Object> byLabel(@PathVariable(value="label")String label)
    {
        Band bandtoget = bandRepository.findBandByLabelContainingIgnoreCase(label).isPresent() ? bandRepository.findBandByLabelContainingIgnoreCase(label).get(): bandRepository.findBandByLabelContainingIgnoreCase(label).get();
        JSONObject entity = bandtoget.toJSON();
        return new ResponseEntity<Object>(entity, HttpStatus.OK);
    }

    /*
     * @param town
     * @return
     */
    @GetMapping("/byTownOfOrigin/{town}")
    public ResponseEntity<Object> byTown(@PathVariable(value="town") String town)
    {
        Band bandtoget = bandRepository.findBandByTownoforiginContainingIgnoreCase(town).isPresent() ? bandRepository.findBandByTownoforiginContainingIgnoreCase(town).get(): bandRepository.findBandByTownoforiginContainingIgnoreCase(town).get();
        JSONObject entity = bandtoget.toJSON();
        return new ResponseEntity<Object>(entity, HttpStatus.OK);
    }

    /**
     * @param style
     * @return
     */
    @GetMapping("/byStyle/{style}")
    public ResponseEntity<Object> byStyle(@PathVariable(value="style") String style)
    {
        Band bandtoget = bandRepository.findBandByStyleContainingIgnoreCase(style).isPresent() ? bandRepository.findBandByStyleContainingIgnoreCase(style).get() : bandRepository.findBandByStyleContainingIgnoreCase(style).get();
        JSONObject entity = bandtoget.toJSON();
        return new ResponseEntity<Object>(entity, HttpStatus.OK);
    }
}
