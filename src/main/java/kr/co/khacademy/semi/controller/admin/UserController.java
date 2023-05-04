package kr.co.khacademy.semi.controller.admin;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import kr.co.khacademy.semi.dao.GradeDao;
import kr.co.khacademy.semi.dao.UserDao;
import kr.co.khacademy.semi.model.Criteria;
import kr.co.khacademy.semi.model.Grade;
import kr.co.khacademy.semi.model.User;
import lombok.extern.java.Log;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;

@WebServlet("/admin/user/*")
@Log
public class UserController extends HttpServlet {

    private static final UserDao userDao = UserDao.getInstance();
    private static final GradeDao gradeDao = GradeDao.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        try {
            String pathInfo = req.getPathInfo();
            System.out.println(pathInfo);
            if ("/register".equals(pathInfo)) {
                req.getRequestDispatcher("/WEB-INF/views/admin/user/register.jsp").forward(req, resp);
            } else if ("/list".equals(pathInfo)) {
                Criteria criteria = Criteria.of(req);
                Long start = (criteria.getPageNumber() * criteria.getAmount()) - (criteria.getAmount() - 1);
                Long end = criteria.getPageNumber() * criteria.getAmount();

                if ("normal".equals(criteria.getType())) {
                    if ("".equals(criteria.getKeyword())) {
                        List<User> normalUsers = userDao.readNormalUser(start, end);
                        req.setAttribute("users", normalUsers);
                    } else {
                        List<User> searchUser = userDao.searchNormalUser(criteria.getKeyword(), start, end);
                        req.setAttribute("users", searchUser);
                    }
                } else {
                    if ("".equals(criteria.getKeyword())) {
                        List<User> adminUsers = userDao.readAdminUser(start, end);
                        req.setAttribute("users", adminUsers);
                    } else {
                        List<User> searchUser = userDao.searchAdminUser(criteria.getKeyword(), start, end);
                        req.setAttribute("users", searchUser);
                    }
                }
                List<String> navi = userDao.getPageNavi(criteria);
                req.setAttribute("criteria", criteria);
                req.setAttribute("navi", navi);
                req.getRequestDispatcher("/WEB-INF/views/admin/user/list.jsp").forward(req, resp);
            } else if ("/item".equals(pathInfo)) {
                Long id = Long.valueOf(req.getParameter("id"));
                User user = userDao.read(id);
                Grade grade = gradeDao.read(user.getProfile().getGradeId());
                req.setAttribute("user", user);
                req.setAttribute("grade", grade);
                req.getRequestDispatcher("/WEB-INF/views/admin/user/item.jsp").forward(req, resp);
            } else if ("/modify".equals(pathInfo)) {
                Long id = Long.valueOf(req.getParameter("id"));
                User user = userDao.read(id);
                Grade grade = gradeDao.read(user.getProfile().getGradeId());
                req.setAttribute("user", user);
                req.setAttribute("grade", grade);
                resp.sendRedirect("WEB-INF/views/admin/user/modify.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendRedirect("/error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
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
            } else if ("/uploadImage".equals(pathInfo)) {
                Long id = Long.valueOf(req.getParameter("target-id"));
                String realPath = req.getServletContext().getRealPath("profileImage");
                File realPathFile = new File(realPath);
                if (!realPathFile.exists()) {
                    realPathFile.mkdir();
                }
                MultipartRequest multipartRequest = new MultipartRequest(req, realPath, 1024 * 1024 * 10, "UTF-8", new DefaultFileRenamePolicy());
                Enumeration<String> names = multipartRequest.getFileNames();
                while (names.hasMoreElements()) {
                    String fileName = names.nextElement();
                    if (multipartRequest.getFile(fileName) != null) {
                        String systemName = multipartRequest.getFilesystemName(fileName);
                        userDao.uploadImage(id, systemName);
                    }
                }
            }
        } catch (SQLException e) {
            resp.sendRedirect("/error.jsp");
        }
    }
}
