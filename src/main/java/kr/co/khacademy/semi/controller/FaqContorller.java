package kr.co.khacademy.semi.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.khacademy.semi.dao.FaqDao;
import kr.co.khacademy.semi.model.Faq;
import kr.co.khacademy.semi.model.Product;

@WebServlet("/FaqContorller")
public class FaqContorller extends HttpServlet {

    private static final FaqDao faqDao = FaqDao.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String pathInfo = req.getPathInfo();
            if ("/register".equals(pathInfo)) {
                resp.sendRedirect("/WEB-INF/views/faq/register.jsp");
            } else if ("/list".equals(pathInfo)) {
                List<Faq> faqList = faqDao.read();
                req.setAttribute("faqList", faqList);
                req.getRequestDispatcher("/WEB-INF/views/faq/list.jsp").forward(req, resp);
            } else if ("/item".equals(pathInfo)) {
                Long id = Long.valueOf(req.getParameter("id"));
                Faq faq = faqDao.read(id);
                req.setAttribute("faq", faq);
                req.getRequestDispatcher("/WEB-INF/views/faq/item.jsp").forward(req, resp);
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
                Faq faq = Faq.of(req);
                faqDao.create(faq);
                resp.sendRedirect("/faq/list");
            } else if ("/modify".equals(pathInfo)) {
                Faq faq = Faq.of(req);
                faqDao.update(faq);
                String location = String.format("/faq/item?id=%d", faq.getId());
                resp.sendRedirect(location);
            } else if ("/delete".equals(pathInfo)) {
                Long id = Long.valueOf(req.getParameter("id"));
                faqDao.delete(id);
                resp.sendRedirect("/faq/list");
            }
        } catch (SQLException e) {
            resp.sendRedirect("/error");
        }
    }


}
