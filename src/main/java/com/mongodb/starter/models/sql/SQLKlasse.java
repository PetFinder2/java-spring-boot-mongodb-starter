

package com.mongodb.starter.models.sql;

import com.mongodb.starter.models.mongo.Schueler;
import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "klasse")
public class SQLKlasse {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String className;


    @OneToMany(mappedBy = "klasse", cascade = CascadeType.ALL)
    private List<SQLSchueler> schueler;

    public SQLKlasse() {
        // No-arg constructor
    }

    public SQLKlasse(String className) {
        this.className = className;
        this.schueler = new ArrayList<>();
    }

    public void addSchueler(SQLSchueler s) {
        schueler.add(s);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<SQLSchueler> getSchueler() {
        return schueler;
    }

}
