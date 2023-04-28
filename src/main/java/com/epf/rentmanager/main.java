package com.epf.rentmanager;


import com.epf.rentmanager.Configuration.AppConfiguration;
import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ofPattern;

public class main {

    public static void main(String[] args) throws ServiceException, DaoException {
        ApplicationContext context = new
                AnnotationConfigApplicationContext(AppConfiguration.class);
        ReservationDao reservationDao = context.getBean(ReservationDao.class);
        ReservationService reservationService = context.getBean(ReservationService.class);
        LocalDate BirthDate = LocalDate.parse("21/10/2001", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        //Reservation reservation = reservationDao.findById(1);
        try {
            System.out.println(reservationService.findAll());
            System.out.println(reservationService.findById(1));
        } catch (ServiceException e) {
        }
    }
}