package com.example.siteESX.controller;

import com.example.siteESX.model.Abonament;
import com.example.siteESX.model.Sala;
import com.example.siteESX.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/sala")
public class SalaController {
    @Autowired
    SalaService salaService;
    @GetMapping()
    public Collection<Sala> getAll() {
        return salaService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sala> getSalaId(@PathVariable String id) {
        Sala sala = salaService.getSalaId(id);
        if (sala == null)
            return new ResponseEntity<Sala>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(salaService.getSalaId(id));
    }

    @PutMapping()
    public ResponseEntity update(@RequestBody Sala sala) {
        Sala sala1 = salaService.getSalaId(sala.getId());
        if (sala1 == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        salaService.update(sala);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity addAbonament(@RequestBody Sala sala) {
        sala.setId(UUID.randomUUID().toString());

        salaService.addSala(sala);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAbonament(@PathVariable String id) {
        Sala sala = salaService.getSalaId(id);
        if (sala == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        salaService.deleteSala(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
