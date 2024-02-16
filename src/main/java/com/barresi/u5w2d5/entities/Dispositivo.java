package com.barresi.u5w2d5.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Dispositivo {
    @Id
    @GeneratedValue
    private UUID id;
    private String tipo;
    private String stato;
    @ManyToOne
    @JoinColumn(name = "dipendente_id")
    private Dipendente dipendente;

    Dispositivo(String tipo,String stato,Dipendente dipendente){
        this.tipo = tipo;
        this.stato = stato;
    }

}
