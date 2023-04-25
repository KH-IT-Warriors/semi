package kr.co.khacademy.semi.exception.login.sub;

import kr.co.khacademy.semi.exception.login.LoginFailedException;

public class PermissionIdNotFoundException extends LoginFailedException {
    public PermissionIdNotFoundException() {
        super();
    }

    public PermissionIdNotFoundException(String message) {
        super(message);
    }
}
