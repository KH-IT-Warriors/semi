package kr.co.khacademy.semi.exception.usergrade;

public class UserGradeException extends RuntimeException {
    public UserGradeException() {
        super();
    }

    public UserGradeException(String message) {
        super(message);
    }
}
