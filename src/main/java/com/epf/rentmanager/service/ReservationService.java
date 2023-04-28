package com.epf.rentmanager.service;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReservationService {
    private ReservationDao reservationDao;

    private ReservationService(ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
    }

    public long create(Reservation reservation) throws ServiceException {
        try {
            reservationDao.create(reservation);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Reservation findById(long id) throws ServiceException {
        try {
            return reservationDao.findById(id);
        } catch (RuntimeException | DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Reservation> findAll() throws ServiceException {
        try {
            return reservationDao.findAll();
        } catch (RuntimeException | DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public long delete(Reservation reservation) throws ServiceException {
        try {
            return this.reservationDao.delete(reservation);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return 0;

    }
}

