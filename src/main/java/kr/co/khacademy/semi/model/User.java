package kr.co.khacademy.semi.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class User {

    Account account;
    Profile profile;

    public static User of(HttpServletRequest req) {
        return User.builder()
            .account(Account.of(req))
            .profile(Profile.of(req))
            .build();
    }

    public static User of(ResultSet resultSet) throws SQLException {
        Account account = Account.of(resultSet);
        Profile profile = Profile.of(resultSet);
        return User.builder()
            .account(account)
            .profile(profile)
            .build();
    }
}

// TODO: id, username, name, phone, email, role, mileage ...
