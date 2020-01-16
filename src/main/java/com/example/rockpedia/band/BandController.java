package com.example.rockpedia.band;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/bands")
public class BandController {

    private final BandService bandService;

    public BandController(BandService bandService) {
        this.bandService = bandService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Band> byId(@PathVariable Long id){
        return new ResponseEntity<>(bandService.byId(id), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List> getAll(){
        return new ResponseEntity<>(bandService.getAll(), HttpStatus.OK);
    }

    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> newBand(@RequestBody Band band)
    {
        try {
            return new ResponseEntity<>(bandService.add(band), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/update/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> replaceBand(@RequestBody Band band, @PathVariable Long id)
    {
        try {
            return new ResponseEntity<>(bandService.insert(id, band), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/delete/{id}", produces = "application/json")
    public ResponseEntity<String> deleteBand(@PathVariable Long id)
    {
        String result;
        try {
            result = bandService.deleteById(id);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/byName/{name}")
    public ResponseEntity<List> byName(@PathVariable(value = "name") String name)
    {
        List<Band> bands = bandService.searchBandName(name);
        return new ResponseEntity<>(bands, HttpStatus.OK);
    }

    @GetMapping("/byGenre/{genre}")
    public ResponseEntity<List> byGenre(@PathVariable(value="genre")String genre)
    {

        List<Band> bands = bandService.searchBandGenre(genre);
        return new ResponseEntity<>(bands, HttpStatus.OK);
    }

    @GetMapping("/byTheme/{theme}")
    public ResponseEntity<List> byTheme(@PathVariable(value="theme")String theme)
    {
        List<Band> bands = bandService.searchBandTheme(theme);
        return new ResponseEntity<>(bands, HttpStatus.OK);
    }

    @GetMapping("/byLocation/{location}")
    public ResponseEntity<List> byLocation(@PathVariable(value="location")String location)
    {
        List<Band> bands = bandService.searchBandLocation(location);
        return new ResponseEntity<>(bands, HttpStatus.OK);
    }
    @GetMapping("/byCountry/{country}")
    public ResponseEntity<List> byCountry(@PathVariable(value="country")String country)
    {
        List<Band> bands = bandService.searchBandCountry(country);
        return new ResponseEntity<>(bands, HttpStatus.OK);
    }

    @GetMapping("/byLabel/{label}")
    public ResponseEntity<List> byLabel(@PathVariable(value="label")String label)
    {
        List<Band> bands = bandService.searchBandLabel(label);
        return new ResponseEntity<>(bands, HttpStatus.OK);
    }

    @GetMapping("/byStatus/{status}")
    public ResponseEntity<List> byStatus(@PathVariable(value="status")String status)
    {
        List<Band> bands = bandService.searchBandStatus(status);
        return new ResponseEntity<>(bands, HttpStatus.OK);
    }

    @GetMapping("/byFormed/{formed}")
    public ResponseEntity<List> byFormed(@PathVariable(value="formed")int formed)
    {
        List<Band> bands = bandService.searchBandFormed(formed);
        return new ResponseEntity<>(bands, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List> getBandsBySearch(@RequestParam(value = "q") String query)
    {
        List<Band> bands = bandService.searchAdvanced(query);
        return new ResponseEntity<>(bands, HttpStatus.OK);
    }


}
