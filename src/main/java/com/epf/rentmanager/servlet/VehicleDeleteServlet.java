package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cars/delete")
public class VehicleDeleteServlet extends HttpServlet {
        @Autowired
        private VehicleService vehicleService;

        @Override
        public void init() throws ServletException {
            super.init();
            SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        }

        protected void doGet(HttpServletRequest request, HttpServletResponse
        response)throws ServletException, IOException {
            try {
                Vehicle vehicle = vehicleService.findById(Long.parseLong(request.getParameter("vehicleId").toString()));
                vehicleService.delete(vehicle);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
            response.sendRedirect("/rentmanager/cars");
        }

    }



