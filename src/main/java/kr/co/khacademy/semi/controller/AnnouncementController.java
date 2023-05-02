package kr.co.khacademy.semi.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.khacademy.semi.dao.AnnouncementDao;
import kr.co.khacademy.semi.model.Announcement;

@WebServlet("/admin/announcement/*")
public class AnnouncementController extends HttpServlet {
    
    private static final AnnouncementDao announcementDao = AnnouncementDao.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String pathInfo = req.getPathInfo();
            if ("/register".equals(pathInfo)) {
                resp.sendRedirect("/WEB-INF/views/announcement/register.jsp");
            } else if ("/list".equals(pathInfo)) {
                List<Announcement> announcements = announcementDao.read();
                req.setAttribute("announcements", announcements);
                req.getRequestDispatcher("/WEB-INF/views/announcement/list.jsp").forward(req, resp);
            } else if ("/item".equals(pathInfo)) {
                Long id = Long.valueOf(req.getParameter("id"));
                Announcement announcement = announcementDao.read(id);
                req.setAttribute("announcement", announcement);
                req.getRequestDispatcher("/WEB-INF/views/announcement/item.jsp").forward(req, resp);
            }else if("/modify".equals(pathInfo)) {
                resp.sendRedirect("/WEB-INF/views/announcement/modify.jsp");
            }
        } catch (SQLException e) {
            resp.sendRedirect("/error");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String pathInfo = req.getPathInfo();
            if ("/register".equals(pathInfo)) {
                Announcement announcement = Announcement.of(req);
                announcementDao.create(announcement);
                resp.sendRedirect("/admin/announcement/list.jsp");
            } else if ("/modify".equals(pathInfo)) {
                Announcement announcement = Announcement.of(req);
                announcementDao.update(announcement);
                String location = String.format("/admin/announcement/item?id=%d", announcement.getId());
                resp.sendRedirect(location);
            } else if ("/delete".equals(pathInfo)) {
                Long id = Long.valueOf(req.getParameter("id"));
                announcementDao.delete(id);
                resp.sendRedirect("/admin/announcement/list");
            }
        } catch (SQLException e) {
            resp.sendRedirect("/error");
        }
    }
	

}
