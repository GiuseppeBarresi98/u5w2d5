package com.barresi.u5w2d5.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Dipendente {
    @Id
    @GeneratedValue
    private UUID id;
    private String username;
    private String nome;
    private String cognome;
    private String email;
    private String avatar;

    @OneToMany(mappedBy = "dipendente")
    private List<Dispositivo> lista_dispositivi;

    public Dipendente(String username,String nome,String cognome,String email){
        this.username= username;
        this.nome= nome;
        this.cognome= cognome;
        this.email = email;
    }



}