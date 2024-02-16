package com.barresi.u5w2d5.controllers;


import com.barresi.u5w2d5.entities.Dipendente;
import com.barresi.u5w2d5.entities.Dispositivo;
import com.barresi.u5w2d5.exceptions.BadRequestException;
import com.barresi.u5w2d5.payloads.NewDipendenteDTO;
import com.barresi.u5w2d5.payloads.NewDispositivoDTO;
import com.barresi.u5w2d5.repositories.DispositivoDAO;
import com.barresi.u5w2d5.services.DispositivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/dispositivi")
public class DispositivoController {
    @Autowired
    DispositivoService dispositivoService;

    @GetMapping
    public Page<Dispositivo> getListaDispositivo(@RequestParam(defaultValue = "0") int pagine, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "tipo") String orderBy){
        return dispositivoService.getListaDispositivi(pagine,size,orderBy);
    }

    @PostMapping
    public Dispositivo saveDispositivo(@RequestBody @Validated NewDispositivoDTO dispositivoDTO, BindingResult validation){
        if (validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }
        return dispositivoService.saveDispositivo(dispositivoDTO);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Dispositivo findDispositivoById(@PathVariable UUID id){
        return this.dispositivoService.findDispositivoById(id);
    }

    @PutMapping("/{id}")
    public Dispositivo updateDispositivo(@PathVariable UUID id,@RequestBody NewDispositivoDTO dispositivoDTO){
        return dispositivoService.updateDispositivo(id, dispositivoDTO);
    }

    @DeleteMapping("/{id}")
    public void findAndDeleteDispositivo(@PathVariable UUID id){
        dispositivoService.deleteDispositivo(id);
    }

    @PostMapping("/{id}/assegna-dispositivo")
    public Dispositivo assignDispositivo(@PathVariable UUID id,@RequestBody NewDispositivoDTO dispositivoDTO) {
       return dispositivoService.assignDispositivo(id, dispositivoDTO);
    }


}
