package com.example.rockpedia;

import org.springframework.boot.actuate.info.Info;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RockpediaWS {
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
