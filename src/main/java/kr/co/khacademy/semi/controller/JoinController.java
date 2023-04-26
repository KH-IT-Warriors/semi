package kr.co.khacademy.semi.controller;

import kr.co.khacademy.semi.dto.JoinRequest;
import kr.co.khacademy.semi.exception.join.JoinFailedException;
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
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");

        try {
            JoinRequest joinRequest = JoinRequest.of(username, password, name, phoneNumber, email);
            if (accountService.join(joinRequest)) {
                request.setAttribute("name", name); // 환영합니다 name 님 출력을 위해 forward
                request.getRequestDispatcher("/WEB-INF/views/joinSuccess.jsp").forward(request, response);
            }
        } catch (JoinFailedException e) {
            request.setAttribute("joinFailed", true);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long accountId = (Long)request.getSession().getAttribute("accountId");
        accountService.deleteAccountByAccountId(accountId);
        response.sendRedirect("/index.jsp");
    }
}
