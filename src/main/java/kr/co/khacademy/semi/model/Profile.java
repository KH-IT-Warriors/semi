package kr.co.khacademy.semi.model;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
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


    public static Profile of(HttpServletRequest req) {
        Long id = (Long) req.getSession().getAttribute("accountId");
        String phoneNumber = req.getParameter("phone-number");
        String email = req.getParameter("email");
        Optional.ofNullable(phoneNumber)
            .filter(Profile::validatePhoneNumber)
            .orElseThrow(RuntimeException::new);
        Optional.ofNullable(email)
            .filter(Profile::validateEmail)
            .orElseThrow(RuntimeException::new);
        return Profile.builder()
            .accountId(id)
            .phoneNumber(phoneNumber)
            .email(email)
            .build();
    }

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

    private static boolean validatePhoneNumber(String phoneNumber) {
        String phoneNumberForm = "^01\\d\\d{8}$";
        return phoneNumber.matches(phoneNumberForm);
    }
    private static boolean validateEmail(String email) {
        String emailForm = "^[a-z0-9]+@.+(\\.com)$";
        return email.matches(emailForm);
    }
}
