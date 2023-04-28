package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet("/rents/delete")
public class ReservationDeleteServlet extends HttpServlet {
    @Autowired
    private ReservationService reservationService;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            /*Long resaId =Long.parseLong(request.getParameter("reservationId"));
            Reservation reservation = reservationService.findById(resaId);*/
            List<Reservation> reservations = reservationService.findAll();
            String id =request.getParameter("reservationId");
            for (int i=0; i<reservations.size();i++){
                if(id.equals(reservations.get(i))){
                //if(id.equals(reservations.get(i))){
                    Reservation reservation = reservations.get(Math.toIntExact(Long.parseLong(request.getParameter("reservationId"))));
                    reservationService.delete(reservation);
                }
            }



            //LocalDate BirthDate = LocalDate.parse("21/12/2022", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            //Reservation reservation = new Reservation(1,1,1,BirthDate,BirthDate);
            //Reservation reservation = reservationService.findById(Long.parseLong(request.getParameter("reservationId").toString()));

            response.sendRedirect("/rentmanager/rents");
        } catch (ServiceException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
