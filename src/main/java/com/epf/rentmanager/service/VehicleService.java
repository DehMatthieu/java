package com.epf.rentmanager.service;

import java.util.List;
import java.util.Optional;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.dao.VehicleDao;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

	private VehicleDao vehicleDao;
	
	private VehicleService(VehicleDao vehicleDao) {
		this.vehicleDao = vehicleDao;
	}


	public long create(Vehicle vehicle) throws ServiceException {
		try {
			return this.vehicleDao.create(vehicle);
		} catch (DaoException e) {
		}
		return 0;
	}

	public Vehicle findById(long id) throws ServiceException {
		try {
			return vehicleDao.findById(id);
		} catch (RuntimeException | DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public List<Vehicle> findAll() throws ServiceException {
		try {
			return vehicleDao.findAll();
		} catch (RuntimeException | DaoException e) {
			throw new ServiceException();
		}
	}
	public Long delete(Vehicle vehicle) throws ServiceException {
		try {
			return this.vehicleDao.delete(vehicle);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return 0L;
	}
}
