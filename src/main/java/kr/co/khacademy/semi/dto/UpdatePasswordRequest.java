package kr.co.khacademy.semi.dto;

import kr.co.khacademy.semi.exception.join.sub.InvalidPasswordException;
import lombok.Data;
import lombok.NonNull;

@Data
public class UpdatePasswordRequest {
    @NonNull
    private Long id;
    @NonNull
    private String password;

    boolean pwHasDownCase;
    boolean pwHasUpperCase;
    boolean pwHasNumber;
    boolean pwHasSpecialWord;
    boolean pwBetween8And15;

    private UpdatePasswordRequest(Long id, String password) {
        this.id = id;

        if (!validatesPassword(password)) {
            InvalidPasswordException invalidPasswordException = new InvalidPasswordException("잘못된 형식의 비밀번호입니다.");
            invalidPasswordException.printStackTrace();
            throw new InvalidPasswordException();
        }
        this.password = password;
    }

    public static UpdatePasswordRequest of(Long id, String password) {
        return new UpdatePasswordRequest(id, password);
    }

    private boolean validatesPassword(String plainPassword) {
        String hasDownCase = ".*[a-z].*?$";
        if (plainPassword.matches(hasDownCase)) {
            this.pwHasDownCase = true;
        } else {
            return false;
        }
        String hasUpperCase = ".*[A-Z].*?$";
        if (plainPassword.matches(hasUpperCase)) {
            this.pwHasUpperCase = true;
        } else {
            return false;
        }
        String hasNumber = ".*[0-9].*?$";
        if (plainPassword.matches(hasNumber)) {
            this.pwHasNumber = true;
        } else {
            return false;
        }
        String hasSpecialWord = ".*[!@#$%^&*()\\-=_+\\[\\]{};':\\\"\\\\|,.<>/?].*?$";
        if (plainPassword.matches(hasSpecialWord)) {
            this.pwHasSpecialWord = true;
        } else {
            return false;
        }
        String between8And15 = ".{8,15}";
        if (plainPassword.matches(between8And15)) {
            this.pwBetween8And15 = true;
        } else {
            return false;
        }
        return true;
    }
}
