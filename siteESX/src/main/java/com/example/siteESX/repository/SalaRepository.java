package com.example.siteESX.repository;

import com.example.siteESX.model.Sala;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface SalaRepository extends Neo4jRepository<Sala, Long> {
    @Query("MATCH (s:Sala) RETURN s")
    Collection<Sala> getAllSali();

    @Query("MATCH (s:Sala{id: $id })RETURN s")
    Sala getSalId(@Param("id") String id);


    @Query("MATCH (s:Sala{id: $id }) set s += {name:$name, adress: $adress}")
    void update(@Param("id") String id, @Param("name") String name, @Param("adress") String adress);

    @Query("CREATE (:Sala{id:$id, name:$name, adress: $adress})")
    void addSala(@Param("id") String id, @Param("name") String name, @Param("adress") String adress);

    @Query("match (s:Sala{id: $id}) delete s")
    void deleteSala(@Param("id") String id);
}
