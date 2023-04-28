package kr.co.khacademy.semi.controller;

import kr.co.khacademy.semi.dao.AccountDao;
import kr.co.khacademy.semi.dao.UserDao;
import kr.co.khacademy.semi.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@WebServlet("/admin/account/normal-user/*")
public class NormalUserController extends HttpServlet {

    private static final UserDao userDao = UserDao.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String pathInfo = req.getPathInfo();
            if ("/list".equals(pathInfo)) {
                List<User> normalUsers = userDao.read();
                req.setAttribute("normalUsers", normalUsers);
                req.getRequestDispatcher("/WEB-INF/views/admin/account/normal_user/list.jsp").forward(req, resp);
            } else if ("/item".equals(pathInfo)) {
                Long id = Long.valueOf(req.getParameter("id"));
                User normalUser = userDao.read(id);
                req.setAttribute("normalUser", normalUser);
                req.getRequestDispatcher("/WEB-INF/views/admin/account/normal_user/item.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            resp.sendRedirect("/error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        try {
            if ("/modify".equals(pathInfo)) {
                User user = User.of(req);
                userDao.update(user);
            } else if ("/delete".equals(pathInfo)) {
                Long targetId = Long.valueOf(req.getParameter("target-id"));
                userDao.delete(targetId);
            }
        } catch (SQLException e) {
            resp.sendRedirect("/error.jsp");
        }
    }
}

// TODO: ROLE 추가 필요.