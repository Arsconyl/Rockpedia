package com.example.rockpedia.band;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
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
    public ResponseEntity<Object> byName(@PathVariable(value = "name") String name)
    {
        List<Band> bands;
        try {
            bands = bandService.searchBandName(name);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(bands, HttpStatus.OK);
    }

    @GetMapping("/byGenre/{genre}")
    public ResponseEntity<Object> byGenre(@PathVariable(value="genre")String genre)
    {
        List<Band> bands;
        try {
            bands = bandService.searchBandGenre(genre);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(bands, HttpStatus.OK);
    }

    @GetMapping("/byTheme/{theme}")
    public ResponseEntity<Object> byTheme(@PathVariable(value="theme")String theme)
    {
        List<Band> bands;
        try {
            bands = bandService.searchBandTheme(theme);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(bands, HttpStatus.OK);
    }

    @GetMapping("/byLocation/{location}")
    public ResponseEntity<Object> byLocation(@PathVariable(value="location")String location)
    {
        List<Band> bands;
        try {
            bands = bandService.searchBandLocation(location);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(bands, HttpStatus.OK);
    }
    @GetMapping("/byCountry/{country}")
    public ResponseEntity<Object> byCountry(@PathVariable(value="country")String country)
    {
        List<Band> bands;
        try {
            bands = bandService.searchBandCountry(country);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(bands, HttpStatus.OK);
    }

    @GetMapping("/byLabel/{label}")
    public ResponseEntity<Object> byLabel(@PathVariable(value="label")String label)
    {
        List<Band> bands;
        try {
            bands = bandService.searchBandLabel(label);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(bands, HttpStatus.OK);
    }

    @GetMapping("/byStatus/{status}")
    public ResponseEntity<Object> byStatus(@PathVariable(value="status")String status)
    {
        List<Band> bands;
        try {
            bands = bandService.searchBandStatus(status);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(bands, HttpStatus.OK);
    }

    @GetMapping("/byFormed/{formed}")
    public ResponseEntity<List> byFormed(@PathVariable(value="formed")int formed)
    {
        List<Band> bands = bandService.searchBandFormed(formed);
        return new ResponseEntity<>(bands, HttpStatus.OK);
    }

//    @GetMapping("/search")
//    public ResponseEntity<Object> getBandsBySearch(@RequestParam(value = "q") String query)
//    {
//        List<Band> bands = null;
//        try {
//            bands = bandService.searchAdvanced(query);
//        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//        return new ResponseEntity<>(bands, HttpStatus.OK);
//    }


}
