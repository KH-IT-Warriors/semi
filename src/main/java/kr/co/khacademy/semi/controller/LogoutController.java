package kr.co.khacademy.semi.controller;

import kr.co.khacademy.semi.common.auth.AuthenticationManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {

    private static final AuthenticationManager authenticationManager = AuthenticationManager.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long accountId = (Long) req.getSession().getAttribute("accountId");
        authenticationManager.logout(accountId);
    }
}
