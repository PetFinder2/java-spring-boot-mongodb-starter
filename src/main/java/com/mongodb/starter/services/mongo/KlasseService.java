package com.mongodb.starter.services.mongo;

import com.mongodb.starter.models.mongo.Klasse;
import com.mongodb.starter.models.mongo.Maturafach;
import com.mongodb.starter.models.mongo.Schueler;
import com.mongodb.starter.models.sql.SQLKlasse;
import com.mongodb.starter.repositories.mongo.KlasseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"*"}, allowedHeaders = {"*"})
@Service
public class KlasseService {
    private KlasseRepository klasseRepository;

    @Autowired
    public KlasseService(KlasseRepository klasseRepository) {
        this.klasseRepository = klasseRepository;
    }
    @CrossOrigin(origins = {"*"}, allowedHeaders = {"*"})
    public void erstelleKlasse(String className) {
        Klasse neueKlasse = new Klasse(className);
        klasseRepository.save(neueKlasse);
    }
    @CrossOrigin(origins = {"*"}, allowedHeaders = {"*"})
    public void fuegeSchuelerHinzu(String className, Schueler schueler) {
        Optional<Klasse> optionalKlasse = klasseRepository.findByClassName(className);
        if (optionalKlasse.isPresent()) {
            Klasse klasse = optionalKlasse.get();
            klasse.addSchueler(schueler);
            klasseRepository.save(klasse);
        } else {
            System.out.println("Klasse nicht gefunden.");
        }
    }
    @CrossOrigin(origins = {"*"}, allowedHeaders = {"*"})
    public void fuegeMaturafachHinzu(String className, String studentName, Maturafach maturafach) {
        Optional<Klasse> optionalKlasse = klasseRepository.findByClassName(className);
        if (optionalKlasse.isPresent()) {
            Klasse klasse = optionalKlasse.get();
            Optional<Schueler> optionalSchueler = findSchuelerInKlasse(klasse, studentName);
            if (optionalSchueler.isPresent()) {
                Schueler schueler = optionalSchueler.get();
                schueler.addMaturafach(maturafach);
                klasseRepository.save(klasse);
            } else {
                System.out.println("Schüler nicht gefunden.");
            }
        } else {
            System.out.println("Klasse nicht gefunden.");
        }
    }
    @CrossOrigin(origins = {"*"}, allowedHeaders = {"*"})
    public List<Klasse> getAlleKlassen() {
        return klasseRepository.findAll();
    }
    @CrossOrigin(origins = {"*"}, allowedHeaders = {"*"})
    public Optional<Schueler> findSchuelerInKlasse(Klasse klasse, String studentName) {
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
    public Klasse getKlasseByClassName(String className) {
        Optional<Klasse> optionalKlasse = klasseRepository.findByClassName(className);
        return optionalKlasse.orElse(null);
    }
}
