package kr.co.khacademy.semi.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Password {

    Long accountId;
    String password;

    public static Password of(HttpServletRequest req) {
        String password = req.getParameter("password");
        Optional.ofNullable(password)
            .filter(Password::validatePassword)
            .orElseThrow(RuntimeException::new);
        return Password.builder()
            .password(password)
            .build();
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
}
