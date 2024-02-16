package com.barresi.u5w2d5.services;


import com.barresi.u5w2d5.entities.Dipendente;
import com.barresi.u5w2d5.exceptions.NotFoundException;
import com.barresi.u5w2d5.payloads.NewDipendenteDTO;
import com.barresi.u5w2d5.repositories.DipendenteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DipendenteService {
    @Autowired
    DipendenteDAO dipendenteDAO;

    public Page<Dipendente> getListaDipendenti(int pageNumber,int size,String orderBy) {
        if(size < 100 ) size = 100;
        Pageable pageable = PageRequest.of(pageNumber,size, Sort.by(orderBy));
        return dipendenteDAO.findAll(pageable);
    }

    public Dipendente findDipendenteById(UUID id){
        return dipendenteDAO.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    public Dipendente saveDipendente(NewDipendenteDTO newDipendente){
        Dipendente dipendente = new Dipendente();
        dipendente.setNome(newDipendente.getNome());
        dipendente.setCognome(newDipendente.getCognome());
        dipendente.setUsername(newDipendente.getUsername());
        dipendente.setEmail(newDipendente.getEmail());
        return dipendenteDAO.save(dipendente);
    }

    public Dipendente updateDipendente(UUID dipendenteID,NewDipendenteDTO dipendeteDTO){
        Dipendente found = this.findDipendenteById(dipendenteID);
        found.setUsername(dipendeteDTO.getUsername());
        found.setNome(dipendeteDTO.getNome());
        found.setCognome(dipendeteDTO.getCognome());
        found.setEmail(dipendeteDTO.getEmail());
        return dipendenteDAO.save(found);
    }
}
