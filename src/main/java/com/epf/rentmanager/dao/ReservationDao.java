package com.epf.rentmanager.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationDao {

	private ReservationDao() {}
	
	private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(?, ?, ?, ?);";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATION_QUERY = "SELECT id, vehicle_id, debut, fin FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, vehicle_id, debut, fin FROM Reservation WHERE client_id=?;";
	private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, client_id, debut, fin FROM Reservation WHERE vehicle_id=?;";
	private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation;";
		
	public Reservation create(Reservation reservation) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement ps =
					connection.prepareStatement(CREATE_RESERVATION_QUERY);

			ps.setLong(1, reservation.getClient_id());
			ps.setLong(2, reservation.getVehicle_id());
			ps.setDate(3, Date.valueOf(reservation.getDebut()));
			ps.setDate(4, Date.valueOf(reservation.getFin()));
			ps.execute();
			ps.close();
			connection.close();
		} catch (SQLException e) {
			throw new DaoException();
		}
		return reservation;
	}
	
	public long delete(Reservation reservation) throws DaoException {
			try {

				Connection connection = ConnectionManager.getConnection();

				PreparedStatement ps =
						connection.prepareStatement(DELETE_RESERVATION_QUERY);
				ps.setLong(1, reservation.getId()); // ATTENTION /!\ : l’indice commence par 1, contrairement aux tableaux
				ps.execute();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DaoException();
			}
	return reservation.getId();
	}


	
	public List<Reservation> findResaByClientId(long id) throws DaoException {
		try {
			List<Reservation> reservations = new ArrayList<Reservation>();
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(FIND_RESERVATIONS_BY_CLIENT_QUERY);
			preparedStatement.setLong(1,id);
			ResultSet rs = preparedStatement.executeQuery();

			rs.next();
			long clientid = rs.getLong("client_id");
			long vehicle_id = rs.getLong("vehicle_id");
			LocalDate debut = rs.getDate("debut").toLocalDate();
			LocalDate fin = rs.getDate("fin").toLocalDate();
			preparedStatement.execute();
			preparedStatement.close();
			reservations.add(new Reservation(id, clientid, vehicle_id, debut, fin));
			return reservations;
			} catch (SQLException e) {

		e.printStackTrace();
		throw new DaoException();
		}
	}
	
	public List<Reservation> findResaByVehicleId(long vehicleId) throws DaoException {
		try {
			List<Reservation> reservations = new ArrayList<Reservation>();
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(FIND_RESERVATIONS_BY_VEHICLE_QUERY);
			preparedStatement.setLong(2,vehicleId);
			ResultSet rs = preparedStatement.executeQuery();

			rs.next();
			long clientId = rs.getLong("client_id");
			LocalDate debut = rs.getDate("debut").toLocalDate();
			LocalDate fin = rs.getDate("fin").toLocalDate();
			preparedStatement.execute();
			preparedStatement.close();
			reservations.add(new Reservation(clientId, vehicleId, debut, fin));
			return reservations;
		} catch (SQLException e) {

			e.printStackTrace();
			throw new DaoException();
		}
	}

	public List<Reservation> findAll() throws DaoException {
		List<Reservation> reservations = new ArrayList<Reservation>();
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(FIND_RESERVATIONS_QUERY);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Long id = rs.getLong("id");
				Long vehicleId = rs.getLong("vehicle_id");
				Long clientId = rs.getLong("client_id");
				LocalDate debut = rs.getDate("debut").toLocalDate();
				LocalDate fin = rs.getDate("fin").toLocalDate();

				reservations.add(new Reservation(Math.toIntExact(id),vehicleId,clientId, debut, fin));
			}
			preparedStatement.execute();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
		return reservations;
	}
	public Reservation findById(long id) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(FIND_RESERVATION_QUERY);
			preparedStatement.setLong(1,id);
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			long client_id = rs.getLong("client_id");
			long vehicle_id = rs.getLong("vehicle_id");
			LocalDate debut = rs.getDate("debut").toLocalDate();
			LocalDate fin = rs.getDate("fin").toLocalDate();
			preparedStatement.execute();
			preparedStatement.close();

			return new Reservation( id, client_id, vehicle_id,  debut, fin) ;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}
}
