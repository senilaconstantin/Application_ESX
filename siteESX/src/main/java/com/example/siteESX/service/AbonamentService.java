package com.example.siteESX.service;

import com.example.siteESX.model.Abonament;
import com.example.siteESX.model.User;
import com.example.siteESX.repository.AbonamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AbonamentService {
    @Autowired
    AbonamentRepository abonamentRepository;

    public Collection<Abonament> getAll() {
        return abonamentRepository.getAllAbonements();
    }

    public Abonament getAbonamentId(String id) {
        return abonamentRepository.getAbonamentId(id);
    }

    public void update(Abonament abonament) {
        abonamentRepository.update(abonament.getId(), abonament.getTipAbonament(), abonament.getPret());
    }

    public void addAbonament(Abonament abonament) {
        abonamentRepository.addAbonament(abonament.getId(), abonament.getTipAbonament(), abonament.getPret());
    }

    public void deleteAbonament(String id) {

        abonamentRepository.deleteAbonament(id);
    }
}
