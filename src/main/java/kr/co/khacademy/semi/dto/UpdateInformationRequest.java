package kr.co.khacademy.semi.dto;

import kr.co.khacademy.semi.exception.join.sub.InvalidEmailException;
import kr.co.khacademy.semi.exception.join.sub.InvalidPhoneNumberException;
import lombok.Data;
import lombok.Value;

@Data
public class UpdateInformationRequest {
    private Long accountId;
    private String name;
    private String phoneNumber;
    private String email;


    private UpdateInformationRequest(Long accountId, String name, String phoneNumber, String email) {
        this.accountId = accountId;
        this.name = name;

        if (!validatesPhoneNumber(phoneNumber)) {
            throw new InvalidPhoneNumberException("잘못된 형식의 전화번호입니다.");
        }
        this.phoneNumber = phoneNumber;

        if (!validatesEmail(email)) {
            throw new InvalidEmailException("잘못된 형식의 이메일입니다.");
        }
        this.email = email;
    }

    public static UpdateInformationRequest of(Long accountId, String name, String phoneNumber, String email) {
        return new UpdateInformationRequest(accountId, name, phoneNumber, email);
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
