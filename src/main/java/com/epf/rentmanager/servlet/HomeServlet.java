package com.epf.rentmanager.servlet;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	@Autowired
	private ClientService clientService;
	@Autowired
	private VehicleService vehicleService;
	@Autowired
	private ReservationService reservationService;
	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		try {
			request.setAttribute("nbClients", clientService.findAll().stream().count());
			request.setAttribute("nbVehicles",vehicleService.findAll().stream().count());
			request.setAttribute("nbReservations", reservationService.findAll().stream().count());
		} catch (ServiceException e) {
			throw new RuntimeException(e);
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
	}

}
