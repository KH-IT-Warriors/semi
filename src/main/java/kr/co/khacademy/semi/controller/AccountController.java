package kr.co.khacademy.semi.controller;

import kr.co.khacademy.semi.dao.UserDao;
import kr.co.khacademy.semi.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/account/*")
public class AccountController extends HttpServlet {

    private static final UserDao userDao = UserDao.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String pathInfo = req.getPathInfo();
            if ("/item".equals(pathInfo)) {
                Long id = (Long) req.getSession().getAttribute("accountId");
                User user = userDao.read(id);
                req.setAttribute("user", user);
                req.getRequestDispatcher("/WEB-INF/views/account/myPage.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String pathInfo = req.getPathInfo();
            if ("/register".equals(pathInfo)) {
                User user = User.of(req);
                userDao.create(user);
                req.setAttribute("registerSuccess", true);
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            } else if ("/modify".equals(pathInfo)) {
                User user = User.of(req);
                userDao.update(user);
                resp.sendRedirect("/account/item");
            }
        } catch (SQLException e) {
            resp.sendRedirect("/error.jsp");
        }
    }
}
