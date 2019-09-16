package com.example.rockpedia;

import com.example.rockpedia.entity.Band;
import org.springframework.boot.actuate.info.Info;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class RockpediaWS {
    private HashMap<Integer, Band> bandlist;
    public RockpediaWS() {
        bandlist = new HashMap<Integer, Band>();
        bandlist.put(1, new Band(1, "Rammstein",  "Till Lindemann, Paul Landers, Richard Kruspe, Oliver Riedel, Christopher Schneider, Flake Lorenz", "Industrial German Metal", 1994," Berlin", "Universal group music"));
    }

    @GetMapping("/bands/{id}")
    public ResponseEntity<Info> Get(@PathVariable Integer id){
        Band bandtoget = bandlist.get(id);
        Info.Builder builder = new Info.Builder();
        builder.withDetail("Name", bandtoget.getName());
        builder.withDetail("members", bandtoget.getMembers());
        builder.withDetail("style", bandtoget.getStyle());
        builder.withDetail("members", bandtoget.getMembers());
        builder.withDetail("yearofcreation", bandtoget.getYearofcreation());
        builder.withDetail("townoforigin", bandtoget.getTownoforigin());
        builder.withDetail("label", bandtoget.getLabel());
        final Info hello = builder.build();
        return ResponseEntity.ok(hello);

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
