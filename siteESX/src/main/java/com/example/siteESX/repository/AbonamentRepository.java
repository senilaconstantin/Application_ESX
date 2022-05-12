package com.example.siteESX.repository;

import com.example.siteESX.model.Abonament;
import com.example.siteESX.model.Sala;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface AbonamentRepository extends Neo4jRepository<Abonament, Long> {
    @Query("MATCH (a:Abonament) RETURN a")
    Collection<Abonament> getAllAbonements();

    @Query("MATCH (a:Abonament{id: $id }) RETURN a")
    Abonament getAbonamentId(@Param("id") String id);

    @Query("Match (a:Abonament) return a.tipAbonament")
    Collection<String> getTipAbonament();


    @Query("MATCH (a:Abonament{id: $id }) set a += {tipAbonament:$tipAbonament, pret: $pret}")
    void update(@Param("id") String id, @Param("tipAbonament") String tipAbonament, @Param("pret") float pret);

    @Query("CREATE (:Abonament{id:$id, tipAbonament:$tipAbonament, pret:$pret})")
    void addAbonament(@Param("id") String id, @Param("tipAbonament") String tipAbonament, @Param("pret") float pret);

    @Query("match (a:Abonament{id: $id}) delete a")
    void deleteAbonament(@Param("id") String id);
    ////////////////////////
    @Query("MATCH (a:Abonament{id: $idAbonament})\n" +
            "MATCH (s:Sala{id: $idSala}) \n" +
            "CREATE (a)-[r:Has_Sala]->(s)")
    void addArc(@Param("idAbonament") String idAbonament, @Param("idSala") String idSala);

    ///Delete Arc
    @Query("MATCH (a:Abonament {id:$idAbonament})-[r:Has_Sala]->(:Sala {id:$idSala}) DELETE r")
    void deleteArc(@Param("idAbonament") String idAbonament, @Param("idSala") String idSala);

    ////GetSala
    @Query("MATCH (s:Sala)-[:Has_Sala]-(a:Abonament {id:$idAbonament}) return s")
    Collection<Sala> getSala(@Param("idAbonament") String idAbonament);
}
