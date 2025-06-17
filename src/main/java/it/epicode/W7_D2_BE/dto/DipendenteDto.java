package it.epicode.W7_D2_BE.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class DipendenteDto {
    @NotEmpty(message = "il nome non può essere vuoto")
    private String nome;
    @NotEmpty(message = "il cognome non può essere vuoto")
    private String cognome;
    @NotEmpty(message = "l'username non può essere vuoto")
    private String username;
    @NotEmpty(message = "inserire l'email in un formato corretto")
    private String email;
}
