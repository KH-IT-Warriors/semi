package kr.co.khacademy.semi.exception.profile.sub;

import kr.co.khacademy.semi.exception.profile.ProfileException;

public class InvalidBonusPointException extends ProfileException {
    public InvalidBonusPointException() {
        super();
    }

    public InvalidBonusPointException(String message) {
        super(message);
    }
}
