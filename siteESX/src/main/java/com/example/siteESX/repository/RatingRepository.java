package com.example.siteESX.repository;

import com.example.siteESX.model.Abonament;
import com.example.siteESX.model.Rating;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface RatingRepository extends Neo4jRepository<Rating, Long> {
    @Query("MATCH (r:Rating) RETURN r")
    Collection<Rating> getAllRatings();

    @Query("MATCH (r:Rating{id: $id })RETURN r")
    Rating getRatingId(@Param("id") String id);


    @Query("MATCH (r:Rating{id: $id }) set r += {note:$note, comment: $comment}")
    void update(@Param("id") String id, @Param("note") float note, @Param("comment") String comment);

    @Query("CREATE (:Rating{id:$id, note:$note, comment: $comment, idGym:$idGym,idUser:$idUser})")
    void addRating(@Param("id") String id, @Param("note") float note, @Param("comment") String comment, @Param("idGym") String idgym, @Param("idUser") String idUser);

    @Query("MATCH (a:User{id: $idUser}) MATCH (ra:Rating{id: $idRating}) CREATE (a)-[r:Rating_User]->(ra)")
    void addUserRatingArc(@Param("idUser") String idUser, @Param("idRating") String idRating);

    @Query("MATCH (ra:Rating{id: $idRating}) MATCH (s:Sala{id: $idSala}) CREATE (ra)-[r:Rating_Sala]->(s)")
    void addRatingGymArc(@Param("idRating") String idRating, @Param("idSala") String idSala);

    @Query("MATCH (u:User)-[ru:Rating_User]-(ra:Rating )-[:Rating_Sala]-(s:Sala{id:$idGym}) return ra, collect(ru), collect(u)")
    Collection<Rating> getRating(@Param("idGym") String idGym);

    @Query("match (r:Rating{id: $id}) delete r")
    void deleteRating(@Param("id") String id);
}
