package kr.co.khacademy.semi.exception.join.sub;

import kr.co.khacademy.semi.exception.join.JoinFailedException;

public class InvalidEmailException extends JoinFailedException {
    public InvalidEmailException() {
        super();
    }

    public InvalidEmailException(String message) {
        super(message);
    }
}
