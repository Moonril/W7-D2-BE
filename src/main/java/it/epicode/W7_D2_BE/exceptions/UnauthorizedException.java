package it.epicode.W7_D2_BE.exceptions;

//questa Runtime perchè...nei filter stiamo facendo override di un'altra classe astratta, non possiamo modificare la firma del metodo
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
