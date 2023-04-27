package kr.co.khacademy.semi.controller;

import kr.co.khacademy.semi.model.Profile;
import kr.co.khacademy.semi.service.AccountServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/profile")
public class ProfileAdminController extends HttpServlet {
    AccountServiceImpl accountService = AccountServiceImpl.getInstance();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long accountId = Long.parseLong(request.getParameter("accountId"));
        String name = request.getParameter("name");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        Long gradeId = Long.parseLong(request.getParameter("gradeId"));
        Long bonusPoint = Long.parseLong(request.getParameter("bonusPoint"));

        Profile profile = Profile.builder()
            .accountId(accountId)
            .name(name)
            .phoneNumber(phoneNumber)
            .email(email)
            .userGradeId(gradeId)
            .bonusPoint(bonusPoint)
            .build();
        boolean isAdmin = true;
        accountService.modifyProfileById(profile, isAdmin);
    }
}
