package com.epf.rentmanager.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;
import org.springframework.stereotype.Repository;

@Repository
public class ClientDao {

	private ClientDao() {
	}

	private static final String CREATE_CLIENT_QUERY = "INSERT INTO Client(nom, prenom, email, naissance) VALUES(?, ?, ?, ?);";
	private static final String DELETE_CLIENT_QUERY = "DELETE FROM Client WHERE id=?;";
	private static final String FIND_CLIENT_QUERY = "SELECT nom, prenom, email, naissance FROM Client WHERE id=?;";
	private static final String FIND_CLIENTS_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client;";

	public long create(Client client) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement ps =
					connection.prepareStatement(CREATE_CLIENT_QUERY);

			ps.setString(1, client.getNom());
			ps.setString(2, client.getPrenom());
			ps.setString(3, client.getEmail());
			ps.setDate(4, Date.valueOf(client.getNaissance()));
			ps.execute();
			ps.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
		return client.getId();
	}


	public long delete(Client client) throws DaoException {
		try {

			Connection connection = ConnectionManager.getConnection();

			PreparedStatement ps =
					connection.prepareStatement(DELETE_CLIENT_QUERY);
			ps.setLong(1, client.getId()); // ATTENTION /!\ : l’indice commence par 1, contrairement aux tableaux
			ps.execute();
		} catch (SQLException e) {
			throw new DaoException();


		}
		return client.getId();
	}

	public List<Client> findAll() throws DaoException {
		List<Client> clients = new ArrayList<Client>();
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(FIND_CLIENTS_QUERY);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Long id = rs.getLong("id");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String email = rs.getString("email");
				LocalDate naissance = rs.getDate("naissance").toLocalDate();

				clients.add(new Client(Math.toIntExact(id), nom, prenom, email, naissance));
			}
			preparedStatement.execute();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
		return clients;

	}

	public List<String> listExistingMail() throws DaoException {
		List<String> listMail = new ArrayList<String>();
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(FIND_CLIENTS_QUERY);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				String mail = rs.getString("email");
				listMail.add(mail);
			}
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return listMail;
	}


	public Client findById(long id) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(FIND_CLIENT_QUERY);
			preparedStatement.setLong(1,id);
			ResultSet rs = preparedStatement.executeQuery();

			rs.next();
			String nom = rs.getString("nom");
			String prenom = rs.getString("prenom");
			String email = rs.getString("email");
			LocalDate naissance = rs.getDate("naissance").toLocalDate();
			preparedStatement.execute();
			preparedStatement.close();


			return new Client(id, nom, prenom, email, naissance);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}
}

