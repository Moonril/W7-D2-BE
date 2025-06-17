package it.epicode.W7_D2_BE.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ViaggioDto {
    @NotEmpty(message = "Inserisci una destinazione")
    private String destinazione;
    @NotNull(message = "Inserisci una data corretta")
    private LocalDate dataViaggio;
}
