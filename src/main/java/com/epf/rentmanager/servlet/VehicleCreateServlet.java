package com.epf.rentmanager.servlet;

import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/cars/create")

public class VehicleCreateServlet extends HttpServlet {
    @Autowired
    private VehicleService vehicleService;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/cars/create.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String manufacturer = request.getParameter("manufacturer");
        int seats = Integer.parseInt(request.getParameter("seats"));
        Vehicle vehicle = new Vehicle(manufacturer, seats);
        try {
            vehicleService.create(vehicle);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("/rentmanager/cars");
    }
}

