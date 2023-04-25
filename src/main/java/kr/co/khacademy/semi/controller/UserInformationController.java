package kr.co.khacademy.semi.controller;

import kr.co.khacademy.semi.dto.UpdateInformationRequest;
import kr.co.khacademy.semi.service.AccountService;
import kr.co.khacademy.semi.service.AccountServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update")
public class UserInformationController extends HttpServlet {
    private static final AccountService accountService = AccountServiceImpl.getInstance();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long accountId = (Long)request.getSession().getAttribute("accountId");
        String name = request.getParameter("name");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        UpdateInformationRequest updateInformationRequest = UpdateInformationRequest.of(accountId, name, phoneNumber, email);
        accountService.updateInformation(updateInformationRequest);
    }
}
