package kr.co.khacademy.semi.exception.join.sub;

import kr.co.khacademy.semi.exception.join.JoinFailedException;

public class InvalidPasswordException extends JoinFailedException {
    public InvalidPasswordException() {
        super();
    }

    public InvalidPasswordException(String message) {
        super(message);
    }
}
