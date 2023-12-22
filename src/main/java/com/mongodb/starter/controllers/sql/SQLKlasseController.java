// SQLKlasseController.java

package com.mongodb.starter.controllers.sql;

import com.mongodb.starter.dto.GeneratedData;
import com.mongodb.starter.models.sql.SQLKlasse;
import com.mongodb.starter.models.sql.SQLMaturafach;
import com.mongodb.starter.models.sql.SQLSchueler;
import com.mongodb.starter.seed.SQLDataSeeder;
import com.mongodb.starter.services.sql.SQLKlasseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"*"}, allowedHeaders = {"*"})
@RestController
@RequestMapping("/api/sql/klassen")
public class SQLKlasseController {
    private final SQLKlasseService klasseService;
    private final SQLDataSeeder dataSeeder;

    @Autowired
    public SQLKlasseController(SQLKlasseService klasseService,SQLDataSeeder dataSeeder) {
        this.klasseService = klasseService;
        this.dataSeeder = dataSeeder;
    }

    @CrossOrigin(origins = {"*"}, allowedHeaders = {"*"})
    @PostMapping
    public void erstelleKlasse(@RequestParam String className) {
        klasseService.erstelleKlasse(className);
    }

    @CrossOrigin(origins = {"*"}, allowedHeaders = {"*"})
    @PostMapping("/{className}/schueler")
    public void fuegeSchuelerHinzu(@PathVariable String className, @RequestBody SQLSchueler schueler) {
        klasseService.fuegeSchuelerHinzu(className, schueler);
    }

    @CrossOrigin(origins = {"*"}, allowedHeaders = {"*"})
    @PostMapping("/{className}/schueler/{studentName}/maturafach")
    public void fuegeMaturafachHinzu(
            @PathVariable String className,
            @PathVariable String studentName,
            @RequestBody SQLMaturafach maturafach
    ) {
        klasseService.fuegeMaturafachHinzu(className, studentName, maturafach);
    }


    @CrossOrigin(origins = {"*"}, allowedHeaders = {"*"})
    @GetMapping("/seed/{count}")
    public List<GeneratedData> seedData(@PathVariable("count") int number) {
        System.out.println("hallo");
        return dataSeeder.seedData(number);
    }


    @CrossOrigin(origins = {"*"}, allowedHeaders = {"*"})
    @GetMapping
    public List<SQLKlasse> getAlleKlassen() {
        return klasseService.getAlleKlassen();
    }
}
