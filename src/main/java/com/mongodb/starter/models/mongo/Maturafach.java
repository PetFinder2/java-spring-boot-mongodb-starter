package com.mongodb.starter.models.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Maturafach {
    @Id
    private String id;
    private String fach;
    private int note;

    public Maturafach(String fach, int note) {
        this.fach = fach;
        this.note = note;
    }

    public String getFach() {
        return fach;
    }

    public int getNote() {
        return note;
    }

    @Override
    public String toString() {
        return fach + ": " + note;
    }
}
