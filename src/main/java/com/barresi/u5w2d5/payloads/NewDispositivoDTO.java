package com.barresi.u5w2d5.payloads;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class NewDispositivoDTO {
    @NotEmpty(message = "Il tipo è obbligatorio")
    private String tipo;

    @NotEmpty(message = "Lo stato è obbligatorio")
    private String stato;

    private UUID dipendente_id;
}
