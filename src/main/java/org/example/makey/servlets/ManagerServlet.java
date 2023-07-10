package org.example.makey.servlets;

import org.example.makey.model.Manager;
import org.example.makey.service.ManagerServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@WebServlet("/manager")
public class ManagerServlet extends HttpServlet {
    private ManagerServiceImpl service = new ManagerServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Manager> managers =service.showAllManagers();
        resp.setContentType("application/json;charset=UTF-8");
        resp.getWriter().print(managers);
        req.getRequestDispatcher("index.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Manager manager = new Manager();

        manager.setName(req.getParameter("name").trim());
        manager.setLastName(req.getParameter("lastname").trim());
        manager.setTelephone(req.getParameter("telephone"));
        manager.setEmail(req.getParameter("email").trim().toLowerCase());
        resp.setContentType("application/json;charset=UTF-8");

        service.saveManager(manager);

        this.doGet(req, resp);

    }
}
