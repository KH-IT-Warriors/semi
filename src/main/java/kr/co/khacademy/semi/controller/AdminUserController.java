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
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/admin/account/admin-user/*")
public class AdminUserController extends HttpServlet {

    private static final UserDao userDao = UserDao.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String pathInfo = req.getPathInfo();
            if ("/list".equals(pathInfo)) {
                List<User> adminUsers = userDao.read();
                adminUsers.stream()
                    .filter((user) -> {
                        return user.getAccount().getRoleId() != 1;
                    })
                    .collect(Collectors.toList()
                );
                req.setAttribute("adminUsers", adminUsers);
                req.getRequestDispatcher("/WEB-INF/views/admin/account/admin_user/list.jsp");
            } else if ("/item".equals(pathInfo)) {
                Long id = Long.valueOf(req.getParameter("id"));
                User adminUser = userDao.read(id);
                req.setAttribute("adminUser", adminUser);
                req.getRequestDispatcher("/WEB-INF/views/admin/account/admin_user/list.jsp");
            }
        } catch (SQLException e) {
            resp.sendRedirect("/error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String pathInfo = req.getPathInfo();
            if ("/register".equals(pathInfo)) {
                User user = User.of(req);
                userDao.create(user);
                resp.sendRedirect("/admin/account/admin-user/list");
            } else if ("/modify".equals(pathInfo)) {
                User user = User.of(req);
                Long targetId = Long.valueOf(req.getParameter("target-id"));
                userDao.update(user, targetId);
            } else if ("/delete".equals(pathInfo)) {
                Long targetId = Long.valueOf(req.getParameter("target-id"));
                userDao.delete(targetId);
            }
        } catch (SQLException e) {
            resp.sendRedirect("/error.jsp");
        }
    }
}
