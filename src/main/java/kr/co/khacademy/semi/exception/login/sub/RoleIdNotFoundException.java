package kr.co.khacademy.semi.exception.login.sub;

import kr.co.khacademy.semi.exception.login.LoginFailedException;

public class RoleIdNotFoundException extends LoginFailedException {
    public RoleIdNotFoundException() {
        super();
    }

    public RoleIdNotFoundException(String message) {
        super(message);
    }
}
