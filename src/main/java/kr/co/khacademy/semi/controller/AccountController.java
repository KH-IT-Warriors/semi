package kr.co.khacademy.semi.controller;

import kr.co.khacademy.semi.dao.AccountDao;
import kr.co.khacademy.semi.dao.ProfileDao;
import kr.co.khacademy.semi.model.Account;
import kr.co.khacademy.semi.model.Password;
import kr.co.khacademy.semi.model.Profile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/account/*")
public class AccountController extends HttpServlet {

    private static AccountDao accountDao = AccountDao.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if ("/join".equals(pathInfo)) {
            Account account = Account.of(req);
            Password password = Password.of(req);
            Profile profile = Profile.of(req);
        }
    }
}

// TODO: ROLE 추가 필요.