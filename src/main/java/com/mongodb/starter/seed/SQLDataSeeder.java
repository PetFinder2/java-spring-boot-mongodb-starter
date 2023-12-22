// SQLDataSeeder.java

package com.mongodb.starter.seed;

import com.github.javafaker.Faker;
import com.mongodb.starter.dto.GeneratedData;
import com.mongodb.starter.models.sql.SQLKlasse;
import com.mongodb.starter.models.sql.SQLMaturafach;
import com.mongodb.starter.models.sql.SQLSchueler;
import com.mongodb.starter.services.sql.SQLKlasseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SQLDataSeeder {

    private final SQLKlasseService klasseService;
    private final Faker faker;

    @Autowired
    public SQLDataSeeder(SQLKlasseService klasseService) {
        this.klasseService = klasseService;
        this.faker = new Faker();
    }

    public List<GeneratedData> seedData(int numberOfRecords) {
        List<GeneratedData> generatedDataList = new ArrayList<>();

        for (int i = 0; i < numberOfRecords; i++) {
            String className = String.valueOf(generateRandomInt());

            klasseService.erstelleKlasse(className);

            SQLKlasse klasse = klasseService.getKlasseByClassName(className);
            SQLSchueler schueler = generateRandomSchueler();
            klasseService.fuegeSchuelerHinzu(className, schueler);

            SQLMaturafach maturafach = generateRandomMaturafach();
            klasseService.fuegeMaturafachHinzu(className, schueler.getName(), maturafach);

            generatedDataList.add(new GeneratedData(className, schueler.getName(), maturafach.getFach(), maturafach.getNote()));
        }

        return generatedDataList;
    }

    private SQLSchueler generateRandomSchueler() {
        return new SQLSchueler(faker.name().fullName());
    }

    private SQLMaturafach generateRandomMaturafach() {
        String fach = faker.lorem().characters(2, 4);
        int note = faker.number().numberBetween(1, 6);
        return new SQLMaturafach(fach, note);
    }

    private int generateRandomInt() {

        return (int) (Math.random() * 2147483646);
    }
}
