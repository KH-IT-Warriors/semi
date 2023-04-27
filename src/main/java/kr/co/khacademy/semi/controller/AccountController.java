package kr.co.khacademy.semi.controller;

import kr.co.khacademy.semi.model.Profile;
import kr.co.khacademy.semi.service.AccountService;
import kr.co.khacademy.semi.service.AccountServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/username")
public class AccountController extends HttpServlet {
    private static final AccountService accountService = AccountServiceImpl.getInstance();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String phoneNumber = request.getParameter("phoneNumber");
        Boolean authorityResult = Boolean.valueOf(request.getParameter("authorityResult"));
        if (authorityResult) {
            Profile profile = Profile.builder()
                .name(name)
                .phoneNumber(phoneNumber)
                .build();
            String username = accountService.findUsernameByPhoneNumber(profile);
            request.setAttribute("username", username);
            request.getRequestDispatcher("").forward(request, response);
        } else {
            throw new RuntimeException();
        }
    }
}
