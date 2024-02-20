package com.barresi.u5w2d5.services;


import com.barresi.u5w2d5.entities.Dipendente;
import com.barresi.u5w2d5.entities.Role;
import com.barresi.u5w2d5.payloads.NewDipendenteDTO;
import com.barresi.u5w2d5.payloads.NewDipendenteLogin;
import com.barresi.u5w2d5.repositories.DipendenteDAO;
import com.barresi.u5w2d5.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private DipendenteService dipendenteService;
    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private    PasswordEncoder passwordEncoder;

    @Autowired
     private  DipendenteDAO dipendenteDAO;

    public String authentication(NewDipendenteLogin payload){
        Dipendente dipendente = dipendenteService.findByemail(payload.getEmail());
        return jwtTools.createToken(dipendente);
    }


    public Dipendente saveDipendente(NewDipendenteDTO newDipendente){
        Dipendente dipendente = new Dipendente();
        dipendente.setNome(newDipendente.getNome());
        dipendente.setCognome(newDipendente.getCognome());
        dipendente.setUsername(newDipendente.getUsername());
        dipendente.setEmail(newDipendente.getEmail());
        dipendente.setPassword(passwordEncoder.encode(newDipendente.getPassword()));
        dipendente.setRole(Role.USER);
        return dipendenteDAO.save(dipendente);
    }
}
