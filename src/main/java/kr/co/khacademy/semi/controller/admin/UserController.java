package kr.co.khacademy.semi.controller.admin;

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

@WebServlet("/admin/user/*")
public class UserController extends HttpServlet {

    private static final UserDao userDao = UserDao.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String pathInfo = req.getPathInfo();
            if ("/register".equals(pathInfo)) {
                resp.sendRedirect("/WEB-INF/views/admin/user/signUp.jsp");
            } else if ("/list".equals(pathInfo)) {
                String type = req.getParameter("type");
                if ("normalUser".equals(type)) {
                    List<User> normalUsers = userDao.readNormalUser();
                    req.setAttribute("users", normalUsers);
                    req.getRequestDispatcher("/WEB-INF/views/admin/user/list.jsp").forward(req, resp);
                } else if ("adminUser".equals(type)) {
                    List<User> adminUsers = userDao.readAdminUser();
                    req.setAttribute("users", adminUsers);
                    req.getRequestDispatcher("/WEB-INF/views/admin/user/list.jsp").forward(req, resp);
                }
            } else if ("/item".equals(pathInfo)) {
                Long id = Long.valueOf(req.getParameter("id"));
                User user = userDao.read(id);
                req.setAttribute("user", user);
                req.getRequestDispatcher("/WEB-INF/views/admin/user/item.jsp").forward(req, resp);
            }  else if ("/modify".equals(pathInfo)) {
                resp.sendRedirect("WEB-INF/views/admin/user/modify.jsp");
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
