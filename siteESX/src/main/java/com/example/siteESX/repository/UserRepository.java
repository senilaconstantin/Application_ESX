package com.example.siteESX.repository;

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


    @Query("MATCH (u:User{id: $id }) set u += {firstName:$firstName, lastName: $lastName, mail:$mail, password:$password, adress:$adress}")
    void update(@Param("id") String id, @Param("firstName") String firstName, @Param("lastName") String lastName, @Param("mail") String mail, @Param("password") String password, @Param("adress") String adress);

    @Query("CREATE (:User{id:$id, firstName:$firstName, lastName: $lastName, mail:$mail, password:$password, adress:$adress})")
    void addUser(@Param("id") String id, @Param("firstName") String firstName, @Param("lastName") String lastName, @Param("mail") String mail, @Param("password") String password, @Param("adress") String adress);

    @Query("match (u:User{id: $id}) delete u")
    void deleteUser(@Param("id") String id);

    @Query("MATCH\n" +
            "  (u:User),\n" +
            "  (a:Abonament)\n" +
            "WHERE a.id = $idUser AND b.id = $idAbonament" +
            "CREATE (u)-[r:H]->(a) ")
    void addArc(@Param("idUser") String idUser, @Param("idAbonament") String idAbonament);

}
