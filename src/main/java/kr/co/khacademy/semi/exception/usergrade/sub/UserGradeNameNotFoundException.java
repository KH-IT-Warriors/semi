package kr.co.khacademy.semi.exception.usergrade.sub;

import kr.co.khacademy.semi.exception.usergrade.UserGradeException;

public class UserGradeNameNotFoundException extends UserGradeException {
    public UserGradeNameNotFoundException() {
        super();
    }

    public UserGradeNameNotFoundException(String message) {
        super(message);
    }
}
