package com.example.siteESX.service;

import com.example.siteESX.model.Abonament;
import com.example.siteESX.model.Sala;
import com.example.siteESX.model.User;
import com.example.siteESX.repository.AbonamentRepository;
import com.example.siteESX.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AbonamentService {
    @Autowired
    AbonamentRepository abonamentRepository;
    @Autowired
    SalaRepository sl;

    public Collection<Abonament> getAll() {
        return abonamentRepository.getAllAbonements();
    }

    public Abonament getAbonamentId(String id) {
        return abonamentRepository.getAbonamentId(id);
    }

    public Collection<String> getTipAbonament() {
        return abonamentRepository.getTipAbonament();
    }

    public void update(Abonament abonament) {
        System.out.println(abonament.getId() + " " + abonament.getTipAbonament() + " " + abonament.getPret());
        abonamentRepository.update(abonament.getId(), abonament.getTipAbonament(), abonament.getPret());
    }

    public void addAbonament(Abonament abonament) {
        abonamentRepository.addAbonament(abonament.getId(), abonament.getTipAbonament(), abonament.getPret());
    }

    public void deleteAbonament(String id) {
        abonamentRepository.deleteAbonament(id);
    }

    /////////
    public void addArc(String idAbonament, String idSala) {
        abonamentRepository.addArc(idAbonament, idSala);
    }

    public void deleteArc(String idAbonament, String idSala) {
        abonamentRepository.deleteArc(idAbonament, idSala);
    }

    //////////
    public Collection<Sala> getSala(String idAbonament) {
        return abonamentRepository.getSala(idAbonament);
    }

    public Collection<Sala> getSalaNotAb(String idAbonament) {
        Collection<Sala> saliAb = abonamentRepository.getSala(idAbonament);

        Collection<Sala> sali = sl.getAllSali();
        sali.removeAll(saliAb);
//        System.out.println("ceva");
        return sali;
    }

}
