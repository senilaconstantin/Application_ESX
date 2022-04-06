package com.example.siteESX.model;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Sala {
    @GraphId
    private Long idSala;

    private String id;
    private String name;
    private String adress;

    public Sala(String id, String name, String adress) {
        this.id = id;
        this.name = name;
        this.adress = adress;
    }
    public Sala(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}
