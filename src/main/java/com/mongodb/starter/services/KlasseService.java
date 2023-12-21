package com.mongodb.starter.services;

import com.mongodb.starter.models.Klasse;
import com.mongodb.starter.models.Maturafach;
import com.mongodb.starter.models.Schueler;
import com.mongodb.starter.repositories.KlasseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KlasseService {
    private KlasseRepository klasseRepository;

    @Autowired
    public KlasseService(KlasseRepository klasseRepository) {
        this.klasseRepository = klasseRepository;
    }

    public void erstelleKlasse(String className) {
        Klasse neueKlasse = new Klasse(className);
        klasseRepository.save(neueKlasse);
    }

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

    public List<Klasse> getAlleKlassen() {
        return klasseRepository.findAll();
    }

    private Optional<Schueler> findSchuelerInKlasse(Klasse klasse, String studentName) {
        return klasse.getSchueler().stream()
                .filter(schueler -> schueler.getName().equals(studentName))
                .findFirst();
    }
}