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
    String profileImage;

    public static Profile of(Long id, String name, String phoneNumber, String email, Long mileage, Long gradeId, Timestamp created, Timestamp lastLogin, String profileImage) {
        Optional.ofNullable(phoneNumber)
            .filter(Profile::validatePhoneNumber)
            .orElseThrow(RuntimeException::new);
        Optional.ofNullable(email)
            .filter(Profile::validateEmail)
            .orElseThrow(RuntimeException::new);
        return Profile.builder()
            .accountId(id)
            .name(name)
            .phoneNumber(phoneNumber)
            .email(email)
            .mileage(mileage)
            .gradeId(gradeId)
            .created(created)
            .lastLogin(lastLogin)
            .profileImage(profileImage)
            .build();
    }

    public static Profile of(HttpServletRequest req) {
        Long id = (Long) req.getSession().getAttribute("account-id");
        String name = req.getParameter("name");
        String phoneNumber = req.getParameter("phone-number");
        String email = req.getParameter("email");
        Long mileage = Long.valueOf(req.getParameter("mileage"));
        Long gradeId = Long.valueOf(req.getParameter("grade-id"));
        Timestamp created = Timestamp.valueOf(req.getParameter("created"));
        Timestamp lastLogin = Timestamp.valueOf(req.getParameter("last-login"));
        String profileImage = Optional.ofNullable(req.getParameter("profile-image"))
            .orElse("");
        return Profile.of(id, name, phoneNumber, email, mileage, gradeId, created, lastLogin, profileImage);
    }

    public static Profile of(ResultSet resultSet) throws SQLException {
        Long accountId = resultSet.getLong("P.account_id");
        String name = resultSet.getString("P.name");
        String phoneNumber = resultSet.getString("P.phone_number");
        String email = resultSet.getString("P.email");
        Long mileage = resultSet.getLong("P.mileage");
        Long gradeId = resultSet.getLong("P.grade_id");
        Timestamp created = resultSet.getTimestamp("P.created");
        Timestamp lastLogin = resultSet.getTimestamp("P.last_login");
        String profileImage = resultSet.getString("P.profile_image");
        return Profile.builder()
            .accountId(accountId)
            .name(name)
            .phoneNumber(phoneNumber)
            .email(email)
            .mileage(mileage)
            .gradeId(gradeId)
            .created(created)
            .lastLogin(lastLogin)
            .profileImage(profileImage)
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
