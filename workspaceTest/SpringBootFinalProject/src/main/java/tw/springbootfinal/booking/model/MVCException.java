package tw.springbootfinal.booking.model;

/**
 * @author
 **/
public class MVCException extends RuntimeException{
    public MVCException(String message) {
        super(message);
    }

    public MVCException() {
    }
}
