package com.barresi.u5w2d5.services;


import com.barresi.u5w2d5.entities.Dipendente;
import com.barresi.u5w2d5.entities.Dispositivo;
import com.barresi.u5w2d5.exceptions.NotFoundException;
import com.barresi.u5w2d5.payloads.NewDipendenteDTO;
import com.barresi.u5w2d5.payloads.NewDispositivoDTO;
import com.barresi.u5w2d5.repositories.DipendenteDAO;
import com.barresi.u5w2d5.repositories.DispositivoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DispositivoService {

    @Autowired
    DispositivoDAO dispositivoDAO;

    public Page<Dispositivo> getListaDispositivi(int pageNumber, int size, String orderBy) {
        if(size < 100 ) size = 100;
        Pageable pageable = PageRequest.of(pageNumber,size, Sort.by(orderBy));
        return dispositivoDAO.findAll(pageable);
    }

    public Dispositivo findDispositivoById(UUID id){
        return dispositivoDAO.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    public Dispositivo saveDispositivo(NewDispositivoDTO newDispositivo){
        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setTipo(newDispositivo.getTipo());
        dispositivo.setStato(newDispositivo.getStato());
        return dispositivoDAO.save(dispositivo);
    }

    public Dispositivo updateDispositivo(UUID dispositivoID,NewDispositivoDTO dispositivoDTO){
        Dispositivo found = this.findDispositivoById(dispositivoID);
        found.setStato(dispositivoDTO.getStato());
        found.setTipo(dispositivoDTO.getTipo());
        return dispositivoDAO.save(found);
    }

    public void deleteDispositivo(UUID dispositivoID){
        Dispositivo found = this.findDispositivoById(dispositivoID);
        dispositivoDAO.delete(found);
    }

}
