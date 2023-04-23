package domains.exceptions;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException(){
        super();
    }
    public InvalidPasswordException(String message) {
        super(message);
    }
}
