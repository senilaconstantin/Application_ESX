package com.example.siteESX.model;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

@NodeEntity
public class Abonament {
    @GraphId
    private Long idAbonament;

    private String id;
    private String tipAbonament;
    private float pret;

    @Relationship(type = "Has", direction = Relationship.INCOMING)
    private List<User> user;

    public Abonament(String id, String tipAbonament, float pret) {
        this.id = id;
        this.tipAbonament = tipAbonament;
        this.pret = pret;
    }

    public Abonament() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipAbonament() {
        return tipAbonament;
    }

    public void setTipAbonament(String tipAbonament) {
        this.tipAbonament = tipAbonament;
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }
}
