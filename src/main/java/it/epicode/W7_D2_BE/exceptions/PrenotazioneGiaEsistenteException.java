package it.epicode.W7_D2_BE.exceptions;

public class PrenotazioneGiaEsistenteException extends RuntimeException {
    public PrenotazioneGiaEsistenteException(String message) {
        super(message);
    }
}
