package com.example.rockpedia;

import com.example.rockpedia.dao.BandDAO;
import com.example.rockpedia.entity.Band;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

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
        builder.withDetail("Name", bandtoget.getName());
        builder.withDetail("members", bandtoget.getMembers());
        builder.withDetail("style", bandtoget.getStyle());
        builder.withDetail("members", bandtoget.getMembers());
        builder.withDetail("yearofcreation", bandtoget.getYearofcreation());
        builder.withDetail("townoforigin", bandtoget.getTownoforigin());
        builder.withDetail("label", bandtoget.getLabel());
        final Info bandJSON = builder.build();
        return ResponseEntity.ok(bandJSON);

    }

    @GetMapping(" /rockpedia1")
    public String coucou() {
        return "coucou";
    }

    @GetMapping("/rockpedia")
    public ResponseEntity<Info> greeting(@RequestParam(value="name", defaultValue = " World") final String name)
    {
        final Info hello = new Info.Builder().withDetail("Hello", name).build();
        return ResponseEntity.ok(hello);
    }


}
