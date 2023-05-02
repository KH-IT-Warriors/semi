package kr.co.khacademy.semi.model;

import kr.co.khacademy.semi.common.PasswordEncryptor;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Account {

    Long id;
    Long statusId;
    Long roleId;
    String username;
    String password;

    public static Account of(Long id, Long statusId, Long roleId, String username, String password) {
        Optional.ofNullable(username)
            .filter(Account::validateUsername)
            .orElseThrow(RuntimeException::new);
        Optional<String> optionalPassword = Optional.ofNullable(password);
        if (optionalPassword.isPresent()) {
            password = optionalPassword.filter(Account::validatePassword)
                .map(Account::encryptPassword)
                .orElseThrow(RuntimeException::new);
        }
        return Account.builder()
            .id(id)
            .statusId(statusId)
            .roleId(roleId)
            .username(username)
            .password(password)
            .build();
    }

    public static Account of(HttpServletRequest req) {
        Long id = Long.valueOf(req.getParameter("account-id"));
        Long statusId = Long.valueOf(req.getParameter("status-id"));
        Long roleId = Long.valueOf(req.getParameter("role-id"));
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        return of(id, statusId, roleId, username, password);
    }

    public static Account of(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("A.id");
        Long statusId = resultSet.getLong("A.status_id");
        Long roleId = resultSet.getLong("A.role_id");
        String username = resultSet.getString("A.username");
        String password = resultSet.getString("A.password");
        return Account.builder()
            .id(id)
            .statusId(statusId)
            .roleId(roleId)
            .username(username)
            .password(password)
            .build();
    }

    private static boolean validateUsername(String username) {
        String hasAlphabet = ".*[a-zA-Z].*?$";
        String hasNumber = ".*[0-9].*?$";
        if (!username.matches(hasAlphabet)) {
            return false;
        }
        if (!username.matches(hasNumber)) {
            return false;
        }
        if (username.length() < 6 || username.length() > 10) {
            return false;
        }
        return true;
    }

    private static boolean validatePassword(String password) {
        String hasUpperCase = ".*[A-Z].*?$";
        String hasDownCase = ".*[a-z].*?$";
        String hasNumber = ".*[0-9].*?$";
        String hasSpecialWord = ".*[!@#$%^&*()\\-=_+\\[\\]{};':\\\"\\\\|,.<>/?].*?$";
        if (!password.matches(hasUpperCase)) {
            return false;
        }
        if (!password.matches(hasDownCase)) {
            return false;
        }
        if (!password.matches(hasNumber)) {
            return false;
        }
        if (!password.matches(hasSpecialWord)) {
            return false;
        }
        return true;
    }

    private static String encryptPassword(String plainPassword) {
        return PasswordEncryptor.get().encryptPassword(plainPassword);
    }
}

