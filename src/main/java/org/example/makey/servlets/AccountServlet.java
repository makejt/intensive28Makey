package org.example.makey.servlets;

import org.example.makey.dao.CustomerDAO;
import org.example.makey.model.Account;
import org.example.makey.service.AccountServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@WebServlet("/account")
public class AccountServlet extends HttpServlet {

    private AccountServiceImpl service = new AccountServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Account> accounts = service.showAllAccounts();
        resp.setContentType("application/json;charset=UTF-8");
        resp.getWriter().print(accounts);
        req.getRequestDispatcher("index.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = new Account();

        account.setNumber(req.getParameter("number"));
        account.setCurrency(Integer.parseInt(req.getParameter("currency")));
        account.setRest(Double.parseDouble(req.getParameter("rest")));
        account.setCustomer(new CustomerDAO().getById
                (Integer.parseInt(req.getParameter("customer_id"))));

        service.saveAccount(account);

        this.doGet(req, resp);
    }
}
