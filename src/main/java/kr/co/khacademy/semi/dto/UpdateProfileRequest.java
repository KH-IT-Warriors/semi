package kr.co.khacademy.semi.dto;

import kr.co.khacademy.semi.common.validator.AccountDataValidator;
import kr.co.khacademy.semi.exception.join.sub.InvalidEmailException;
import kr.co.khacademy.semi.exception.join.sub.InvalidPhoneNumberException;
import lombok.Data;

@Data
public class UpdateProfileRequest {
    AccountDataValidator accountDataValidator = AccountDataValidator.getInstance();

    private Long accountId;
    private String name;
    private String phoneNumber;
    private String email;


    private UpdateProfileRequest(Long accountId, String name, String phoneNumber, String email) {
        this.accountId = accountId;
        this.name = name;

        if (!accountDataValidator.validatesPhoneNumber(phoneNumber)) {
            throw new InvalidPhoneNumberException("잘못된 형식의 전화번호입니다.");
        }
        this.phoneNumber = phoneNumber;

        if (!accountDataValidator.validatesEmail(email)) {
            throw new InvalidEmailException("잘못된 형식의 이메일입니다.");
        }
        this.email = email;
    }

    public static UpdateProfileRequest of(Long accountId, String name, String phoneNumber, String email) {
        return new UpdateProfileRequest(accountId, name, phoneNumber, email);
    }
}
