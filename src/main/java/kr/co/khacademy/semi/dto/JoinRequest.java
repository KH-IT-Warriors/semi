package kr.co.khacademy.semi.dto;

import domains.exceptions.InvalidPasswordException;
import lombok.Data;
import lombok.NonNull;
import lombok.Value;
import lombok.With;

@Data
public class JoinRequest {
    String username;
    @With
    String plainPassword;
    boolean hasDownCase;
    boolean hasUpperCase;
    boolean hasNumber;
    boolean hasSpecialWord;
    boolean between8And16;

    private JoinRequest(String username, String plainPassword){
        if (!validatesPassword(plainPassword)) {
            InvalidPasswordException invalidPasswordException = new InvalidPasswordException("잘못된 형식의 비밀번호입니다.");
            invalidPasswordException.printStackTrace();
            throw invalidPasswordException;
        }
        this.username = username;
        this.plainPassword = plainPassword;
    }

    private boolean validatesPassword(String plainPassword) {
        String hasDownCase = ".*[a-z].*?$";
        if (plainPassword.matches(hasDownCase)) {
            this.hasDownCase = true;
        } else {
            return false;
        }
        String hasUpperCase = ".*[A-Z].*?$";
        if (plainPassword.matches(hasUpperCase)) {
            this.hasUpperCase = true;
        } else {
            return false;
        }
        String hasNumber = ".*[0-9].*?$";
        if (plainPassword.matches(hasNumber)) {
            this.hasNumber = true;
        } else {
            return false;
        }
        String hasSpecialWord = ".*[!@#$%^&*()\\-=_+\\[\\]{};':\\\"\\\\|,.<>/?].*?$";
        if (plainPassword.matches(hasSpecialWord)) {
            this.hasSpecialWord = true;
        } else {
            return false;
        }
        String between8And16 = ".{8,16}";
        if (plainPassword.matches(between8And16)) {
            this.between8And16 = true;
        } else {
            return false;
        }
        return true;
    }
}
