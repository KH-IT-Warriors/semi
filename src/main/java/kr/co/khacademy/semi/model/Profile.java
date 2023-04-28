package kr.co.khacademy.semi.model;


import lombok.Builder;
import lombok.Value;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

@Value(staticConstructor = "of")
@Builder
public class Profile {

    Long accountId;
    String name;
    String phoneNumber;
    String email;
    Long mileage;
    Long gradeId;
    Timestamp created;
    Timestamp lastLogin;


    public static Profile of(ResultSet resultSet) throws SQLException {
        Long accountId = resultSet.getLong("account_id");
        String name = resultSet.getString("name");
        String phoneNumber = resultSet.getString("phone_number");
        String email = resultSet.getString("email");
        Long mileage = resultSet.getLong("mileage");
        Long gradeId = resultSet.getLong("grade_id");
        Timestamp created = resultSet.getTimestamp("created");
        Timestamp lastLogin = resultSet.getTimestamp("last_login");
        return Profile.builder()
            .accountId(accountId)
            .name(name)
            .phoneNumber(phoneNumber)
            .email(email)
            .mileage(mileage)
            .gradeId(gradeId)
            .created(created)
            .lastLogin(lastLogin)
            .build();
    }
}