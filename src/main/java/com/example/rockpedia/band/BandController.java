package com.example.rockpedia.band;

import com.example.rockpedia.SwaggerConfig;
import io.swagger.annotations.*;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

@RestController
@RequestMapping("/bands")
@Api(value = "UserController", produces = MediaType.APPLICATION_JSON_VALUE)
public class BandController {
    
    private static final String MESSAGEBEGIN = "{\n\t\"message\": \"";
    private static final String MESSAGEEND = "\"\n}";

    private final BandRESTService bandRESTService;
    private final BandExcelService bandExcelService;

    public BandController(BandRESTService bandRESTService, BandExcelService bandExcelService) {
        this.bandRESTService = bandRESTService;
        this.bandExcelService = bandExcelService;
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Getting a band by its id")
    @ApiResponses({@ApiResponse(code = 200, message = "OK", response = Band.class),
            @ApiResponse(code = 204, message = MESSAGEBEGIN + "There is no band by id {id}" +  MESSAGEEND),
            @ApiResponse(code = 404, message = "Not Found")})
    public ResponseEntity<Object> byId(@PathVariable Long id){
        Band band = bandRESTService.byId(id);
        if (band == null)
            return new ResponseEntity<>(MESSAGEBEGIN + "There is no band by id " + id + MESSAGEEND, HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(bandRESTService.byId(id), HttpStatus.OK);
    }

    @GetMapping("")
    @ApiOperation(value = "Getting all bands")
    @ApiResponses({@ApiResponse(code = 200, message = "OK", response = SwaggerConfig.BandsList.class),
            @ApiResponse(code = 204, message = MESSAGEBEGIN + "There is no band by id {id}" +  MESSAGEEND),
            @ApiResponse(code = 404, message = "Not Found")})
    public ResponseEntity<List> getAll(){
        return new ResponseEntity<>(bandRESTService.getAll(), HttpStatus.OK);
    }

    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "Adding a band")
    @ApiResponses({@ApiResponse(code = 200, message = "OK", response = Band.class)})
    public ResponseEntity<Object> newBand(@RequestBody BandTemplate band)
    {
        try {
            return new ResponseEntity<>(bandRESTService.add(new Band(band)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/update/{id}", consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "Updating a band")
    @ApiResponses({@ApiResponse(code = 200, message = "OK", response = Band.class)})
    public ResponseEntity<Object> replaceBand(@RequestBody BandTemplate band, @PathVariable Long id)
    {
        try {
            return new ResponseEntity<>(bandRESTService.insert(id, new Band(band)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/delete/{id}", produces = "application/json")
    @ApiOperation(value = "Deleting a band")
    @ApiResponses({@ApiResponse(code = 200, message = "OK", response = Band.class)})
    public ResponseEntity<String> deleteBand(@PathVariable Long id)
    {
        String result;
        try {
            result = bandRESTService.deleteById(id);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/byName/{name}")
    @ApiOperation(value = "Searching a band by its name")
    @ApiResponses({@ApiResponse(code = 200, message = "OK", response = SwaggerConfig.BandsList.class)})
    public ResponseEntity<Object> byName(@PathVariable(value = "name") String name)
    {
        List<Band> bands;
        try {
            bands = bandRESTService.searchBandName(name);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            return new ResponseEntity<>(MESSAGEBEGIN + e.getMessage() + MESSAGEEND, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(bands, HttpStatus.OK);
    }

    @GetMapping("/byGenre/{genre}")
    @ApiOperation(value = "Searching a band by its genre")
    @ApiResponses({@ApiResponse(code = 200, message = "OK", response = SwaggerConfig.BandsList.class)})
    public ResponseEntity<Object> byGenre(@PathVariable(value="genre")String genre)
    {
        List<Band> bands;
        try {
            bands = bandRESTService.searchBandGenre(genre);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            return new ResponseEntity<>(MESSAGEBEGIN + e.getMessage() + MESSAGEEND, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(bands, HttpStatus.OK);
    }

    @GetMapping("/byTheme/{theme}")
    @ApiOperation(value = "Searching a band by its theme")
    @ApiResponses({@ApiResponse(code = 200, message = "OK", response = SwaggerConfig.BandsList.class)})
    public ResponseEntity<Object> byTheme(@PathVariable(value="theme")String theme)
    {
        List<Band> bands;
        try {
            bands = bandRESTService.searchBandTheme(theme);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            return new ResponseEntity<>(MESSAGEBEGIN + e.getMessage() + MESSAGEEND, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(bands, HttpStatus.OK);
    }

    @GetMapping("/byLocation/{location}")
    @ApiOperation(value = "Searching a band by its location")
    @ApiResponses({@ApiResponse(code = 200, message = "OK", response = SwaggerConfig.BandsList.class)})
    public ResponseEntity<Object> byLocation(@PathVariable(value="location")String location)
    {
        List<Band> bands;
        try {
            bands = bandRESTService.searchBandLocation(location);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            return new ResponseEntity<>(MESSAGEBEGIN + e.getMessage() + MESSAGEEND, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(bands, HttpStatus.OK);
    }
    @GetMapping("/byCountry/{country}")
    @ApiOperation(value = "Searching a band by its country")
    @ApiResponses({@ApiResponse(code = 200, message = "OK", response = SwaggerConfig.BandsList.class)})
    public ResponseEntity<Object> byCountry(@PathVariable(value="country")String country)
    {
        List<Band> bands;
        try {
            bands = bandRESTService.searchBandCountry(country);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            return new ResponseEntity<>(MESSAGEBEGIN + e.getMessage() + MESSAGEEND, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(bands, HttpStatus.OK);
    }

    @GetMapping("/byLabel/{label}")
    @ApiOperation(value = "Searching a band by its label")
    @ApiResponses({@ApiResponse(code = 200, message = "OK", response = SwaggerConfig.BandsList.class)})
    public ResponseEntity<Object> byLabel(@PathVariable(value="label")String label)
    {
        List<Band> bands;
        try {
            bands = bandRESTService.searchBandLabel(label);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            return new ResponseEntity<>(MESSAGEBEGIN + e.getMessage() + MESSAGEEND, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(bands, HttpStatus.OK);
    }

    @GetMapping("/byStatus/{status}")
    @ApiOperation(value = "Searching a band by its status")
    @ApiResponses({@ApiResponse(code = 200, message = "OK", response = SwaggerConfig.BandsList.class)})
    public ResponseEntity<Object> byStatus(@PathVariable(value="status")String status)
    {
        List<Band> bands;
        try {
            bands = bandRESTService.searchBandStatus(status);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            return new ResponseEntity<>(MESSAGEBEGIN + e.getMessage() + MESSAGEEND, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(bands, HttpStatus.OK);
    }

    @GetMapping("/byFormed/{formed}")
    @ApiOperation(value = "Searching a band by its date of formation")
    @ApiResponses({@ApiResponse(code = 200, message = "OK", response = SwaggerConfig.BandsList.class)})
    public ResponseEntity<List> byFormed(@PathVariable(value="formed")int formed)
    {
        List<Band> bands = bandRESTService.searchBandFormed(formed);
        return new ResponseEntity<>(bands, HttpStatus.OK);
    }

    @GetMapping("/search")
    @ApiOperation(value = "Searching a band by all values, date of formation apart")
    @ApiResponses({@ApiResponse(code = 200, message = "OK", response = SwaggerConfig.BandsList.class)})
    public ResponseEntity<Object> getBandsBySearch(@ApiParam(name = "q", value = "Query to search") @RequestParam("q") String query, @ApiParam(name = "csv", value = "Export CSV") @RequestParam(value = "csv", defaultValue = "false") boolean csv) throws IOException {
        List<Band> bands;
        try {
            bands = bandRESTService.searchBand(query);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            return new ResponseEntity<>(MESSAGEBEGIN + e.getMessage() + MESSAGEEND, HttpStatus.BAD_REQUEST);
        }
        return csv ? exportCSV(bands, "bands_search_" + query) : new ResponseEntity<>(bands, HttpStatus.OK);
    }

    private ResponseEntity<Object> exportCSV(List<Band> bands, String filename) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("charset", "utf-8");
        headers.add(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + filename + ".csv\"");

        InputStreamResource excel = bandExcelService.exportCSV(bands);

        return ResponseEntity.ok().headers(headers).contentType(MediaType.parseMediaType("text/csv")).body(excel);
    }

}
