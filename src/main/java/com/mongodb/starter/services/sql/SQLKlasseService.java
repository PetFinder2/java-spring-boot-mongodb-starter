// SQLKlasseService.java

package com.mongodb.starter.services.sql;

import com.mongodb.starter.models.sql.SQLKlasse;
import com.mongodb.starter.models.sql.SQLMaturafach;
import com.mongodb.starter.models.sql.SQLSchueler;
import com.mongodb.starter.repositories.sql.SQLKlasseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SQLKlasseService {
    private SQLKlasseRepository klasseRepository;

    @Autowired
    public SQLKlasseService(SQLKlasseRepository klasseRepository) {
        this.klasseRepository = klasseRepository;
    }

    public void erstelleKlasse(String className) {
        SQLKlasse neueKlasse = new SQLKlasse(className);
        klasseRepository.save(neueKlasse);
    }

    public void fuegeSchuelerHinzu(String className, SQLSchueler schueler) {
        Optional<SQLKlasse> optionalKlasse = klasseRepository.findByClassName(className);
        if (optionalKlasse.isPresent()) {
            SQLKlasse klasse = optionalKlasse.get();
            klasse.addSchueler(schueler);
            klasseRepository.save(klasse);
        } else {
            System.out.println("Klasse nicht gefunden.");
        }
    }

    public void fuegeMaturafachHinzu(String className, String studentName, SQLMaturafach maturafach) {
        Optional<SQLKlasse> optionalKlasse = klasseRepository.findByClassName(className);
        if (optionalKlasse.isPresent()) {
            SQLKlasse klasse = optionalKlasse.get();
            Optional<SQLSchueler> optionalSchueler = findSchuelerInKlasse(klasse, studentName);
            if (optionalSchueler.isPresent()) {
                SQLSchueler schueler = optionalSchueler.get();
                schueler.addMaturafach(maturafach);
                klasseRepository.save(klasse);
            } else {
                System.out.println("Schüler nicht gefunden.");
            }
        } else {
            System.out.println("Klasse nicht gefunden.");
        }
    }

    public List<SQLKlasse> getAlleKlassen() {
        return klasseRepository.findAll();
    }

    public Optional<SQLSchueler> findSchuelerInKlasse(SQLKlasse klasse, String studentName) {
        return klasse.getSchueler().stream()
                .filter(schueler -> schueler.getName().equals(studentName))
                .findFirst();
    }

    public void entferneKlasse(String className) {
        klasseRepository.deleteByClassName(className);
    }

    public void entferneAlleKlassen() {
        klasseRepository.deleteAll();
    }
}
