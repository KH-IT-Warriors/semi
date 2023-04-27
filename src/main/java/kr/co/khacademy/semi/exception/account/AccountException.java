package kr.co.khacademy.semi.exception.account;

public class AccountException extends RuntimeException {
    public AccountException() {
        super();
    }

    public AccountException(String message) {
        super(message);
    }
}
