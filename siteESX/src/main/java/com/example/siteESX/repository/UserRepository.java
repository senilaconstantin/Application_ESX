package com.example.siteESX.repository;

import com.example.siteESX.model.Abonament;
import com.example.siteESX.model.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface UserRepository extends Neo4jRepository<User, Long> {
    @Query("MATCH (u:User) RETURN u")
    Collection<User> getAllUsers();

    @Query("MATCH (u:User{id: $id })RETURN u")
    User getUserID(@Param("id") String id);

    @Query("MATCH (u:User{mail: $mail, password:$password })RETURN u")
    User login(@Param("mail") String mail, @Param("password") String password);

    @Query("MATCH (u:User{mail: $mail, password:$password, role:'ADMIN'})RETURN u")
    User loginAdmin(@Param("mail") String mail, @Param("password") String password);


    @Query("MATCH (u:User{id: $id }) set u += {firstName:$firstName, lastName: $lastName, mail:$mail, password:$password, adress:$adress}")
    void update(@Param("id") String id, @Param("firstName") String firstName, @Param("lastName") String lastName, @Param("mail") String mail, @Param("password") String password, @Param("adress") String adress);

    @Query("CREATE (:User{id:$id, firstName:$firstName, lastName: $lastName, mail:$mail, password:$password, adress:$adress, role:$role})")
    void addUser(@Param("id") String id, @Param("firstName") String firstName, @Param("lastName") String lastName, @Param("mail") String mail, @Param("password") String password, @Param("adress") String adress, @Param("role") String role);

    @Query("MATCH (a:Abonament{tipAbonament: $tipAbonament}) CREATE (u:User{id:$id, firstName:$firstName, lastName: $lastName, mail:$mail, password:$password, adress:$adress}) CREATE (u)-[:Has_Abonament]->(a)")
    void addUserAb(@Param("id") String id, @Param("firstName") String firstName, @Param("lastName") String lastName, @Param("mail") String mail, @Param("password") String password, @Param("adress") String adress, @Param("tipAbonament") String tipAbonament);

    @Query("match (u:User{id: $id}) detach delete u")
    void deleteUser(@Param("id") String id);

    ///////////////Relatie
    @Query("MATCH (a:User{id: $idUser})\n" +
            "MATCH (b:Abonament{id: $idAbonament}) \n" +
            "CREATE (a)-[r:Has_Abonament]->(b)")
    void addArc(@Param("idUser") String idUser, @Param("idAbonament") String idAbonament);

//    @Query("MATCH (a:Abonament{id: $idAbonament})\n" +
//            "MATCH (b:User{id: $idUser}) \n" +
//            "CREATE (a)-[r:Has_Abonament]->(b)")
//    void addArcI(@Param("idUser") String idUser, @Param("idAbonament") String idAbonament);

    ///Delete Arc
    @Query("MATCH (:User {id:$idUser})-[r:Has_Abonament]->() DELETE r")
    void deleteArc(@Param("idUser") String idUser);

    //    @Query("MATCH (:Abonament {id:$idAbonament})-[r:Has_Abonament]->(:User {id:$idUser}) DELETE r")
//    void deleteArcI(@Param("idUser") String idUser, @Param("idAbonament") String idAbonament);
    ////GetAbonament
    @Query("MATCH (a:Abonament )-[:Has_Abonament]-(u:User{id:$idUser}) return a")
    Abonament getAbonament(@Param("idUser") String idUser);
}
