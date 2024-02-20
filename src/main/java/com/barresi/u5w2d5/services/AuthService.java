package com.barresi.u5w2d5.services;


import com.barresi.u5w2d5.entities.Dipendente;
import com.barresi.u5w2d5.payloads.NewDipendenteLogin;
import com.barresi.u5w2d5.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private DipendenteService dipendenteService;
    @Autowired
    private JWTTools jwtTools;

    public String authentication(NewDipendenteLogin payload){
        Dipendente dipendente = dipendenteService.findByemail(payload.getEmail());
        return jwtTools.createToken(dipendente);
    }
}
