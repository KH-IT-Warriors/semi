package kr.co.khacademy.semi.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.khacademy.semi.dao.FaqDao;
import kr.co.khacademy.semi.model.Faq;

@WebServlet("/FaqContorller")
public class FaqContorller extends HttpServlet {

    private static final FaqDao faqDao = FaqDao.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if("/register".equals(pathInfo)) {
            Faq faq = Faq.of(req);
            
            faqDao.create(faq);
            resp.sendRedirect("");

        }else if("/modify".equals(pathInfo)) {
            Faq faq = Faq.of(req);
            faqDao.update(faq);
            String location = String.format("", faq.getId());
            resp.sendRedirect(location);
            
        }else if("/delete".equals(pathInfo)) {
            Long id = Long.parseLong(req.getParameter("id"));
            faqDao.delete(id);
            resp.sendRedirect("");
        }
    }


}
