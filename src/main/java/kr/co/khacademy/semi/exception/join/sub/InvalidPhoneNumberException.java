package kr.co.khacademy.semi.exception.join.sub;

import kr.co.khacademy.semi.exception.join.JoinFailedException;

public class InvalidPhoneNumberException extends JoinFailedException {
    public InvalidPhoneNumberException() {
        super();
    }

    public InvalidPhoneNumberException(String message) {
        super(message);
    }
}
