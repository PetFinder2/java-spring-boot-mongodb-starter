package com.mongodb.starter.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class Schueler {
    @Id
    private String id;
    private String name;
    private List<Maturafach> maturafacher;

    public Schueler(String name) {
        this.name = name;
        this.maturafacher = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addMaturafach(Maturafach maturafach) {
        maturafacher.add(maturafach);
    }

    public List<Maturafach> getMaturafacher() {
        return maturafacher;
    }
}
