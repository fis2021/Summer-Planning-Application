package Exceptions;

public class ReservationAlreadyExistsException extends Exception {
    public ReservationAlreadyExistsException(String msg){
        super(msg);
    }
}
