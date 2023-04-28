package kr.co.khacademy.semi.controller;

import kr.co.khacademy.semi.dao.ReplyDao;
import kr.co.khacademy.semi.model.Reply;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/reply/*")
public class ReplyController extends HttpServlet {

    private static final ReplyDao replyDao = ReplyDao.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if ("/register".equals(pathInfo)) {
            Reply reply = Reply.of(req);
            replyDao.create(reply);
            String location = String.format("/product/item?id=%d", reply.getProductId());
            resp.sendRedirect(location);

        } else if ("/modify".equals(pathInfo)) {
            Reply reply = Reply.of(req);
            replyDao.update(reply);
            String location = String.format("/product/item?id=d%", reply.getProductId());
            resp.sendRedirect(location);
        }

    }
}
