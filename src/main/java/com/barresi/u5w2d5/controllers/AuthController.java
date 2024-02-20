package com.barresi.u5w2d5.controllers;

import com.barresi.u5w2d5.entities.Dipendente;
import com.barresi.u5w2d5.exceptions.BadRequestException;
import com.barresi.u5w2d5.payloads.LoginResponseDTO;
import com.barresi.u5w2d5.payloads.NewDipendenteDTO;
import com.barresi.u5w2d5.payloads.NewDipendenteLogin;
import com.barresi.u5w2d5.services.AuthService;
import com.barresi.u5w2d5.services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private DipendenteService dipendenteService;


    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody NewDipendenteLogin payload) {
        return new LoginResponseDTO(authService.authentication(payload));
    }


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente saveDipendente(@RequestBody @Validated NewDipendenteDTO dipendenteDTO, BindingResult validation){
        if (validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }
        return authService.saveDipendente(dipendenteDTO);
    }


}