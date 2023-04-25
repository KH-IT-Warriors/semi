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
        invalidateSession(previousHttpSession);
    }

    public void logout(Long accountId) {
        HttpSession httpSession = accountSessionMap.remove(accountId);
        invalidateSession(httpSession);
    }

    private void invalidateSession(HttpSession httpSession) {
        Optional.ofNullable(httpSession).ifPresent(HttpSession::invalidate);
    }

    public void remove(Long accountId) {
        accountSessionMap.remove(accountId);
    }

    public Boolean isAccountAuthenticated(HttpSession httpSession) {
        Long accountId = (Long) httpSession.getAttribute("accountId");
        return Optional.ofNullable(accountId).map(accountSessionMap::containsKey).orElse(Boolean.FALSE);
    }
}
