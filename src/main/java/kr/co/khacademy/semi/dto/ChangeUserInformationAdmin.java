package kr.co.khacademy.semi.dto;


import kr.co.khacademy.semi.exception.update.sub.InvalidBonusPointException;
import lombok.Data;

@Data
public class ChangeUserInformationAdmin {
    private Long accountId;
    private String name;
    private String phoneNumber;
    private String email;
    private String grade;
    private int bonusPoint;

    private ChangeUserInformationAdmin(Long accountId, String name, String phoneNumber, String email, String grade,  int bonusPoint) {
        this.accountId = accountId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;

        this.grade = grade;
        if (bonusPoint > 0) {
            this.bonusPoint = bonusPoint;
        } else {
            throw new InvalidBonusPointException("보너스 포인트에는 0보다 큰 값을 입력해야합니다.");
        }
    }

    public static ChangeUserInformationAdmin of(Long accountId, String name, String phoneNumber, String email, String grade, int bonusPoint) {
        return new ChangeUserInformationAdmin(accountId, name, phoneNumber, email, grade, bonusPoint);
    }
}
