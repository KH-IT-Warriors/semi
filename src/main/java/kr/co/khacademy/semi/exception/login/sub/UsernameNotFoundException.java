package kr.co.khacademy.semi.exception.login.sub;

import kr.co.khacademy.semi.exception.login.LoginFailedException;

public class UsernameNotFoundException extends LoginFailedException {
    public UsernameNotFoundException() {
        super();
    }

    public UsernameNotFoundException(String message) {
        super(message);
    }
}
