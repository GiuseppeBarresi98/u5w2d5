package com.barresi.u5w2d5.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
public class ErrorsPayloadList extends  ErrorsPayload{
    private List<String> errorList;
    public ErrorsPayloadList(String messaggio, LocalDateTime time, List<String> errorList) {
        super(messaggio, time);
        this.errorList = errorList;
    }
}
