package kr.co.khacademy.semi.exception.join;

public class JoinFailedException extends RuntimeException {

    public JoinFailedException() {
        super();
    }

    public JoinFailedException(String message) {
        super(message);
    }
}
