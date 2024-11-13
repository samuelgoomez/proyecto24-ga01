package org.openapitools.dao;

import java.net.URI;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.openapitools.model.FilmList;
import org.openapitools.model.ModelList;
import org.openapitools.model.PaymentMethod;
import org.openapitools.model.Profile;
import org.openapitools.model.Profile.TypeEnum;
import org.openapitools.model.SeriesList;
import org.openapitools.model.SuscripcionPlan;
import org.openapitools.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class UserDAO {
	private final DataSource dataSource;
	
	@Autowired
    private WebClient webClient;
	
	 @Autowired
	    public UserDAO(DataSource dataSource) {
	        this.dataSource = dataSource;
	 }
	 
	 
	 public User addUser (User user) {
		 String addList = "INSERT INTO users (username,password,email,name,createdAt,preferences) VALUES (?,?,?,?,?,?)";
		 int userID = 0;
		 Timestamp createdAt;
		 
		 try (Connection connection = dataSource.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(addList,Statement.RETURN_GENERATED_KEYS)) {

	            // Establecer los parámetros para la consulta
	            preparedStatement.setString(1, user.getUsername());
	            preparedStatement.setString(2, user.getPassword());
	            preparedStatement.setString(3, user.getEmail());
	            preparedStatement.setString(4, user.getName());
	            
	            createdAt = Timestamp.from(Instant.now());
	            preparedStatement.setTimestamp(5, createdAt);     
	            
	            java.sql.Array preferences = connection.createArrayOf("integer", user.getPreferences().toArray(new Integer[0]));
	            preparedStatement.setArray(6, preferences);


	            // Ejecutar la consulta
	            int affectedRows = preparedStatement.executeUpdate();
	            
	            if (affectedRows > 0) {
	                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
	                    if (generatedKeys.next()) {
	                        userID = generatedKeys.getInt(1); // Suponiendo que `listID` es el ID generado
	                    }
	                }
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new RuntimeException("Error al insertar la película", e);
	        }
		 
		 Instant instant = createdAt.toInstant();
		 OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.UTC);
		 
		 User usuario = new User(
				 userID,
				 user.getUsername(),
				 user.getPassword(),
				 user.getEmail(),
				 user.getName(),
				 offsetDateTime,
				 user.getPreferences()
		 );
		 
		 return usuario;
	 }
	 
	 
	 public boolean deleteUser (int userID) {
		 boolean deleted = false; // Variable para verificar si se eliminaron filas
		    String sql = "DELETE FROM users WHERE userID = ?";

		    try (Connection connection = dataSource.getConnection(); // Obtener conexión
		        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
		        preparedStatement.setInt(1, userID); // Establecer el userId en el PreparedStatement
		        int rowsAffected = preparedStatement.executeUpdate(); // Ejecutar la actualización
		        deleted = rowsAffected > 0; // Verificar si se eliminaron filas
		    } catch (SQLException e) {
		        e.printStackTrace(); // Manejo de excepciones
		    }

		    return deleted; // Retornar true si se eliminaron filas, false de lo contrario
	 }
	 
	 
	 public User getUser(int userID) {
		 User user = null;
	        String sql = "SELECT * FROM users WHERE userID = ?";

	        try (Connection connection = dataSource.getConnection();
	            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	            preparedStatement.setInt(1, userID);
	            ResultSet resultSet = preparedStatement.executeQuery();
	            if (resultSet.next()) {

	            	Integer[] preferencesArray = (Integer[]) resultSet.getArray("preferences").getArray();
	                List<Integer> preferences = new ArrayList<>();
	                for (Integer filmID : preferencesArray) {
	                    preferences.add(filmID);
	                }
	            	
	            	user = new User (
	            			resultSet.getInt("userID"),
	            			resultSet.getString("username"),
	            			resultSet.getString("password"),
	            			resultSet.getString("email"),
	            			resultSet.getString("name"),
	            			resultSet.getTimestamp("createdAt").toInstant().atOffset(ZoneOffset.UTC),
	            			preferences
	            	);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return user;
	    }
	 
	 
	 public User editUser(int userID,User user) {
	        User newUser = null;
	        String sql = "UPDATE users SET username=?, password=?, email=?, name=?, preferences=? WHERE userID = ?";
	        
	        try (Connection connection = dataSource.getConnection();
	                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	                preparedStatement.setString(1, user.getUsername());
	                preparedStatement.setString(2, user.getPassword());
	                preparedStatement.setString(3, user.getEmail());
	                preparedStatement.setString(4, user.getName());
	                
	                java.sql.Array preferences = connection.createArrayOf("integer", user.getPreferences().toArray(new Integer[0]));
	                preparedStatement.setArray(5, preferences);
	                preparedStatement.setInt(6, userID);
	                int filas = preparedStatement.executeUpdate();
	                if (filas > 0) {
	                	newUser = user;
	                	newUser.setUserID(userID);
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        return newUser;
	 }
	 
	 
	 public List<User> getUsers () {
		 List<User> users = new ArrayList<>();
		 String sql = "SELECT * FROM users";
		 
		 try (Connection connection = dataSource.getConnection();
	                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	                
	                ResultSet resultSet = preparedStatement.executeQuery();
	                while (resultSet.next()) {
	                	
	                	Integer[] preferencesArray = (Integer[]) resultSet.getArray("preferences").getArray();
		                List<Integer> preferences = new ArrayList<>();
		                for (Integer filmID : preferencesArray) {
		                    preferences.add(filmID);
		                }
	                	
	                	User user = new User (
	                			resultSet.getInt("userID"),
	                			resultSet.getString("username"),
	                			resultSet.getString("password"),
	                			resultSet.getString("email"),
	                			resultSet.getString("name"),
	                			resultSet.getTimestamp("createdAt").toInstant().atOffset(ZoneOffset.UTC),
	                			preferences
	                	);
	                	
	                	users.add(user);
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
		 
		 return users;
	 }
	 
	 	
}
