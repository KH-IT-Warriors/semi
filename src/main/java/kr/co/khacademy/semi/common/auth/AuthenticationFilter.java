package kr.co.khacademy.semi.common.auth;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/private/*")
public class AuthenticationFilter implements Filter {

    private static final AuthenticationManager authenticationManager = AuthenticationManager.getInstance();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        if (authenticationManager.isAccountAuthenticated(req.getSession())) {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}
