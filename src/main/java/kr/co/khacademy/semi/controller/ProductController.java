package kr.co.khacademy.semi.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.khacademy.semi.dao.ProductDao;
import kr.co.khacademy.semi.model.Criteria;
import kr.co.khacademy.semi.model.Product;

@WebServlet("/admin/product/*")
public class ProductController extends HttpServlet {

    private static final ProductDao productDao = ProductDao.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String pathInfo = req.getPathInfo();
            if ("/register".equals(pathInfo)) {
                req.getRequestDispatcher("/WEB-INF/views/admin/product/register.jsp").forward(req, resp);
            } else if ("/list".equals(pathInfo)) {
                Criteria criteria = Criteria.of(req);

                Long pageNumber = criteria.getPageNumber();
                Long amount = criteria.getAmount();

                Long start = pageNumber * amount - (amount - 1);
                Long end = pageNumber * amount;

                List<Product> products = productDao.read(start, end);
//                List<String> pageNavi = productDao.getPageNavi(criteria);

//                req.setAttribute("pageNavi", pageNavi);
                req.setAttribute("products", products);
                req.getRequestDispatcher("/WEB-INF/views/admin/product/list.jsp").forward(req, resp);

            } else if ("/item".equals(pathInfo)) {
                Long id = Long.valueOf(req.getParameter("id"));
                Product product = productDao.read(id);
                req.setAttribute("product", product);
                req.getRequestDispatcher("/WEB-INF/views/admin/product/item.jsp").forward(req, resp);
            } else if ("/modify".equals(pathInfo)) {
                Long id = Long.valueOf(req.getParameter("id"));
                req.setAttribute("id", id);
                req.getRequestDispatcher("/WEB-INF/views/admin/product/modify.jsp").forward(req, resp);
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
                Product product = Product.of(req);
                productDao.create(product);
                resp.sendRedirect("/admin/product/list");
            } else if ("/modify".equals(pathInfo)) {
                Product product = Product.of(req);
                Long id = Long.valueOf(req.getParameter("id"));
                productDao.update(product, id);
                String location = String.format("/admin/product/item?id=%d", id);
                resp.sendRedirect(location);
            } else if ("/delete".equals(pathInfo)) {
                Long id = Long.valueOf(req.getParameter("id"));
                productDao.delete(id);
                resp.sendRedirect("/admin/product/list");
            }
        } catch (SQLException e) {
            resp.sendRedirect("/error");
        }
    }
}
