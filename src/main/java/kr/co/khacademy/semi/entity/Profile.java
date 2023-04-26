package kr.co.khacademy.semi.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.sql.Timestamp;

@Data
@Builder
public class Profile {
    @NonNull
    private Long accountId;
    @NonNull
    private String name;
    @NonNull
    private String phoneNumber;
    @NonNull
    private String email;
    private Timestamp registeredTime;
    private Timestamp recentConnection;
    private Long bonusPoint;
    private Long userGradeId;
}
