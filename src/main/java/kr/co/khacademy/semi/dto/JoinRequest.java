package kr.co.khacademy.semi.dto;

import kr.co.khacademy.semi.exception.join.sub.InvalidEmailException;
import kr.co.khacademy.semi.exception.join.sub.InvalidPasswordException;
import kr.co.khacademy.semi.exception.join.sub.InvalidPhoneNumberException;
import kr.co.khacademy.semi.exception.join.sub.InvalidUsernameException;
import lombok.Data;
import lombok.NonNull;
import lombok.With;

@Data
public class JoinRequest {
    @NonNull
    String username;
    @With
    String plainPassword;
    String phoneNumber;
    String email;

    boolean idHasAlphabet;
    boolean idHasNumber;
    boolean idBetween6And10;

    boolean pwHasDownCase;
    boolean pwHasUpperCase;
    boolean pwHasNumber;
    boolean pwHasSpecialWord;
    boolean pwBetween8And15;

    private JoinRequest(String username, String plainPassword, String phoneNumber, String email){
        if (!validatesUsername(username)) {
            InvalidUsernameException invalidUsernameException = new InvalidUsernameException("잘못된 형식의 아이디입니다.");
            invalidUsernameException.printStackTrace();
            throw invalidUsernameException;
        }
        this.username = username;

        if (!validatesPassword(plainPassword)) {
            InvalidPasswordException invalidPasswordException = new InvalidPasswordException("잘못된 형식의 비밀번호입니다.");
            invalidPasswordException.printStackTrace();
            throw invalidPasswordException;
        }
        this.plainPassword = plainPassword;

        if (!validatesPhoneNumber(phoneNumber)) {
            InvalidPhoneNumberException invalidPhoneNumberException = new InvalidPhoneNumberException("잘못된 형식의 전화번호입니다.");
            invalidPhoneNumberException.printStackTrace();
            throw invalidPhoneNumberException;
        }
        this.phoneNumber = phoneNumber;

        if (!validatesEmail(email)) {
            InvalidEmailException invalidEmailException = new InvalidEmailException("잘못된 형식의 이메일입니다.");
            invalidEmailException.printStackTrace();
            throw invalidEmailException;
        }
        this.email = email;
    }

    public JoinRequest of(String username, String plainPassword, String phoneNumber, String email){
        return new JoinRequest(username, plainPassword, phoneNumber, email);
    }

    private boolean validatesUsername(String username) {
        String hasAlphabet = ".*[a-zA-Z].*?$";
        if (username.matches(hasAlphabet)) {
            this.idHasAlphabet = true;
        } else {
            return false;
        }
        String hasNumber = ".*[0-9].*?$";
        if (username.matches(hasNumber)) {
            this.idHasNumber = true;
        } else {
            return false;
        }
        String between6And10 = ".{6,10}";
        if (username.matches(between6And10)) {
            this.idBetween6And10 = true;
        } else {
            return false;
        }
        return true;
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

    private boolean validatesPhoneNumber(String phoneNumber) {
        String phoneNumberForm = "^01\\d\\d{8}$";
        if (phoneNumber.matches(phoneNumberForm)) {
            return true;
        }
        return false;
    }

    private boolean validatesEmail(String email) {
        String emailForm = "^[a-z0-9]+@.+(\\.com)$";
        if (email.matches(emailForm)) {
            return true;
        }
        return false;
    }
}
