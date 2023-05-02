package kr.co.khacademy.semi.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.co.khacademy.semi.dao.AddressDao;
import kr.co.khacademy.semi.model.Address;

@WebServlet("/address/*")
public class AddressController extends HttpServlet {

    private static final AddressDao addressDao = AddressDao.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String pathInfo = req.getPathInfo();
            if ("/register".equals(pathInfo)) {
                resp.sendRedirect("/WEB-INF/views/address/register.jsp");
            } else if ("/list".equals(pathInfo)) {
                List<Address> addresses = addressDao.read();
                req.setAttribute("address", addresses);
                req.getRequestDispatcher("/WEB-INF/views/address/list.jsp").forward(req, resp);
            } else if ("/item".equals(pathInfo)) {
                Long id = Long.parseLong(req.getParameter("id"));
                Long accountId = Long.valueOf(req.getParameter("accountId"));
                Address address = addressDao.read(accountId);
                req.setAttribute("product", address);
                req.getRequestDispatcher("/WEB-INF/views/product/item.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            resp.sendRedirect("/error");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String pathInfo = req.getPathInfo();
            if ("/register".equals(pathInfo)) {
                Address address = Address.of(req);
                addressDao.create(address);
                resp.sendRedirect("/address/list") ;
            } else if ("/modify".equals(pathInfo)) {
                Address address = Address.of(req);
                addressDao.update(address);
                String location = String.format("/address/item?id=%d", address.getId());
                resp.sendRedirect(location);
            } else if ("/delete".equals(pathInfo)) {
                Long id = Long.valueOf(req.getParameter("id"));
                addressDao.delete(id);
                resp.sendRedirect("/address/list");
            }
        } catch (SQLException e) {
            resp.sendRedirect("/error");
        }
    }

}
