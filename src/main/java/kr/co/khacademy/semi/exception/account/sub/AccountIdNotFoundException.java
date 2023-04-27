package kr.co.khacademy.semi.exception.account.sub;

import kr.co.khacademy.semi.exception.account.AccountException;

public class AccountIdNotFoundException extends AccountException {
    public AccountIdNotFoundException() {
        super();
    }

    public AccountIdNotFoundException(String message) {
        super(message);
    }
}
