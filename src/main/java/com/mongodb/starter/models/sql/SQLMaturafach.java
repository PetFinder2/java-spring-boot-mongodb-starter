

package com.mongodb.starter.models.sql;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "maturafach")
public class SQLMaturafach {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fach;
    private int note;

    @ManyToOne
    @JoinColumn(name = "schueler_id")
    private SQLSchueler schueler;


    public SQLMaturafach() {
        // No-arg constructor
    }

    public SQLMaturafach(String fach, int note) {
        this.fach = fach;
        this.note = note;
    }

    public void setSchueler(SQLSchueler schueler) {
        this.schueler = schueler;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFach() {
        return fach;
    }

    public void setFach(String fach) {
        this.fach = fach;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

}
