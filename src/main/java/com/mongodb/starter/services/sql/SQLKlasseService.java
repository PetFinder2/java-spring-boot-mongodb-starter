// SQLKlasseService.java

package com.mongodb.starter.services.sql;

import com.mongodb.starter.models.sql.SQLKlasse;
import com.mongodb.starter.models.sql.SQLMaturafach;
import com.mongodb.starter.models.sql.SQLSchueler;
import com.mongodb.starter.repositories.sql.SQLKlasseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = {"*"}, allowedHeaders = {"*"})
@Service
public class SQLKlasseService {
    private final SQLKlasseRepository klasseRepository;

    @Autowired
    public SQLKlasseService(SQLKlasseRepository klasseRepository) {
        this.klasseRepository = klasseRepository;
    }
    @CrossOrigin(origins = {"*"}, allowedHeaders = {"*"})
    public void erstelleKlasse(String className) {
        SQLKlasse neueKlasse = new SQLKlasse(className);
        klasseRepository.save(neueKlasse);
    }
    @CrossOrigin(origins = {"*"}, allowedHeaders = {"*"})
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
    @CrossOrigin(origins = {"*"}, allowedHeaders = {"*"})
    public void fuegeMaturafachHinzu(String className, String studentName, SQLMaturafach maturafach) {
        Optional<SQLKlasse> optionalKlasse = klasseRepository.findByClassName(className);
        if (optionalKlasse.isPresent()) {
            SQLKlasse klasse = optionalKlasse.get();
            klasse.getSchueler().forEach(schueler -> {
                if (schueler.getName().equals(studentName)) {
                    schueler.addMaturafach(maturafach);
                }
            });
            klasseRepository.save(klasse);
        } else {
            System.out.println("Klasse nicht gefunden.");
        }
    }

    @CrossOrigin(origins = {"*"}, allowedHeaders = {"*"})
    public List<SQLKlasse> getAlleKlassen() {
        return klasseRepository.findAll();
    }
    @CrossOrigin(origins = {"*"}, allowedHeaders = {"*"})
    public Optional<SQLSchueler> findSchuelerInKlasse(SQLKlasse klasse, String studentName) {
        return klasse.getSchueler().stream()
                .filter(schueler -> schueler.getName().equals(studentName))
                .findFirst();
    }
    @CrossOrigin(origins = {"*"}, allowedHeaders = {"*"})
    public void entferneKlasse(String className) {
        klasseRepository.deleteByClassName(className);
    }
    @CrossOrigin(origins = {"*"}, allowedHeaders = {"*"})
    public void entferneAlleKlassen() {
        klasseRepository.deleteAll();
    }
    @CrossOrigin(origins = {"*"}, allowedHeaders = {"*"})
    public SQLKlasse getKlasseByClassName(String className) {
        Optional<SQLKlasse> optionalKlasse = klasseRepository.findByClassName(className);
        return optionalKlasse.orElse(null);
    }
}
