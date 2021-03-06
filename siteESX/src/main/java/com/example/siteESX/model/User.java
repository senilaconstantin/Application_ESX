package com.example.siteESX.model;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NodeEntity
public class User {
    @GraphId
    private Long idUser;

    private String id;

    private String firstName;
    private String lastName;
    private String mail;
    private String password;
    private String adress;

    private String role;

    @Relationship(type="Rating_User",direction=Relationship.OUTGOING)
    private Set<Rating> rating=new HashSet<Rating>();


    /*@Relationship(type = "Has", direction = Relationship.INCOMING)
    private Abonament abonament;*/

    public User(String id, String firstName, String lastName, String mail, String password, String adress) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.password = password;
        this.adress = adress;
    }

    public User(String id, String firstName, String lastName, String mail, String password, String adress, String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.password = password;
        this.adress = adress;
        this.role = role;
    }

    public User(String firstName, String lastName, String mail, String adress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.adress = adress;
    }

    public User() {
    }

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
