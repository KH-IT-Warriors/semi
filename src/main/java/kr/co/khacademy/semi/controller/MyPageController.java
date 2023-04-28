package kr.co.khacademy.semi.controller;

import kr.co.khacademy.semi.dao.AccountDao;
import kr.co.khacademy.semi.dao.ProfileDao;
import kr.co.khacademy.semi.model.Account;
import kr.co.khacademy.semi.model.Profile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@WebServlet("/my-page")
public class MyPageController extends HttpServlet {

    private static AccountDao accountDao = AccountDao.getInstance();
    private static ProfileDao profileDao = ProfileDao.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = (Long) req.getSession().getAttribute("accountId");
        Optional<Account> myAccount = accountDao.read(id);
        Profile myProfile = profileDao.read(id);
        req.setAttribute("myAccount", myAccount);
        req.setAttribute("myProfile", myProfile);
        req.getRequestDispatcher("/WEB-INF/views/my-page").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}