package kr.co.khacademy.semi.controller;

import kr.co.khacademy.semi.dto.UpdateInformationRequest;
import kr.co.khacademy.semi.dto.UpdatePasswordRequest;
import kr.co.khacademy.semi.entity.UserInformation;
import kr.co.khacademy.semi.service.AccountService;
import kr.co.khacademy.semi.service.AccountServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("*.information")
public class UserInformationController extends HttpServlet {
    private static final AccountService accountService = AccountServiceImpl.getInstance();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long accountId = (Long) request.getSession().getAttribute("accountId");
        UserInformation userInformation = accountService.findUserInformation(accountId);
        request.setAttribute("userInformation", userInformation);
        request.getRequestDispatcher("/WEB-INF/views/myPage.jsp").forward(request,response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getRequestURI();
        if (command.equals("/updatePassword.information")) {
            Long accountId = (Long) request.getSession().getAttribute("accountId");
            String plainPassword = request.getParameter("password");
            UpdatePasswordRequest updatePasswordRequest = UpdatePasswordRequest.of(accountId, plainPassword);
            accountService.updatePassword(updatePasswordRequest);
        } else if (command.equals("/update.information")) {
            Long accountId = (Long) request.getSession().getAttribute("accountId");
            String name = request.getParameter("name");
            String phoneNumber = request.getParameter("phoneNumber");
            String email = request.getParameter("email");
            UpdateInformationRequest updateInformationRequest = UpdateInformationRequest.of(accountId, name, phoneNumber, email);
            accountService.updateInformation(updateInformationRequest);
            doGet(request, response);
        }
    }
}
