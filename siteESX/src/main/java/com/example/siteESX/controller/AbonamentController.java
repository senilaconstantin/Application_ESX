package com.example.siteESX.controller;

import com.example.siteESX.model.Abonament;
import com.example.siteESX.model.User;
import com.example.siteESX.service.AbonamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

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

    @PutMapping()
    public ResponseEntity update(@RequestBody Abonament abonament) {
        Abonament abonament1 = abonamentService.getAbonamentId(abonament.getId());
        if (abonament1 == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        abonamentService.update(abonament);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity addAbonament(@RequestBody Abonament abonament) {
        abonament.setId(UUID.randomUUID().toString());

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
}
