package kr.co.khacademy.semi.exception.join.sub;

import kr.co.khacademy.semi.exception.join.JoinFailedException;

public class InvalidUsernameException extends JoinFailedException {
    public InvalidUsernameException() {
        super();
    }

    public InvalidUsernameException(String message) {
        super(message);
    }
}
