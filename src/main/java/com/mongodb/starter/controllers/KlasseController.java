package com.mongodb.starter.controllers;

import com.mongodb.starter.models.Klasse;
import com.mongodb.starter.models.Maturafach;
import com.mongodb.starter.models.Schueler;
import com.mongodb.starter.services.KlasseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.mongodb.starter.services.*;

import java.util.List;

@RestController
@RequestMapping("/api/klassen")
public class KlasseController {
    private final KlasseService klasseService;

    @Autowired
    public KlasseController(KlasseService klasseService) {
        this.klasseService = klasseService;
    }

    @PostMapping
    public void erstelleKlasse(@RequestParam String className) {
        klasseService.erstelleKlasse(className);
    }

    @PostMapping("/{className}/schueler")
    public void fuegeSchuelerHinzu(@PathVariable String className, @RequestBody Schueler schueler) {
        klasseService.fuegeSchuelerHinzu(className, schueler);
    }

    @PostMapping("/{className}/schueler/{studentName}/maturafach")
    public void fuegeMaturafachHinzu(
            @PathVariable String className,
            @PathVariable String studentName,
            @RequestBody Maturafach maturafach
    ) {
        klasseService.fuegeMaturafachHinzu(className, studentName, maturafach);
    }

    @GetMapping
    public List<Klasse> getAlleKlassen() {
        return klasseService.getAlleKlassen();
    }
}
