package com.example.siteESX.model;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Rating {
   @GraphId
   private Long idRating;

   private String id;
   private float note;
   private String comment;

    public Rating(String id, float note, String comment) {
        this.id = id;
        this.note = note;
        this.comment = comment;
    }
    public Rating(){

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
}
