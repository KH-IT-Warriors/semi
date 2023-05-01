package kr.co.khacademy.semi.model;

import lombok.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class User {

    Account account;
    Profile profile;
    Role role;

    public static User of(HttpServletRequest req) {
        return User.builder()
            .account(Account.of(req))
            .profile(Profile.of(req))
            .build();
    }

    public static User of(ResultSet resultSet) throws SQLException {
        Account account = Account.of(resultSet);
        Profile profile = Profile.of(resultSet);
        Role role = Role.of(resultSet);
        return User.builder()
            .account(account)
            .profile(profile)
            .role(role)
            .build();
    }
}
