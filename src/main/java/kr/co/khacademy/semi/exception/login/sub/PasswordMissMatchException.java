package kr.co.khacademy.semi.exception.login.sub;

import kr.co.khacademy.semi.exception.login.LoginFailedException;

public class PasswordMissMatchException extends LoginFailedException {
    public PasswordMissMatchException() {
        super();
    }

    public PasswordMissMatchException(String message) {
        super(message);
    }
}
