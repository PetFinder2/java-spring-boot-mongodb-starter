// SQLDataSeeder.java

package com.mongodb.starter.seed;

import com.github.javafaker.Faker;
import com.mongodb.starter.dto.GeneratedData;
import com.mongodb.starter.models.mongo.Klasse;
import com.mongodb.starter.models.mongo.Maturafach;
import com.mongodb.starter.models.mongo.Schueler;
import com.mongodb.starter.models.sql.SQLKlasse;
import com.mongodb.starter.models.sql.SQLMaturafach;
import com.mongodb.starter.models.sql.SQLSchueler;
import com.mongodb.starter.services.mongo.KlasseService;
import com.mongodb.starter.services.sql.SQLKlasseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataSeeder {

    private final KlasseService klasseService;
    private final Faker faker;

    @Autowired
    public DataSeeder(KlasseService klasseService) {
        this.klasseService = klasseService;
        this.faker = new Faker();
    }

    public List<GeneratedData> seedData(int numberOfRecords) {
        List<GeneratedData> generatedDataList = new ArrayList<>();

        for (int i = 0; i < numberOfRecords; i++) {
            String className = String.valueOf(generateRandomInt());

            klasseService.erstelleKlasse(className);

            Klasse klasse = klasseService.getKlasseByClassName(className);
            Schueler schueler = generateRandomSchueler();
            klasseService.fuegeSchuelerHinzu(className, schueler);

            Maturafach maturafach = generateRandomMaturafach();
            klasseService.fuegeMaturafachHinzu(className, schueler.getName(), maturafach);

            generatedDataList.add(new GeneratedData(className, schueler.getName(), maturafach.getFach(), maturafach.getNote()));
        }

        return generatedDataList;
    }

    private Schueler generateRandomSchueler() {
        return new Schueler(faker.name().fullName());
    }

    private Maturafach generateRandomMaturafach() {
        String fach = faker.lorem().characters(2, 4);
        int note = faker.number().numberBetween(1, 6);
        return new Maturafach(fach, note);
    }

    private int generateRandomInt() {

        return (int) (Math.random() * 2147483646);
    }
}
