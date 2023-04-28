package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;
import org.springframework.stereotype.Repository;

@Repository
public class VehicleDao {

	private VehicleDao() {}
	
	private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(constructeur, nb_places) VALUES(?, ?);";
	private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLE_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle;";
	
	public long create(Vehicle vehicle) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement ps =
					connection.prepareStatement(CREATE_VEHICLE_QUERY);

			ps.setString(1, vehicle.getConstructeur());
			ps.setInt(2, vehicle.getNb_places());
			long id =ps.executeUpdate();
			ps.close();
			connection.close();
			return id;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	public long delete(Vehicle vehicle) throws DaoException {
		try {

			Connection connection = ConnectionManager.getConnection();

			PreparedStatement ps =
					connection.prepareStatement(DELETE_VEHICLE_QUERY);
			ps.setLong(1, vehicle.getId()); // ATTENTION /!\ : l’indice commence par 1, contrairement aux tableaux
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
		return vehicle.getId();
	}


	public List<Vehicle> findAll() throws DaoException {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		try{
				Connection connection = ConnectionManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(FIND_VEHICLES_QUERY);
				ResultSet rs = preparedStatement.executeQuery();

			while(rs.next()){
				Long id = rs.getLong("id");
				String constructeur = rs.getString("constructeur");
				int nb_places = rs.getInt("nb_places");

				vehicles.add(new Vehicle(id,constructeur,nb_places));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
		return vehicles;
	}
	public Vehicle findById(long id) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(FIND_VEHICLE_QUERY);
			preparedStatement.setLong(1,id);
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
				String constructeur = rs.getString("constructeur");
				int nb_places = rs.getInt("nb_places");
			preparedStatement.execute();
			preparedStatement.close();

			return new Vehicle(id, constructeur, nb_places);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

}
