package com.example.rockpedia.band;

import org.springframework.beans.factory.annotation.Autowired;

import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.ArrayList;

@RestController
@RequestMapping("/bands")
public class BandController {

    @Autowired
    private BandRepository bandRepository;

    public BandController() {
    }

    @GetMapping("/{id}")
    public ResponseEntity<Band> Get(@PathVariable Long id){
        Band bandtoget = bandRepository.findById(id).isPresent() ? bandRepository.findById(id).get(): bandRepository.findById(id).get();
        return new ResponseEntity<Band>(bandtoget, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List> Get(){
        Iterable<Band> listOfBands = bandRepository.findAll();
        List<Band> bands = iterableToList(listOfBands);
        return new ResponseEntity<List>(bands, HttpStatus.OK);
    }

    @PostMapping(path = "/add", consumes = "application/json")
    public Band newBand(@RequestBody Band band)
    {
        return bandRepository.save(band);
    }

    @PutMapping(path = "/update/{id}", consumes = "application/json")
    public Band replaceBand(@RequestBody Band band, @PathVariable Long id)
    {
        band.setId(id);
        return bandRepository.save(band);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteBand(@PathVariable Long id)
    {
        bandRepository.deleteById(id);
    }

    @GetMapping("/byName/{name}")
    public ResponseEntity<List> byName(@PathVariable(value = "name") String name)
    {
        Optional<Iterable<Band>> listOfBands = bandRepository.findAllByNameContainingIgnoreCase(name);
        List<Band> bands = new ArrayList<>();
        if(listOfBands.isPresent())
            bands = iterableToList(listOfBands.get());
        return new ResponseEntity<List>(bands, HttpStatus.OK);
    }

    @GetMapping("/byGenre/{genre}")
    public ResponseEntity<List> byGenre(@PathVariable(value="genre")String label)
    {
        Optional<Iterable<Band>> listOfBands = bandRepository.findAllByGenreContainingIgnoreCase(label);
        List<Band> bands = new ArrayList<>();
        if(listOfBands.isPresent())
            bands = iterableToList(listOfBands.get());
        return new ResponseEntity<List>(bands, HttpStatus.OK);
    }

    @GetMapping("/byTheme/{theme}")
    public ResponseEntity<List> byTheme(@PathVariable(value="theme")String label)
    {
        Optional<Iterable<Band>> listOfBands = bandRepository.findAllByThemesContainingIgnoreCase(label);
        List<Band> bands = new ArrayList<>();
        if(listOfBands.isPresent())
            bands = iterableToList(listOfBands.get());
        return new ResponseEntity<List>(bands, HttpStatus.OK);
    }

    @GetMapping("/byLocation/{location}")
    public ResponseEntity<List> byLocation(@PathVariable(value="location")String label)
    {
        Optional<Iterable<Band>> listOfBands = bandRepository.findAllByLocationContainingIgnoreCase(label);
        List<Band> bands = new ArrayList<>();
        if(listOfBands.isPresent())
            bands = iterableToList(listOfBands.get());
        return new ResponseEntity<List>(bands, HttpStatus.OK);
    }
    @GetMapping("/byCountry/{country}")
    public ResponseEntity<List> byCountry(@PathVariable(value="country")String label)
    {
        Optional<Iterable<Band>> listOfBands = bandRepository.findAllByCountryContainingIgnoreCase(label);
        List<Band> bands = new ArrayList<>();
        if(listOfBands.isPresent())
            bands = iterableToList(listOfBands.get());
        return new ResponseEntity<List>(bands, HttpStatus.OK);
    }

    @GetMapping("/byLabel/{label}")
    public ResponseEntity<List> byLabel(@PathVariable(value="label")String label)
    {
        Optional<Iterable<Band>> listOfBands = bandRepository.findAllByLabelContainingIgnoreCase(label);
        List<Band> bands = new ArrayList<>();
        if(listOfBands.isPresent())
            bands = iterableToList(listOfBands.get());
        return new ResponseEntity<List>(bands, HttpStatus.OK);
    }

    @GetMapping("/byStatus/{status}")
    public ResponseEntity<List> byStatus(@PathVariable(value="status")String label)
    {
        Optional<Iterable<Band>> listOfBands = bandRepository.findAllByStatusContainingIgnoreCase(label);
        List<Band> bands = new ArrayList<>();
        if(listOfBands.isPresent())
            bands = iterableToList(listOfBands.get());
        return new ResponseEntity<List>(bands, HttpStatus.OK);
    }

    @GetMapping("/byFormed/{formed}")
    public ResponseEntity<List> byFormed(@PathVariable(value="formed")int formed)
    {
        Optional<Iterable<Band>> listOfBands = bandRepository.findAllByFormed(formed);
        List<Band> bands = new ArrayList<>();
        if(listOfBands.isPresent())
            bands = iterableToList(listOfBands.get());
        return new ResponseEntity<List>(bands, HttpStatus.OK);
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

    private List<Band> iterableToList(Iterable<Band> listOfBands)
    {
        List<Band> bands = new ArrayList<>();
        for (Band band : listOfBands) {
            bands.add(band);
        }
        return bands;
    }
}
