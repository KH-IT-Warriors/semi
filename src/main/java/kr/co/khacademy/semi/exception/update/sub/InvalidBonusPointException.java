package kr.co.khacademy.semi.exception.update.sub;

import kr.co.khacademy.semi.exception.update.UpdateFailedException;

public class InvalidBonusPointException extends UpdateFailedException {
    public InvalidBonusPointException() {
        super();
    }

    public InvalidBonusPointException(String message) {
        super(message);
    }
}
