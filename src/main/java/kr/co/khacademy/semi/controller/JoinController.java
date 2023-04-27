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

@WebServlet("/join")
public class JoinController extends HttpServlet {
    private static final AccountService accountService = AccountServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String plainPassword = request.getParameter("password");
        String name = request.getParameter("name");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");

        Account account = Account.builder()
            .username(username)
            .build();
        Password password = Password
            .builder()
            .encryptedPassword(plainPassword)
            .build();
        Profile profile = Profile
            .builder()
            .name(name)
            .phoneNumber(phoneNumber)
            .email(email)
            .build();
        accountService.join(account, password, profile);
        request.setAttribute("name", name); // 환영합니다 name 님 출력을 위해 forward
        request.getRequestDispatcher("/WEB-INF/views/joinSuccess.jsp").forward(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long accountId = (Long)request.getSession().getAttribute("accountId");
        accountService.deleteAccountById(accountId);
        response.sendRedirect("/index.jsp");
    }
}
