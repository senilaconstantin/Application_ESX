package com.example.siteESX.service;

import com.example.siteESX.model.Abonament;
import com.example.siteESX.model.Sala;
import com.example.siteESX.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SalaService {
    @Autowired
    SalaRepository salaRepository;
    public Collection<Sala> getAll() {
        return salaRepository.getAllSali();
    }

    public Sala getSalaId(String id) {
        return salaRepository.getSalId(id);
    }

    public void update(Sala sala) {
        salaRepository.update(sala.getId(), sala.getName(), sala.getAdress());
    }

    public void addSala(Sala sala) {
        salaRepository.addSala(sala.getId(), sala.getName(), sala.getAdress());
    }

    public void deleteSala(String id) {

        salaRepository.deleteSala(id);
    }
}
