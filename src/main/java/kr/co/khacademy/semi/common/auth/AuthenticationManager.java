package kr.co.khacademy.semi.common.auth;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class AuthenticationManager {

    private static final AuthenticationManager instance = new AuthenticationManager();

    private static final Map<Long, HttpSession> accountSessionMap = new ConcurrentHashMap<>();

    private AuthenticationManager() {
    }

    public static AuthenticationManager getInstance() {
        return instance;
    }

    public void login(Long accountId, HttpSession httpSession) {
        httpSession.setAttribute("accountId", accountId);
        HttpSession previousHttpSession = accountSessionMap.put(accountId, httpSession);
        Optional.ofNullable(previousHttpSession).ifPresent(HttpSession::invalidate);
    }
}
