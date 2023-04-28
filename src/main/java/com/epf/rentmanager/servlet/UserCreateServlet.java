package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet("/users/create")
public class UserCreateServlet extends HttpServlet {
    @Autowired
    private ClientService clientService;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("clients", clientService.findAll());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/create.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String last_name = request.getParameter("last_name");
            String first_name = request.getParameter("first_name");
            LocalDate BirthDate = LocalDate.parse(request.getParameter("BirthDate"), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String email = request.getParameter("email");
            Client client = new Client(last_name, first_name, email, BirthDate);

        /*while(!client.isLegal(BirthDate)|| clientService.existingMailUsed(email)) {
            last_name = request.getParameter("last_name");
            first_name = request.getParameter("first_name");
            BirthDate = LocalDate.parse(request.getParameter("BirthDate"), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            email = request.getParameter("email");
            client = new Client(last_name, first_name, email, BirthDate);
        }*/
            request.setAttribute("client", clientService.create(client));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        this.doGet(request, response);
    }
}