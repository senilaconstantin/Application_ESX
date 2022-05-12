package com.example.siteESX.model;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NodeEntity
public class UserAb {
    @GraphId
    private Long idUser;

    private String id;

    private String firstName;
    private String lastName;
    private String mail;
    private String password;
    private String adress;

    private String tipAbonament;
//    @Relationship(type="Rating_User",direction=Relationship.OUTGOING)
//    private Set<Rating> rating=new HashSet<Rating>();


    /*@Relationship(type = "Has", direction = Relationship.INCOMING)
    private Abonament abonament;*/




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getTipAbonament() {
        return tipAbonament;
    }

    public void setTipAbonament(String tipAbonament) {
        this.tipAbonament = tipAbonament;
    }
}
