package kr.co.khacademy.semi.dto;

import kr.co.khacademy.semi.common.validator.AccountDataValidator;
import kr.co.khacademy.semi.exception.join.sub.InvalidEmailException;
import kr.co.khacademy.semi.exception.join.sub.InvalidPhoneNumberException;
import lombok.Data;

@Data
public class ProfilePutRequest {
    AccountDataValidator accountDataValidator = AccountDataValidator.getInstance();

    Long accountId;
    String name;
    String phoneNumber;
    String email;


    private ProfilePutRequest(Long accountId, String name, String phoneNumber, String email) {
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

    public static ProfilePutRequest of(Long accountId, String name, String phoneNumber, String email) {
        return new ProfilePutRequest(accountId, name, phoneNumber, email);
    }
}
