package com.mongodb.starter;

import com.mongodb.starter.models.Klasse;
import com.mongodb.starter.models.Maturafach;
import com.mongodb.starter.models.Schueler;
import com.mongodb.starter.services.KlasseService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EntityScan(basePackages = {"com.mongodb.starter.models", "com.mongodb.starter.models"})
public class ApplicationStarter {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationStarter.class, args);
    }

    @Bean
    public CommandLineRunner dataLoader(KlasseService klasseService) {
        return args -> {

            String className = "4DHIF";
            String schuelerName = "Stjepan Jaksic";

            Klasse klasse = new Klasse(className);
            System.out.println("---Klasse erstellt...---");

            Schueler schueler = new Schueler(schuelerName);

            schueler.addMaturafach(new Maturafach("Diplomarbeit", 1));
            schueler.addMaturafach(new Maturafach("Mathematik", 1));
            schueler.addMaturafach(new Maturafach("Deutsch", 1));
            schueler.addMaturafach(new Maturafach("Englisch", 1));
            schueler.addMaturafach(new Maturafach("Fachtheorie", 1));
            schueler.addMaturafach(new Maturafach("Schwerpunktfach", 1));
            schueler.addMaturafach(new Maturafach("Wahlfach", 1));

            klasse.addSchueler(schueler);
            System.out.println("---Schueler hinzugefuegt...---");

            klasseService.erstelleKlasse(klasse.getClassName());
            klasseService.fuegeSchuelerHinzu(klasse.getClassName(), schueler);
            System.out.println("---Gespeichert...---");

            System.out.println("----------");
            System.out.println("-----METHODEN-----");
            System.out.println("----------");

            System.out.println("---findSchuelerInKlasse---");

            String gesuchterSchuelerName = "Stjepan Jaksic";

            klasseService.findSchuelerInKlasse(klasse, gesuchterSchuelerName).ifPresentOrElse(
                    gefundenerSchueler -> System.out.println("Gefundener Schüler: " + gefundenerSchueler.getName() +
                            " in Klasse: " + klasse.getClassName()),
                    () -> System.out.println("Schüler nicht gefunden."));
            System.out.println("----------");

            System.out.println("---GetAlleKlassen---");

            List<Klasse> alleKlassen = klasseService.getAlleKlassen();
            if (alleKlassen.isEmpty()) {
                System.out.println("Keine Klassen gefunden.");
            } else {
                System.out.println("Alle Klassen:");
                alleKlassen.forEach(kl -> System.out.println("Klasse: " + kl.getClassName()));
            }
            System.out.println("----------");
            System.out.println("--------------------");


//            System.out.println("----------DELETE----------");
//            System.out.println("--------------------");
//            System.out.println("----------");
//
//            System.out.println("---EntferneKlasse---");
//
//            String zuLoeschendeKlasse = "5AHIF";
//            klasseService.entferneKlasse(zuLoeschendeKlasse);
//            System.out.println("--- Klasse " + zuLoeschendeKlasse + " wird entfernt..---");
//            System.out.println("-----");
//
//            System.out.println("Alle Klassen:");
//            alleKlassen.forEach(kl -> System.out.println("Klasse: " + kl.getClassName()));
//
//            System.out.println("---EntferneAlleKlassen---");
//            klasseService.entferneAlleKlassen();
//            if (alleKlassen.isEmpty()) {
//                System.out.println("Keine Klassen gefunden.");
//            } else {
//                System.out.println("Alle Klassen:");
//                alleKlassen.forEach(kl -> System.out.println("Klasse: " + kl.getClassName()));
//            }
//            System.out.println("----------");
//            System.out.println("--------------------");
//

//            System.out.println("----------UPDATE----------");
//            System.out.println("--------------------");
//            System.out.println("----------");
        };
    }
}
