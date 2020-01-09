package com.example.rockpedia.band;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.example.rockpedia.Tools.iterableToList;

@RestController
@RequestMapping("/bands")
public class BandController {

    private final BandRepository bandRepository;

    private final BandService bandService;

    public BandController(BandRepository bandRepository, BandService bandService) {
        this.bandRepository = bandRepository;
        this.bandService = bandService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Band> byId(@PathVariable Long id){
        Optional<Band> bandtoget = bandRepository.findById(id);
        Band band = new Band();
        if(bandtoget.isPresent())
           band = bandtoget.get();
        return new ResponseEntity<>(band, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List> getAll(){
        Iterable<Band> listOfBands = bandRepository.findAll();
        List<Band> bands = iterableToList(listOfBands);
        return new ResponseEntity<>(bands, HttpStatus.OK);
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
        List<Band> bands = bandService.searchBand(query);
        return new ResponseEntity<>(bands, HttpStatus.OK);
    }


}
