// SQLKlasseController.java

package com.mongodb.starter.controllers.sql;

import com.mongodb.starter.models.sql.SQLKlasse;
import com.mongodb.starter.models.sql.SQLMaturafach;
import com.mongodb.starter.models.sql.SQLSchueler;
import com.mongodb.starter.services.sql.SQLKlasseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sql/klassen")
public class SQLKlasseController {
    private final SQLKlasseService klasseService;

    @Autowired
    public SQLKlasseController(SQLKlasseService klasseService) {
        this.klasseService = klasseService;
    }

    @PostMapping
    public void erstelleKlasse(@RequestParam String className) {
        klasseService.erstelleKlasse(className);
    }

    @PostMapping("/{className}/schueler")
    public void fuegeSchuelerHinzu(@PathVariable String className, @RequestBody SQLSchueler schueler) {
        klasseService.fuegeSchuelerHinzu(className, schueler);
    }

    @PostMapping("/{className}/schueler/{studentName}/maturafach")
    public void fuegeMaturafachHinzu(
            @PathVariable String className,
            @PathVariable String studentName,
            @RequestBody SQLMaturafach maturafach
    ) {
        klasseService.fuegeMaturafachHinzu(className, studentName, maturafach);
    }

    @GetMapping
    public List<SQLKlasse> getAlleKlassen() {
        return klasseService.getAlleKlassen();
    }
}
