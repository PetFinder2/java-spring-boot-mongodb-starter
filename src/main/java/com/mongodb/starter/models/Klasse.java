package com.mongodb.starter.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "klassen")
@Entity
@Table(name = "klasse")
public class Klasse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primärer Schlüssel für JPA

    @Id
    private String mongoId;

    @Indexed(unique = true)
    private String className;

    @OneToMany(mappedBy = "klasse", cascade = CascadeType.ALL)
    private List<Schueler> schueler;

    public Klasse(String className) {
        this.className = className;
        this.schueler = new ArrayList<>();
    }

    public Klasse() {
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

    public String getMongoId() {
        return mongoId;
    }

    public void setMongoId(String mongoId) {
        this.mongoId = mongoId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
