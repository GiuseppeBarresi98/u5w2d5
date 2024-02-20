package com.barresi.u5w2d5.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException{
    public NotFoundException(UUID id){
        super("Il dipendente con id " + id +" non è stato trovato");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
