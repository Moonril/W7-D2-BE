package it.epicode.W7_D2_BE.dto;

import lombok.Data;

@Data
public class PrenotazioneDto {

    private String preferenze;

    private int viaggioId;
    private int dipendenteId;
}
