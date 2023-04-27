package kr.co.khacademy.semi.exception.account.sub;

import kr.co.khacademy.semi.exception.account.AccountException;

public class PhoneNumberNotFoundException extends AccountException {
    public PhoneNumberNotFoundException() {
        super();
    }

    public PhoneNumberNotFoundException(String message) {
        super(message);
    }
}
