package kr.co.khacademy.semi.model;

import kr.co.khacademy.semi.exception.join.sub.InvalidEmailException;
import kr.co.khacademy.semi.exception.join.sub.InvalidPhoneNumberException;
import kr.co.khacademy.semi.exception.profile.sub.InvalidBonusPointException;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.sql.Timestamp;

@Value
@Builder
public class Profile {
    @NonNull
    Long accountId;
    @NonNull
    String name;
    @NonNull
    String phoneNumber;
    @NonNull
    String email;
    Timestamp registeredTime;
    Timestamp recentConnection;
    Long bonusPoint;
    Long userGradeId;


    private Profile(
        Long accountId,
        String name,
        String phoneNumber,
        String email,
        Timestamp registeredTime,
        Timestamp recentConnection,
        Long bonusPoint,
        Long userGradeId
    ) {
        if ((phoneNumber != null) && !validatesPhoneNumber(phoneNumber)) {
            throw new InvalidPhoneNumberException();
        }
        if ((email != null) && !validatesEmail(email)) {
            throw new InvalidEmailException();
        }
        if ((bonusPoint != null) && bonusPoint < 0) {
            throw new InvalidBonusPointException();
        }
        this.accountId = accountId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.registeredTime = registeredTime;
        this.recentConnection = recentConnection;
        this.bonusPoint = bonusPoint;
        this.userGradeId = userGradeId;
    }

    private Boolean validatesPhoneNumber(String data) {
        String phoneNumberForm = "^01\\d\\d{8}$";
        return data.matches(phoneNumberForm);
    }

    private Boolean validatesEmail(String data) {
        String emailForm = "^[a-z0-9]+@.+(\\.com)$";
        return data.matches(emailForm);
    }
}
