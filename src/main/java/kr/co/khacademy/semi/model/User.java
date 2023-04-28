package kr.co.khacademy.semi.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class User {

    Long id;
    String username;
    String name;
    String phoneNumber;
    String email;
    String role;
    String grade;
    Long mileage;
    Timestamp created;

    public static User of(ResultSet resultSet) throws SQLException {
        return User.builder()
            .id(resultSet.getLong("id"))
            .username(resultSet.getString("username"))
            .name(resultSet.getString("name"))
            .phoneNumber(resultSet.getString("phone_number"))
            .email(resultSet.getString("email"))
            .role(resultSet.getString("role_name"))
            .grade(resultSet.getString("grade_name"))
            .mileage(resultSet.getLong("mileage"))
            .created(resultSet.getTimestamp("created"))
            .build();
    }
}

// TODO: id, username, name, phone, email, role, mileage ...
