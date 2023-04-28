package kr.co.khacademy.semi.model;

import kr.co.khacademy.semi.common.PasswordEncryptor;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import javax.servlet.http.HttpServletRequest;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Login {

    Long id;
    String password;

    public static Login of(HttpServletRequest req) {
        Long id = (Long) req.getSession().getAttribute("accountId");
        String plainPassword = req.getParameter("password");
        String password = PasswordEncryptor.get().encryptPassword(plainPassword);
        return Login.builder()
            .id(id)
            .password(password)
            .build();
    }
}
