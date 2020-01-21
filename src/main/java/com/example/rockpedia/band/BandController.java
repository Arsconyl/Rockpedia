package com.example.rockpedia.band;

import com.example.rockpedia.SwaggerConfig;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import io.swagger.annotations.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

@RestController
@RequestMapping("/bands")
@Api(value = "UserController", produces = MediaType.APPLICATION_JSON_VALUE)
public class BandController {
    
    private static final String MESSAGEBEGIN = "{\n\t\"message\": \"";
    private static final String MESSAGEEND = "\"\n}";

    private final BandService bandService;

    public BandController(BandService bandService) {
        this.bandService = bandService;
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Getting a band by its id")
    @ApiResponses({@ApiResponse(code = 200, message = "OK", response = Band.class),
            @ApiResponse(code = 204, message = MESSAGEBEGIN + "There is no band by id {id}" +  MESSAGEEND),
            @ApiResponse(code = 404, message = "Not Found")})
    public ResponseEntity<Object> byId(@PathVariable Long id){
        Band band = bandService.byId(id);
        if (band == null)
            return new ResponseEntity<>(MESSAGEBEGIN + "There is no band by id " + id + MESSAGEEND, HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(bandService.byId(id), HttpStatus.OK);
    }

    @GetMapping("")
    @ApiOperation(value = "Getting all bands")
    @ApiResponses({@ApiResponse(code = 200, message = "OK", response = SwaggerConfig.BandsList.class),
            @ApiResponse(code = 204, message = MESSAGEBEGIN + "There is no band by id {id}" +  MESSAGEEND),
            @ApiResponse(code = 404, message = "Not Found")})
    public ResponseEntity<List> getAll(){
        return new ResponseEntity<>(bandService.getAll(), HttpStatus.OK);
    }

    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "Adding a band")
    @ApiResponses({@ApiResponse(code = 200, message = "OK", response = Band.class)})
    public ResponseEntity<Object> newBand(@RequestBody BandTemplate band)
    {
        try {
            return new ResponseEntity<>(bandService.add(new Band(band)), HttpStatus.OK);
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
            return new ResponseEntity<>(bandService.insert(id, new Band(band)), HttpStatus.OK);
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
            result = bandService.deleteById(id);
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
            bands = bandService.searchBandName(name);
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
            bands = bandService.searchBandGenre(genre);
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
            bands = bandService.searchBandTheme(theme);
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
            bands = bandService.searchBandLocation(location);
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
            bands = bandService.searchBandCountry(country);
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
            bands = bandService.searchBandLabel(label);
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
            bands = bandService.searchBandStatus(status);
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
        List<Band> bands = bandService.searchBandFormed(formed);
        return new ResponseEntity<>(bands, HttpStatus.OK);
    }

    @GetMapping("/search")
    @ApiOperation(value = "Searching a band by all values, date of formation apart")
    @ApiResponses({@ApiResponse(code = 200, message = "OK", response = SwaggerConfig.BandsList.class)})
    public ResponseEntity<Object> getBandsBySearch(@ApiParam(name = "q", value = "Query to search", defaultValue = "") @RequestParam("q") String query)
    {
        List<Band> bands;
        try {
            bands = bandService.searchBand(query);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            return new ResponseEntity<>(MESSAGEBEGIN + e.getMessage() + MESSAGEEND, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(bands, HttpStatus.OK);
    }

    @GetMapping("/export-bands")
    public void exportCSV(HttpServletResponse response) throws Exception {

        //set file name and content type
        String filename = "bands.csv";

        response.setContentType("text/csv");
        response.setCharacterEncoding("utf-8");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + filename + "\"");

        //create a csv writer
        StatefulBeanToCsv<Band> writer = new StatefulBeanToCsvBuilder<Band>(response.getWriter())
                .withQuotechar(CSVWriter.DEFAULT_QUOTE_CHARACTER)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withOrderedResults(true)
                .build();

        //write all users to csv file
        writer.write(bandService.getAll());

    }

}
