package kr.co.khacademy.semi.controller;

import kr.co.khacademy.semi.dao.ProductDao;
import kr.co.khacademy.semi.model.Product;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
            Optional<Product> product = productDao.read(id);
            req.setAttribute("product", product);
            req.getRequestDispatcher("/WEB-INF/views/product/item.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if ("/register".equals(pathInfo)) {
            Product product = Product.of(req);
            if (productDao.create(product)) {
                resp.sendRedirect("/product/list");
            } else {
                req.setAttribute("product", product);
                req.getRequestDispatcher("/WEB-INF/views/product/register.jsp");
            }
        } else if ("/modify".equals(pathInfo)) {
            Product product = Product.of(req);
            if (productDao.update(product)) {
                String location = String.format("/product/item?id=%d", product.getId());
                resp.sendRedirect(location);
            } else {
                req.setAttribute("product", product);
                req.setAttribute("shouldModify", true);
                String path = String.format("/WEB-INF/views/product/item?id=%d", product.getId());
                req.getRequestDispatcher(path).forward(req, resp);
            }
        } else if ("/delete".equals(pathInfo)) {
            Long id = Long.parseLong(req.getParameter("id"));
            productDao.delete(id);
            resp.sendRedirect("/product/list");
        }
    }
}
