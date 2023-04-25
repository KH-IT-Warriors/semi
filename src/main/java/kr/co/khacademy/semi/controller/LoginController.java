package kr.co.khacademy.semi.controller;

import kr.co.khacademy.semi.common.auth.AuthenticationManager;
import kr.co.khacademy.semi.dto.LoginRequest;
import kr.co.khacademy.semi.exception.LoginFailedException;
import kr.co.khacademy.semi.service.AccountService;
import kr.co.khacademy.semi.service.AccountServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    private static final AuthenticationManager authenticationManager = AuthenticationManager.getInstance();

    private static final AccountService accountService = AccountServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            LoginRequest loginRequest = LoginRequest.of(username, password);
            Long accountId = accountService.login(loginRequest);

            authenticationManager.login(accountId, req.getSession());
            resp.sendRedirect("/index.jsp");
        } catch (LoginFailedException e) {
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
        }
    }
}
