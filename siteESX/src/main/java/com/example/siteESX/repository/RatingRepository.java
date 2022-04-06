package com.example.siteESX.repository;

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

    @Query("CREATE (:Rating{id:$id, note:$note, comment: $comment})")
    void addRating(@Param("id") String id, @Param("note") float note, @Param("comment") String comment);

    @Query("match (r:Rating{id: $id}) delete r")
    void deleteRating(@Param("id") String id);
}
