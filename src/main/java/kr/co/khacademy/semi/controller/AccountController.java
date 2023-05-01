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
            if ("/register".equals(pathInfo)) {
                resp.sendRedirect("/WEB-INF/views/account/register.jsp");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
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
            }
        } catch(SQLException e){
            resp.sendRedirect("/error.jsp");
        }
    }
}


