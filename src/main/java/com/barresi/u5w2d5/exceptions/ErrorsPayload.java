package com.barresi.u5w2d5.exceptions;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ErrorsPayload {
    private String messaggio;
    private LocalDateTime time;
}
