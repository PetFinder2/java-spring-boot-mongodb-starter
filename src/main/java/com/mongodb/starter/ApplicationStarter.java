package com.mongodb.starter;

import com.mongodb.starter.models.Klasse;
import com.mongodb.starter.models.Maturafach;
import com.mongodb.starter.models.Schueler;
import com.mongodb.starter.services.KlasseService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApplicationStarter {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationStarter.class, args);
    }

    @Bean
    public CommandLineRunner dataLoader(KlasseService klasseService) {
        return args -> {

            Klasse klasse = new Klasse("5AHIF");

            Schueler schueler = new Schueler("Stjepan Jaksic");

            schueler.addMaturafach(new Maturafach("Diplomarbeit", 1));
            schueler.addMaturafach(new Maturafach("Mathematik", 1));
            schueler.addMaturafach(new Maturafach("Deutsch", 1));
            schueler.addMaturafach(new Maturafach("Englisch", 1));
            schueler.addMaturafach(new Maturafach("Fachtheorie", 1));
            schueler.addMaturafach(new Maturafach("Schwerpunktfach", 1));
            schueler.addMaturafach(new Maturafach("Wahlfach", 1));

            klasse.addSchueler(schueler);


            klasseService.erstelleKlasse(klasse.getClassName());
            klasseService.fuegeSchuelerHinzu(klasse.getClassName(), schueler);
        };
    }
}
