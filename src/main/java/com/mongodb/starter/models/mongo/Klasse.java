package com.mongodb.starter.models.mongo;

import com.mongodb.starter.models.mongo.Schueler;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
@Document(collection = "klassen")
public class Klasse {
    @Id
    private String id;
    @Indexed(unique = true)
    private String className;
    private List<Schueler> schueler;

    public Klasse(String className) {
        this.className = className;
        this.schueler = new ArrayList<>();
    }

    public String getClassName() {
        return className;
    }

    public void addSchueler(Schueler s) {
        schueler.add(s);
    }

    public List<Schueler> getSchueler() {
        return schueler;
    }
}
