package com.cybage.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cybage.model.Organizer;

public class OrganizerDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/organizer?useSSL=false";
	private String jdbcOrganizername = "root";
	private String jdbcPassword = "root";

	private static final String INSERT_ORGANIZERS_SQL = "INSERT INTO organizers" + "  (name, email, country) VALUES "
			+ " (?, ?, ?);";

	private static final String SELECT_ORGANIZER_BY_ID = "select id,name,email,country from organizers where id =?";
	private static final String SELECT_ALL_ORGANIZERS = "select * from organizers";
	private static final String DELETE_ORGANIZERS_SQL = "delete from organizers where id = ?;";
	private static final String UPDATE_ORGANIZERS_SQL = "update organizers set name = ?,email= ?, country =? where id = ?;";

	public OrganizerDAO() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcOrganizername, jdbcPassword);
		}
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connection;
	}

	public void insertOrganizer(Organizer organizer) throws SQLException {
		System.out.println(INSERT_ORGANIZERS_SQL);
	
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORGANIZERS_SQL)) {
			preparedStatement.setString(1, organizer.getName());
			preparedStatement.setString(2, organizer.getEmail());
			preparedStatement.setString(3, organizer.getCountry());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} 
		
		catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Organizer selectOrganizer(int id) {
		Organizer organizer = null;
		
		try (Connection connection = getConnection();
				
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORGANIZER_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String name = rs.getString("name");
				String email = rs.getString("email");
				String country = rs.getString("country");
				organizer = new Organizer(id, name, email, country);
			}
		} 
		
		catch (SQLException e) {
			printSQLException(e);
		}
		
		return organizer;
	}

	public List<Organizer> selectAllOrganizer() {

		List<Organizer> organizers = new ArrayList<>();
		
		try (Connection connection = getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ORGANIZERS);) {
			System.out.println(preparedStatement);
			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String country = rs.getString("country");
				organizers.add(new Organizer(id, name, email, country));
			}
		}
		
		catch (SQLException e) {
			printSQLException(e);
		}
		
		return organizers;
	}

	public boolean deleteOrganizer(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_ORGANIZERS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		
		return rowDeleted;
	}

	public boolean updateOrganizer(Organizer organizer) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_ORGANIZERS_SQL);) {
			statement.setString(1, organizer.getName());
			statement.setString(2, organizer.getEmail());
			statement.setString(3, organizer.getCountry());
			statement.setInt(4, organizer.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
}

