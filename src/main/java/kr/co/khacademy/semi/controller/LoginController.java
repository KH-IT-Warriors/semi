package kr.co.khacademy.semi.controller;

import kr.co.khacademy.semi.auth.AuthenticationManager;
import kr.co.khacademy.semi.dao.AccountDao;
import kr.co.khacademy.semi.model.Login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    private static AccountDao accountDao = AccountDao.getInstance();
    private static AuthenticationManager authenticationManager = AuthenticationManager.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Login login = Login.of(req);
        if (accountDao.read(login)) {
            authenticationManager.login(login.getId(), req.getSession());
            resp.sendRedirect("/index.jsp");
        } else {
            req.setAttribute("loginFail", true);
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
}
