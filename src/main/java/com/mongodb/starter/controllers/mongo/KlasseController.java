package com.mongodb.starter.controllers.mongo;

import com.mongodb.starter.dto.GeneratedData;
import com.mongodb.starter.models.mongo.Klasse;
import com.mongodb.starter.models.mongo.Maturafach;
import com.mongodb.starter.models.mongo.Schueler;
import com.mongodb.starter.seed.DataSeeder;
import com.mongodb.starter.services.mongo.KlasseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = {"*"}, allowedHeaders = {"*"})
@RestController
@RequestMapping("/api/mongodb/klassen")
public class KlasseController {
    private final KlasseService klasseService;

    private final DataSeeder dataSeeder;

    @Autowired
    public KlasseController(KlasseService klasseService, DataSeeder dataSeeder) {
        this.klasseService = klasseService;
        this.dataSeeder = dataSeeder;
    }
    @CrossOrigin(origins = {"*"}, allowedHeaders = {"*"})
    @PostMapping("/create/{classname}")
    public void erstelleKlasse(@PathVariable String classname) {
        klasseService.erstelleKlasse(classname);
    }


    @CrossOrigin(origins = {"*"}, allowedHeaders = {"*"})
    @PostMapping("/{className}/schueler")
    public void fuegeSchuelerHinzu(@PathVariable String className, @RequestBody Schueler schueler) {
        klasseService.fuegeSchuelerHinzu(className, schueler);
    }

    @CrossOrigin(origins = {"*"}, allowedHeaders = {"*"})
    @PostMapping("/{className}/schueler/{studentName}/maturafach")
    public void fuegeMaturafachHinzu(
            @PathVariable String className,
            @PathVariable String studentName,
            @RequestBody Maturafach maturafach
    ) {
        klasseService.fuegeMaturafachHinzu(className, studentName, maturafach);
    }
    @CrossOrigin(origins = {"*"}, allowedHeaders = {"*"})
    @GetMapping
    public List<Klasse> getAlleKlassen() {
        return klasseService.getAlleKlassen();
    }

    @CrossOrigin(origins = {"*"}, allowedHeaders = {"*"})
    @GetMapping("/seed/{count}")
    public ResponseEntity<Map<String, Object>> seedData(@PathVariable("count") int number) {
        long startTime = System.currentTimeMillis();
        List<GeneratedData> generatedDataList = dataSeeder.seedData(number);
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;

        double recordsPerSecond = (double) number / (totalTime / 1000.0);

        Map<String, Object> result = new HashMap<>();
        result.put("generatedDataList", generatedDataList);
        result.put("totalTime", totalTime);
        result.put("recordsPerSecond", recordsPerSecond);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
