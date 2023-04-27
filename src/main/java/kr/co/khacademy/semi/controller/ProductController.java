package kr.co.khacademy.semi.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/product/*")
public class ProductController extends HttpServlet {

    private static final ProductDao productDao = ProductDao.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if ("/register".equals(pathInfo)) {
            resp.sendRedirect("/WEB-INF/views/product/register.jsp");
        } else if ("/list".equals(pathInfo)) {
            List<Product> products = productDao.read();
            req.setAttribute("products", products);
            req.getRequestDispatcher("/WEB-INF/views/product/list.jsp").forward(req, resp);
        } else if ("/item".equals(pathInfo)) {
            Long id = Long.parseLong(req.getParameter("id"));
            Product product = productDao.read(id);
            req.setAttribute("product", product);
            req.getRequestDispatcher("/WEB-INF/views/product/item.jsp").forward(req, resp);
        }
    }
}
