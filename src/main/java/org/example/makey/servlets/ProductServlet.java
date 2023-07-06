package org.example.makey.servlets;

import org.example.makey.model.Manager;
import org.example.makey.model.Product;
import org.example.makey.service.ProductServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {

    private ProductServiceImpl service = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<Product> products =service.showAllProducts();
        resp.setContentType("application/json;charset=UTF-8");
        resp.getWriter().print(products);
        req.getRequestDispatcher("index.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = new Product();
        product.setType(req.getParameter("type"));

        service.saveProduct(product);

        this.doGet(req, resp);
    }
}