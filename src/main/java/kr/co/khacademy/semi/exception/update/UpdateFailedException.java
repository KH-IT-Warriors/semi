package kr.co.khacademy.semi.exception.update;

public class UpdateFailedException extends RuntimeException {
    public UpdateFailedException() {
        super();
    }

    public UpdateFailedException(String message) {
        super(message);
    }
}
