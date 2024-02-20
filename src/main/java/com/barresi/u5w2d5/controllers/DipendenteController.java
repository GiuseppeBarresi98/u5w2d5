package com.barresi.u5w2d5.controllers;


import com.barresi.u5w2d5.entities.Dipendente;
import com.barresi.u5w2d5.exceptions.BadRequestException;
import com.barresi.u5w2d5.payloads.NewDipendenteDTO;
import com.barresi.u5w2d5.services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {
    @Autowired
    DipendenteService dipendenteService;

    @GetMapping
    public Page<Dipendente> getListaDipendenti(@RequestParam(defaultValue = "0") int pagina, @RequestParam(defaultValue = "10") int size,@RequestParam(defaultValue = "cognome") String orderBy){
        return dipendenteService.getListaDipendenti(pagina, size, orderBy);
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Dipendente findDipendenteById(@PathVariable UUID id){
        return this.dipendenteService.findDipendenteById(id);
    }

    @PutMapping("/{id}")
    public Dipendente updateDipendente(@PathVariable UUID id,@RequestBody NewDipendenteDTO dipendenteDTO){
        return dipendenteService.updateDipendente(id, dipendenteDTO);
    }

    @DeleteMapping("/{id}")
    public void findAndDeleteDipendente(@PathVariable UUID id){
        dipendenteService.deleteDipendente(id);
    }

    @PatchMapping("/{id}/uploadImg")
    public String uploadImg(@PathVariable UUID id, @RequestParam("image")MultipartFile image) throws IOException{
        return dipendenteService.updateImg(id,image);
    }




}
