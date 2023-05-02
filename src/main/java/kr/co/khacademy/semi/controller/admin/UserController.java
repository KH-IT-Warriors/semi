package kr.co.khacademy.semi.controller.admin;

import kr.co.khacademy.semi.dao.UserDao;
import kr.co.khacademy.semi.model.User;
import lombok.extern.java.Log;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

@WebServlet("/admin/user/*")
@Log
public class UserController extends HttpServlet {

    private static final UserDao userDao = UserDao.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String pathInfo = req.getPathInfo();
            if ("/register".equals(pathInfo)) {
                req.getRequestDispatcher("/WEB-INF/views/admin/user/signUp.jsp").forward(req, resp);
            } else if ("/list".equals(pathInfo)) {
                List<User> normalUsers = userDao.readNormalUser();
                req.setAttribute("normalUsers", normalUsers);
                req.getRequestDispatcher("/WEB-INF/views/admin/user/list.jsp").forward(req, resp);
            } else if ("/item".equals(pathInfo)) {
                Long id = Long.valueOf(req.getParameter("id"));
                User normalUser = userDao.read(id);
                req.setAttribute("normalUser", normalUser);
                req.getRequestDispatcher("/WEB-INF/views/admin/user/item.jsp").forward(req, resp);
            } else if ("/admin-list".equals(pathInfo)) {
                List<User> adminUsers = userDao.readAdminUser();
                req.setAttribute("adminUsers", adminUsers);
                req.getRequestDispatcher("/WEB-INF/views/admin/user/list.jsp").forward(req, resp);
            } else if ("/modify".equals(pathInfo)) {
                resp.sendRedirect("WEB-INF/views/admin/user/modify.jsp");
            }
        } catch (SQLException e) {
            resp.sendRedirect("/error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        try {
            String pathInfo = req.getPathInfo();
            if ("/register".equals(pathInfo)) {
                User user = User.of(req);
                userDao.create(user);
                resp.sendRedirect("/admin/user/list");
            } else if ("/modify".equals(pathInfo)) {
                User user = User.of(req);
                userDao.update(user);
            } else if ("/delete".equals(pathInfo)) {
                Long targetId = Long.valueOf(req.getParameter("target-id"));
                userDao.forceDelete(targetId);
            }
        } catch (SQLException e) {
            resp.sendRedirect("/error.jsp");
        }
    }
}
