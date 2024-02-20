package com.barresi.u5w2d5.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class NewDipendenteDTO {
    @NotEmpty(message = "Lo username è obbligatorio")
    @Size(min = 3,max = 20,message = "Lo username deve essere di almeno 3 caratteri e massimo 20")
    private String username;

    @NotEmpty(message = "IL nome è obbligatorio")
    @Size(min = 3,max = 20,message = "IL nome deve essere di almeno 3 caratteri e massimo 20")
    private String nome;

    @NotEmpty(message = "IL cognome è obbligatorio")
    @Size(min = 3,max = 20,message = "IL cognome deve essere di almeno 3 caratteri e massimo 20")
    private String cognome;

    @NotEmpty(message = "L'email è obbligatoria")
    private String email;

    @NotEmpty(message = "La password è obbligatoria")
    private String password;
}
