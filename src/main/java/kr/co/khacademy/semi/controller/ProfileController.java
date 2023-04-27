package kr.co.khacademy.semi.controller;

import kr.co.khacademy.semi.dto.ProfilePutRequest;
import kr.co.khacademy.semi.model.Profile;
import kr.co.khacademy.semi.service.AccountService;
import kr.co.khacademy.semi.service.AccountServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileController extends HttpServlet {
    private static final AccountService accountService = AccountServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long accountId = (Long) request.getSession().getAttribute("accountId");
        String username = accountService.findUsernameById(accountId);
        Profile profile = accountService.findProfileById(accountId);
        request.setAttribute("username", username);
        request.setAttribute("userProfile", profile);
        request.getRequestDispatcher("/WEB-INF/views/myPage.jsp").forward(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long accountId = (Long) request.getSession().getAttribute("accountId");
        String name = request.getParameter("name");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        Profile profile = Profile.builder()
            .accountId(accountId)
            .name(name)
            .phoneNumber(phoneNumber)
            .email(email)
            .build();
        accountService.modifyProfileById(profile);
        doGet(request, response);
    }
}
