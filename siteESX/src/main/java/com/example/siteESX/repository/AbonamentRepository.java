package com.example.siteESX.repository;

import com.example.siteESX.model.Abonament;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface AbonamentRepository extends Neo4jRepository<Abonament, Long> {
    @Query("MATCH (a:Abonament) RETURN a")
    Collection<Abonament> getAllAbonements();

    @Query("MATCH (a:Abonament{id: $id }) RETURN a")
    Abonament getAbonamentId(@Param("id") String id);


    @Query("MATCH (a:Abonament{id: $id }) set a += {tipAbonament:$tipAbonament, pret: $pret}")
    void update(@Param("id") String id, @Param("tipAbonament") String tipAbonament, @Param("pret") float pret);

    @Query("CREATE (:Abonament{id:$id, tipAbonament:$tipAbonament, pret:$pret})")
    void addAbonament(@Param("id") String id, @Param("tipAbonament") String tipAbonament, @Param("pret") float pret);

    @Query("match (a:Abonament{id: $id}) delete a")
    void deleteAbonament(@Param("id") String id);
}
