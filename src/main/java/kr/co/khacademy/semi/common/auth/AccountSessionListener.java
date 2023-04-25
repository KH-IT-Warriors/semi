package kr.co.khacademy.semi.common.auth;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Optional;

@WebListener
public class AccountSessionListener implements HttpSessionListener {

    private static final Integer MAX_INACTIVE_INTERVAL = 30 * 60;

    private static final AuthenticationManager authenticationManager = AuthenticationManager.getInstance();

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        se.getSession().setMaxInactiveInterval(MAX_INACTIVE_INTERVAL);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession httpSession = se.getSession();
        Long accountId = (Long) httpSession.getAttribute("accountId");
        Optional.ofNullable(accountId).ifPresent(authenticationManager::remove);
    }
}
