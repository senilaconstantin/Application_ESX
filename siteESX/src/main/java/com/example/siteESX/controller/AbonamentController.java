package com.example.siteESX.controller;

import com.example.siteESX.model.*;
import com.example.siteESX.service.AbonamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/abonament")
public class AbonamentController {
    @Autowired
    AbonamentService abonamentService;
    @GetMapping()
    public Collection<Abonament> getAll() {
        return abonamentService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Abonament> getUserID(@PathVariable String id) {
        Abonament abonament1 = abonamentService.getAbonamentId(id);
        if (abonament1 == null)
            return new ResponseEntity<Abonament>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(abonamentService.getAbonamentId(id));
    }

    @GetMapping("/tipAbonament")
    public Collection<String> getTipAbonament() {
        return abonamentService.getTipAbonament();
    }

    @PutMapping()
    public ResponseEntity update(@RequestBody Abonament abonament) {
        Abonament abonament1 = abonamentService.getAbonamentId(abonament.getId());
        if (abonament1 == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        Collection<Abonament> ab=abonamentService.getAll();
        for(Abonament abon:ab){
            if(abon.getTipAbonament().equals(abonament.getTipAbonament())){
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
        }
        abonamentService.update(abonament);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity addAbonament(@RequestBody Abonament abonament) {
        abonament.setId(UUID.randomUUID().toString());

        Collection<Abonament> ab=abonamentService.getAll();
        for(Abonament abon:ab){
            if(abon.getTipAbonament().equals(abonament.getTipAbonament())){
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
        }

        abonamentService.addAbonament(abonament);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAbonament(@PathVariable String id) {
        Abonament abonament = abonamentService.getAbonamentId(id);
        if (abonament == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        abonamentService.deleteAbonament(id);
        return new ResponseEntity(HttpStatus.OK);
    }
    ////////////
    @PostMapping("/addArc")
    public ResponseEntity addArc(@RequestBody AddSala arcA){
        Abonament abonament = abonamentService.getAbonamentId(arcA.getIdAbonament());
        if (abonament == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        abonamentService.addArc(arcA.getIdAbonament(), arcA.getIdSala());
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/deleteArc/{idAbonament}/{idSala}")
    public ResponseEntity deleteArc(@PathVariable String idAbonament, @PathVariable String idSala){
        Abonament abonament = abonamentService.getAbonamentId(idAbonament);
        if (abonament == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        abonamentService.deleteArc(idAbonament, idSala);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/getSala/{idAbonament}")
    public ResponseEntity<Collection<Sala>> getSala(@PathVariable String idAbonament){
        Abonament abonament = abonamentService.getAbonamentId(idAbonament);
        if (abonament == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        return ResponseEntity.ok(abonamentService.getSala(idAbonament));
    }

    @GetMapping("/getSalaNotAb/{idAbonament}")
    public ResponseEntity<Collection<Sala>> getSalaNotAb(@PathVariable String idAbonament) {
        Collection<Sala> sali=abonamentService.getSalaNotAb(idAbonament);
        return ResponseEntity.ok(sali);
    }
}
