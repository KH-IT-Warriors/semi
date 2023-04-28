package kr.co.khacademy.semi.controller;

import kr.co.khacademy.semi.dao.ProfileDao;
import kr.co.khacademy.semi.model.Profile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/my-page")
public class MyPageController extends HttpServlet {

    private static ProfileDao profileDao = ProfileDao.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = (Long) req.getSession().getAttribute("accountId");
        Profile myProfile = profileDao.read(id);
        req.setAttribute("myProfile", myProfile);
        req.getRequestDispatcher("").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
