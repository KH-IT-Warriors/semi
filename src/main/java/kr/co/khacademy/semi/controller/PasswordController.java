package kr.co.khacademy.semi.controller;

import kr.co.khacademy.semi.model.Account;
import kr.co.khacademy.semi.model.Password;
import kr.co.khacademy.semi.model.Profile;
import kr.co.khacademy.semi.service.AccountService;
import kr.co.khacademy.semi.service.AccountServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/password")
public class PasswordController extends HttpServlet {
    private static final AccountService accountService = AccountServiceImpl.getInstance();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String name = request.getParameter("name");
        String phoneNumber = request.getParameter("phoneNumber");
        Boolean authorityResult = Boolean.valueOf(request.getParameter("authorityResult"));
        if (authorityResult) {
            Account account = Account.builder()
                .username(username)
                .build();
            Profile profile = Profile.builder()
                .name(name)
                .phoneNumber(phoneNumber)
                .build();
            Long accountId = accountService.findPasswordByPhoneNumber(account, profile).getAccountId();
            request.setAttribute("accountId", accountId);
            // 찾아온 아이디 정보와 함께 비밀번호 재설정 페이지 이동
            request.getRequestDispatcher("").forward(request, response);
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long accountId = (Long) request.getSession().getAttribute("accountId");
        String plainPassword = request.getParameter("password");
        Password password = Password.builder()
            .accountId(accountId)
            .encryptedPassword(plainPassword)
            .build();
        accountService.modifyPasswordById(password);
    }
}
