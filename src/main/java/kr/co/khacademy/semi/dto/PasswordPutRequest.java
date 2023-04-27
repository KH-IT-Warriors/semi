package kr.co.khacademy.semi.dto;

import kr.co.khacademy.semi.common.validator.AccountDataValidator;
import kr.co.khacademy.semi.exception.join.sub.InvalidPasswordException;
import lombok.Data;
import lombok.NonNull;

@Data
public class PasswordPutRequest {
    AccountDataValidator accountDataValidator = AccountDataValidator.getInstance();

    @NonNull
    Long id;
    @NonNull
    String plainPassword;

    private PasswordPutRequest(Long id, String plainPassword) {
        this.id = id;

        if (!accountDataValidator.validatesPassword(plainPassword)) {
            InvalidPasswordException invalidPasswordException = new InvalidPasswordException("잘못된 형식의 비밀번호입니다.");
            invalidPasswordException.printStackTrace();
            throw new InvalidPasswordException();
        }
        this.plainPassword = plainPassword;
    }

    public static PasswordPutRequest of(Long id, String password) {
        return new PasswordPutRequest(id, password);
    }
}
