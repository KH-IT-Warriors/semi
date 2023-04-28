package kr.co.khacademy.semi.controller;

import kr.co.khacademy.semi.dao.AccountDao;
import kr.co.khacademy.semi.dao.ProfileDao;
import kr.co.khacademy.semi.dao.UserDao;
import kr.co.khacademy.semi.model.Account;
import kr.co.khacademy.semi.model.Profile;
import kr.co.khacademy.semi.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Optional;

@WebServlet("/my-page/*")
public class MyPageController extends HttpServlet {

    private static AccountDao accountDao = AccountDao.getInstance();
    private static ProfileDao profileDao = ProfileDao.getInstance();
    private static UserDao userDao = UserDao.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String pathInfo = req.getPathInfo();
            if ("/item".equals(pathInfo)) {
                Long id = (Long) req.getSession().getAttribute("accountId");
                User user = userDao.read(id);
                req.setAttribute("user", user);
                req.getRequestDispatcher("/WEB-INF/views/myPage.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Profile modifyProfile = Profile.of(req);
        if (profileDao.update(modifyProfile)) {
            resp.sendRedirect("/my-page");
        }
    }
}
