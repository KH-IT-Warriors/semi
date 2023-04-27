package kr.co.khacademy.semi.exception.profile.sub;

import kr.co.khacademy.semi.exception.profile.ProfileException;

public class ProfileIdNotFoundException extends ProfileException {
    public ProfileIdNotFoundException() {
        super();
    }

    public ProfileIdNotFoundException(String message) {
        super(message);
    }
}
