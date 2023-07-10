package org.example.makey.servlets;

import org.example.makey.dao.ManagerDAO;
import org.example.makey.model.Customer;
import org.example.makey.service.CustomServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/customer")
public class CustomerServlet extends HttpServlet {
    private CustomServiceImpl service = new CustomServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Customer> customers = service.showAllCustomers();
        for (Customer c : customers) {
            resp.getWriter().print(c);
            resp.getWriter().print(c.getManager());
            resp.getWriter().print(c.getAccounts());
        }
        resp.setContentType("application/json;charset=UTF-8");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Customer customer = new Customer();

//        customer.setBrandName(req.getParameter("brandname"));
//        customer.setLegalForm(req.getParameter("leagalform"));
//        customer.setINN(Integer.parseInt(req.getParameter("INN")));
        customer.setManager(new ManagerDAO().getById(Integer.parseInt(req.getParameter("manager_id"))));

        service.saveCustomer(customer);

        this.doGet(req, resp);
    }
}