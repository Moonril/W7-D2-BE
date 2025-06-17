package it.epicode.W7_D2_BE.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginDto {
    @NotEmpty(message = "inserisci un username")
    private String username;
    @NotEmpty(message = "inserisci una password")
    private String password;
}
