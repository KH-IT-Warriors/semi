package kr.co.khacademy.semi.dto;

import kr.co.khacademy.semi.exception.profile.sub.InvalidBonusPointException;

import lombok.Data;

@Data
public class ProfileByAdminPutRequest {
    private Long accountId;
    private String name;
    private String phoneNumber;
    private String email;
    private Long gradeId;
    private Long bonusPoint;

    private ProfileByAdminPutRequest(Long accountId, String name, String phoneNumber, String email, Long gradeId, Long bonusPoint) {
        this.accountId = accountId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;

        this.gradeId = gradeId;
        if (bonusPoint > 0) {
            this.bonusPoint = bonusPoint;
        } else {
            throw new InvalidBonusPointException("보너스 포인트에는 0보다 큰 값을 입력해야합니다.");
        }
    }

    public static ProfileByAdminPutRequest of(Long accountId, String name, String phoneNumber, String email, Long gradeId, Long bonusPoint) {
        return new ProfileByAdminPutRequest(accountId, name, phoneNumber, email, gradeId, bonusPoint);
    }
}