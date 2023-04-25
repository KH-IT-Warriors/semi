package kr.co.khacademy.semi.exception.login.sub;

import kr.co.khacademy.semi.exception.login.LoginFailedException;

public class AccountIdNotFoundException extends LoginFailedException {
    public AccountIdNotFoundException() {
        super();
    }

    public AccountIdNotFoundException(String message) {
        super(message);
    }
}
