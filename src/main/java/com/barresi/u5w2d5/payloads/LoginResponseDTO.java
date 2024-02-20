package com.barresi.u5w2d5.payloads;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class LoginResponseDTO {
    String accessToken;
}
