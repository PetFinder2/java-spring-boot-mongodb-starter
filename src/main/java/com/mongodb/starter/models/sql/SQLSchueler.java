
package com.mongodb.starter.models.sql;

import com.mongodb.starter.models.mongo.Maturafach;
import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "schueler")
public class SQLSchueler {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "klasse_id")
    private SQLKlasse klasse;

    @OneToMany(mappedBy = "schueler", cascade = CascadeType.ALL)
    private List<SQLMaturafach> maturafacher;

    public SQLSchueler() {
        // No-arg constructor
    }

    public SQLSchueler(String name) {
        this.name = name;
        this.maturafacher = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SQLKlasse getKlasse() {
        return klasse;
    }

    public void setKlasse(SQLKlasse klasse) {
        this.klasse = klasse;
    }

    public List<SQLMaturafach> getMaturafacher() {
        return maturafacher;
    }

    public void addMaturafach(SQLMaturafach maturafach) {
        maturafacher.add(maturafach);
    }

}
