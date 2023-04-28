package com.epf.rentmanager.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
	private ClientDao clientDao;

	private ClientService(ClientDao clientDao) {
		this.clientDao = clientDao;
	}


	public Client findById(long id) throws ServiceException {
		try {
			return clientDao.findById(id);
		} catch (RuntimeException | DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public List<Client> findAll() throws ServiceException {
		try {
			return clientDao.findAll();
		} catch (RuntimeException | DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public long create(Client client) throws ServiceException {
		try {
			return this.clientDao.create(client);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public long delete(Client client) throws ServiceException {
		try {
			return this.clientDao.delete(client);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public boolean existingMailUsed(String email) throws DaoException {
		try {
			List<String>listExistingMail = clientDao.listExistingMail();
			for (int i=0; i<listExistingMail.size();i++){
				if(email.equals(listExistingMail.get(i))){
					return true;
				}
			}
		} catch (DaoException e) {
		}
		return false;
	}
}


