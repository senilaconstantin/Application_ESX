package com.example.siteESX.model;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class Rating {
    @GraphId
    private Long idRating;

    private String id;
    private float note;
    private String comment;
    private String idUser;
    private String idGym;
    @Relationship(type="Rating_User",direction=Relationship.INCOMING)
    private User user;


    public Rating(String id, float note, String comment) {
        this.id = id;
        this.note = note;
        this.comment = comment;
    }

    public Rating() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdGym() {
        return idGym;
    }

    public void setIdGym(String idGym) {
        this.idGym = idGym;
    }

    public User getUser() {
        return user;
    }
}
