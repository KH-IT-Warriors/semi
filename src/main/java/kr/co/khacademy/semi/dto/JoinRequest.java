package kr.co.khacademy.semi.dto;

import kr.co.khacademy.semi.common.validator.AccountDataValidator;
import kr.co.khacademy.semi.exception.join.sub.InvalidEmailException;
import kr.co.khacademy.semi.exception.join.sub.InvalidPasswordException;
import kr.co.khacademy.semi.exception.join.sub.InvalidPhoneNumberException;
import kr.co.khacademy.semi.exception.join.sub.InvalidUsernameException;
import lombok.Data;
import lombok.NonNull;
import lombok.With;

@Data
public class JoinRequest {
    AccountDataValidator accountDataValidator = AccountDataValidator.getInstance();

    @NonNull
    String username;
    @With
    String plainPassword;
    String name;
    String phoneNumber;
    String email;

    private JoinRequest(String username, String plainPassword, String name, String phoneNumber, String email){
        if (!accountDataValidator.validatesUsername(username)) {
            InvalidUsernameException invalidUsernameException = new InvalidUsernameException("잘못된 형식의 아이디입니다.");
            invalidUsernameException.printStackTrace();
            throw invalidUsernameException;
        }
        this.username = username;

        if (!accountDataValidator.validatesPassword(plainPassword)) {
            InvalidPasswordException invalidPasswordException = new InvalidPasswordException("잘못된 형식의 비밀번호입니다.");
            invalidPasswordException.printStackTrace();
            throw invalidPasswordException;
        }
        this.plainPassword = plainPassword;

        this.name = name;

        if (!accountDataValidator.validatesPhoneNumber(phoneNumber)) {
            InvalidPhoneNumberException invalidPhoneNumberException = new InvalidPhoneNumberException("잘못된 형식의 전화번호입니다.");
            invalidPhoneNumberException.printStackTrace();
            throw invalidPhoneNumberException;
        }
        this.phoneNumber = phoneNumber;

        if (!accountDataValidator.validatesEmail(email)) {
            InvalidEmailException invalidEmailException = new InvalidEmailException("잘못된 형식의 이메일입니다.");
            invalidEmailException.printStackTrace();
            throw invalidEmailException;
        }
        this.email = email;
    }

    public static JoinRequest of(String username, String plainPassword, String name, String phoneNumber, String email){
        return new JoinRequest(username, plainPassword, name, phoneNumber, email);
    }
}
