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
import java.util.Optional;
import java.util.stream.Collectors;

@WebServlet("/admin/account/normal-user/*")
public class NormalUserController extends HttpServlet {

    private static final UserDao userDao = UserDao.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String pathInfo = req.getPathInfo();
            if ("/list".equals(pathInfo)) {
                List<User> normalUsers = userDao.read();
                normalUsers.stream()
                    .filter((user) -> {
                        return user.getAccount().getRoleId() == 1;
                    })
                    .collect(Collectors.toList()
                    );
                req.setAttribute("normalUsers", normalUsers);
                req.getRequestDispatcher("/WEB-INF/views/admin/account/normal_user/list.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            resp.sendRedirect("/error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
