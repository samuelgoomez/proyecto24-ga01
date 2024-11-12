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
	 
	 public ModelList addList (int userID, ModelList list) {
		 String addList = "INSERT INTO lists (userID,name,createdAt) VALUES (?,?,?)";
		 int listID = 0;
		 Timestamp createdAt;
		 List<Integer> films = new ArrayList<>();
		 List<Integer> series = new ArrayList<>();
		 
		 try (Connection connection = dataSource.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(addList,Statement.RETURN_GENERATED_KEYS)) {

	            // Establecer los parámetros para la consulta
	            preparedStatement.setInt(1, userID);
	            preparedStatement.setString(2, list.getName());
	            
	            createdAt = Timestamp.from(Instant.now());
	            preparedStatement.setTimestamp(3, createdAt);

	            // Ejecutar la consulta
	            int affectedRows = preparedStatement.executeUpdate();
	            
	            if (affectedRows > 0) {
	                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
	                    if (generatedKeys.next()) {
	                        listID = generatedKeys.getInt(1); // Suponiendo que `listID` es el ID generado
	                    }
	                }
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new RuntimeException("Error al insertar la película", e);
	        }
		 
		 Instant instant = createdAt.toInstant();
		 OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.UTC);
		 
		 ModelList lista = new ModelList(
				 listID,
				 userID,
				 list.getName(),
				 films,
				 series,
				 offsetDateTime
		);
		 
		 return lista;
	 }
	 
	 public PaymentMethod addPayment (int userID, PaymentMethod payment) {
		 String addList = "INSERT INTO payments (userID,cardNumber,expirationDate,cardHolderName) VALUES (?,?,?,?)";
		 int paymentID = 0;
		 
		 try (Connection connection = dataSource.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(addList,Statement.RETURN_GENERATED_KEYS)) {

	            // Establecer los parámetros para la consulta
	            preparedStatement.setInt(1, userID);
	            preparedStatement.setInt(2, payment.getCardNumber());
	            
	            LocalDateTime localDateTime = payment.getExpirationDate().atStartOfDay();
	            Timestamp expirationDate = Timestamp.valueOf(localDateTime);
	            preparedStatement.setTimestamp(3, expirationDate);
	            
	            preparedStatement.setString(4, payment.getCardHolderName());
	            

	            // Ejecutar la consulta
	            int affectedRows = preparedStatement.executeUpdate();
	            
	            if (affectedRows > 0) {
	                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
	                    if (generatedKeys.next()) {
	                        paymentID = generatedKeys.getInt(1); // Suponiendo que `listID` es el ID generado
	                    }
	                }
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new RuntimeException("Error al insertar la película", e);
	        }
		 
		 PaymentMethod pay = new PaymentMethod(
				 paymentID,
				 userID,
				 payment.getCardNumber(),
				 payment.getExpirationDate(),
				 payment.getCardHolderName()
		 );
		 
		 return pay;
	 }
	 
	 public Profile addProfile (int userID, Profile profile) {
		 String addList = "INSERT INTO profiles (userID,name,type,avatarURL,createdAt) VALUES (?,?,?,?,?)";
		 int profileID = 0;
		 Timestamp createdAt;
		 
		 try (Connection connection = dataSource.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(addList,Statement.RETURN_GENERATED_KEYS)) {

	            // Establecer los parámetros para la consulta
	            preparedStatement.setInt(1, userID);
	            preparedStatement.setString(2, profile.getName());
	            preparedStatement.setString(3, profile.getType().getValue());
	            preparedStatement.setString(4, profile.getAvatarURL().toString());
	            
	            createdAt = Timestamp.from(Instant.now());
	            preparedStatement.setTimestamp(5, createdAt);         

	            // Ejecutar la consulta
	            int affectedRows = preparedStatement.executeUpdate();
	            
	            if (affectedRows > 0) {
	                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
	                    if (generatedKeys.next()) {
	                        profileID = generatedKeys.getInt(1); // Suponiendo que `listID` es el ID generado
	                    }
	                }
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new RuntimeException("Error al insertar la película", e);
	        }
		 
		 Instant instant = createdAt.toInstant();
		 OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.UTC);
		 
		 Profile perfil = new Profile(
				 profileID,
				 userID,
				 profile.getName(),
				 profile.getType(),
				 profile.getAvatarURL(),
				 offsetDateTime
		 );
		 
		 return perfil;
	 }
	 
	 public boolean cancelSubPlan (int userID) {
		 boolean deleted = false; // Variable para verificar si se eliminaron filas
		    String sql = "DELETE FROM subPlans WHERE userID = ?";

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
	 
	 public boolean deleteList (int userID,int listID) {
		 boolean deleted = false; // Variable para verificar si se eliminaron filas
		    String sql = "DELETE FROM lists WHERE userID = ? AND listID = ?";

		    try (Connection connection = dataSource.getConnection(); // Obtener conexión
		         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
		        preparedStatement.setInt(1, userID); // Establecer el userId en el PreparedStatement
		        preparedStatement.setInt(2, listID);
		        int rowsAffected = preparedStatement.executeUpdate(); // Ejecutar la actualización
		        deleted = rowsAffected > 0; // Verificar si se eliminaron filas
		    } catch (SQLException e) {
		        e.printStackTrace(); // Manejo de excepciones
		    }

		    return deleted; // Retornar true si se eliminaron filas, false de lo contrario
	 }
	 
	 public boolean deletePayment (int userID) {
		 boolean deleted = false; // Variable para verificar si se eliminaron filas
		    String sql = "DELETE FROM payments WHERE userID = ?";

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
	 
	 public boolean deleteProfile (int userID, int profileID) {
		 boolean deleted = false; // Variable para verificar si se eliminaron filas
		    String sql = "DELETE FROM profiles WHERE userID = ? AND profileID = ?";

		    try (Connection connection = dataSource.getConnection(); // Obtener conexión
		        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
		        preparedStatement.setInt(1, userID); // Establecer el userId en el PreparedStatement
		        preparedStatement.setInt(2, profileID);
		        int rowsAffected = preparedStatement.executeUpdate(); // Ejecutar la actualización
		        deleted = rowsAffected > 0; // Verificar si se eliminaron filas
		    } catch (SQLException e) {
		        e.printStackTrace(); // Manejo de excepciones
		    }

		    return deleted; // Retornar true si se eliminaron filas, false de lo contrario
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
	 
	 public ModelList editList(int userID,int listID, ModelList list) {
	        ModelList newList = null;
	        String sql = "UPDATE lists SET name=? WHERE userID = ? AND listID = ?";
	        
	        try (Connection connection = dataSource.getConnection();
	                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	                preparedStatement.setString(1, list.getName());
	                preparedStatement.setInt(2, userID);
	                preparedStatement.setInt(3, listID);
	                int filas = preparedStatement.executeUpdate();
	                if (filas > 0) {
	                	newList = list;
	                	newList.setUserID(userID);
	                	newList.setListID(listID);
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        return newList;
	 }
	 
	 public Profile editProfile(int userID,int profileID, Profile profile) {
	        Profile newProfile = null;
	        String sql = "UPDATE profiles SET name=?, type=?, avatarURL=? WHERE userID = ? AND profileID = ?";
	        
	        try (Connection connection = dataSource.getConnection();
	                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	                preparedStatement.setString(1, profile.getName());
	                preparedStatement.setString(2, profile.getType().getValue());
	                preparedStatement.setString(3, profile.getAvatarURL().toString());
	                preparedStatement.setInt(4, userID);
	                preparedStatement.setInt(5, profileID);
	                int filas = preparedStatement.executeUpdate();
	                if (filas > 0) {
	                	newProfile = profile;
	                	newProfile.setUserID(userID);
	                	newProfile.setProfileID(profileID);
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        return profile;
	 }
	 
	 public ModelList getList(int userID, int listID) {
	        ModelList list = null;
	        String sql = "SELECT * FROM lists WHERE userID = ? AND listID = ?";

	        try (Connection connection = dataSource.getConnection();
	            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	            preparedStatement.setInt(1, userID);
	            preparedStatement.setInt(2, listID);
	            ResultSet resultSet = preparedStatement.executeQuery();
	            if (resultSet.next()) {
	            	Integer[] filmArray = (Integer[]) resultSet.getArray("films").getArray();
	                List<Integer> films = new ArrayList<>();
	                if(filmArray != null && filmArray.length>0) {
	                for (Integer filmID : filmArray) {
	                    films.add(filmID);
	                }
	                }
	                
	               
	                Integer[] serieArray = (Integer[]) resultSet.getArray("series").getArray();
	                List<Integer> series = new ArrayList<>();
	                if(serieArray != null && serieArray.length>0) {
	                for (Integer serieID : serieArray) {
	                    series.add(serieID);
	                }
	                }
	                
	                OffsetDateTime createdAt = resultSet.getTimestamp("createdAt").toInstant().atOffset(ZoneOffset.UTC);
	            	
	            	list = new ModelList (
	            			resultSet.getInt("listID"),
	            			resultSet.getInt("userID"),
	            			resultSet.getString("name"),
	            			films,
	            			series,
	            			createdAt
	            	);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return list;
	    }
	 
	 public PaymentMethod getPayment(int userID) {
	        PaymentMethod payment = null;
	        String sql = "SELECT * FROM payments WHERE userID = ?";

	        try (Connection connection = dataSource.getConnection();
	            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	            preparedStatement.setInt(1, userID);
	            ResultSet resultSet = preparedStatement.executeQuery();
	            if (resultSet.next()) {
	            	LocalDate expirationDate = resultSet.getTimestamp("expirationDate").toLocalDateTime().toLocalDate();
	            	
	            	payment = new PaymentMethod (
	            			resultSet.getInt("paymentID"),
	            			resultSet.getInt("userID"),
	            			resultSet.getInt("cardNumber"),
	            			expirationDate,
	            			resultSet.getString("cardHolderName")
	            	);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return payment;
	    }
	 
	 public Profile getProfile(int userID, int profileID) {
	        Profile perfil = null;
	        String sql = "SELECT * FROM profiles WHERE userID = ? AND profileID = ?";

	        try (Connection connection = dataSource.getConnection();
	            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	            preparedStatement.setInt(1, userID);
	            preparedStatement.setInt(2, profileID);
	            ResultSet resultSet = preparedStatement.executeQuery();
	            if (resultSet.next()) {
	            	
	            	perfil = new Profile (
	            			resultSet.getInt("profileID"),
	            			resultSet.getInt("userID"),
	            			resultSet.getString("name"),
	            			TypeEnum.fromValue(resultSet.getString("type")),
	            			URI.create(resultSet.getString("avatarURL")),
	            			resultSet.getTimestamp("createdAt").toInstant().atOffset(ZoneOffset.UTC)
	            	);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return perfil;
	    }
	 
	 public SuscripcionPlan getPlan(int userID) {
		 SuscripcionPlan plan = null;
	        String sql = "SELECT * FROM subPlans WHERE userID = ?";

	        try (Connection connection = dataSource.getConnection();
	            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	            preparedStatement.setInt(1, userID);
	            ResultSet resultSet = preparedStatement.executeQuery();
	            if (resultSet.next()) {
	            	
	            	plan = new SuscripcionPlan (
	            			resultSet.getInt("planID"),
	            			resultSet.getInt("userID"),
	            			resultSet.getTimestamp("startDate").toLocalDateTime().toLocalDate(),
	            			resultSet.getTimestamp("endDate").toLocalDateTime().toLocalDate(),
	            			resultSet.getInt("paymentID")
	            	);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return plan;
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
	 
	 public List<ModelList> getLists(int userID) {
		 List<ModelList> lists = new ArrayList<>();
	        String sql = "SELECT * FROM lists WHERE userID = ?";

	        try (Connection connection = dataSource.getConnection();
	            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	            preparedStatement.setInt(1, userID);
	            ResultSet resultSet = preparedStatement.executeQuery();
	            while (resultSet.next()) {

	            	Integer[] filmArray = (Integer[]) resultSet.getArray("films").getArray();
	                List<Integer> films = new ArrayList<>();
	                if(filmArray != null && filmArray.length>0) {
	                for (Integer filmID : filmArray) {
	                    films.add(filmID);
	                }
	                }
	                
	               
	                Integer[] serieArray = (Integer[]) resultSet.getArray("series").getArray();
	                List<Integer> series = new ArrayList<>();
	                if(serieArray != null && serieArray.length>0) {
	                for (Integer serieID : serieArray) {
	                    series.add(serieID);
	                }
	                }
	                
	                OffsetDateTime createdAt = resultSet.getTimestamp("createdAt").toInstant().atOffset(ZoneOffset.UTC);
	            	
	            	ModelList list = new ModelList (
	            			resultSet.getInt("listID"),
	            			resultSet.getInt("userID"),
	            			resultSet.getString("name"),
	            			films,
	            			series,
	            			createdAt
	            	);
	            	
	            	lists.add(list);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return lists;
	    }
	 
	 public SuscripcionPlan addPlan (int userID,SuscripcionPlan plan) {
		 String addList = "INSERT INTO subPlans (planID,userID,startDate,endDate,paymentID) VALUES (?,?,?,?,?)";
		 Timestamp startDate = Timestamp.from(Instant.now());
		 Timestamp endDate = Timestamp.valueOf(startDate.toLocalDateTime().plusMonths(1));
		 SuscripcionPlan newPlan = null;
		 
		 try (Connection connection = dataSource.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(addList)) {

	            // Establecer los parámetros para la consulta
			 	preparedStatement.setInt(1, plan.getPlanID());
	            preparedStatement.setInt(2, userID);
	            preparedStatement.setTimestamp(3, startDate);
	            preparedStatement.setTimestamp(4, endDate);
	            preparedStatement.setInt(5, plan.getPaymentID());

	            // Ejecutar la consulta
	            int affectedRows = preparedStatement.executeUpdate();
	            
	            if (affectedRows > 0) {
	            	 newPlan = new SuscripcionPlan(
	       				 plan.getPlanID(),
	       				 userID,
	       				 startDate.toLocalDateTime().toLocalDate(),
	       				 endDate.toLocalDateTime().toLocalDate(),
	       				 plan.getPaymentID()
	       		 );
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new RuntimeException("Error al insertar la película", e);
	        }	 
		 
		 return newPlan;
	 }
	 
	 public List<Profile> getProfiles(int userID) {
		 List<Profile> lists = new ArrayList<>();
	        String sql = "SELECT * FROM profiles WHERE userID = ?";

	        try (Connection connection = dataSource.getConnection();
	            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	            preparedStatement.setInt(1, userID);
	            ResultSet resultSet = preparedStatement.executeQuery();
	            while (resultSet.next()) {
	            	
	               Profile perfil = new Profile (
	            			resultSet.getInt("profileID"),
	            			resultSet.getInt("userID"),
	            			resultSet.getString("name"),
	            			TypeEnum.fromValue(resultSet.getString("type")),
	            			URI.create(resultSet.getString("avatarURL")),
	            			resultSet.getTimestamp("createdAt").toInstant().atOffset(ZoneOffset.UTC)
	            	);
	            	
	            	lists.add(perfil);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return lists;
	    }
	 
	 public User login (String username, String password) {
		 User user = null;
		    String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

		    try (Connection connection = dataSource.getConnection(); // Obtener conexión
		        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
		        preparedStatement.setString(1, username); // Establecer el userId en el PreparedStatement
		        preparedStatement.setString(2, password);
		        ResultSet resultSet = preparedStatement.executeQuery(); // Ejecutar la actualización

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
		        e.printStackTrace(); // Manejo de excepciones
		    }

		    return user; // Retornar true si se eliminaron filas, false de lo contrario
	 }
	 
	 public boolean logout () {
		 return true;
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
	 
	 public void deleteListFilm(int userID,int listID,int filmID) {
	        java.sql.Array filmsArray = null;
	        List<Integer> filmsList = null;
	        
	        String sql = "SELECT films FROM lists WHERE userID=? AND listID=?";
	        
	        try (Connection connection = dataSource.getConnection();
	                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	                preparedStatement.setInt(1, userID);
	                preparedStatement.setInt(2, listID);
	                
	                ResultSet resultSet = preparedStatement.executeQuery();
	                if (resultSet.next()) {
	                	filmsArray = resultSet.getArray("films");
	                	Integer[] films = (Integer[]) filmsArray.getArray();
	                	 filmsList = new ArrayList<>(Arrays.asList(films));
	                	 filmsList.remove(Integer.valueOf(filmID));
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        
	        String sql2 = "UPDATE lists SET films=? WHERE userID=? AND listID=?";
	        
	        try (Connection connection = dataSource.getConnection();
	                PreparedStatement preparedStatement = connection.prepareStatement(sql2)) {
	        		
	        		java.sql.Array films = connection.createArrayOf("integer", filmsList.toArray(new Integer[0]));
	        		preparedStatement.setArray(1, films);
	        		
	                preparedStatement.setInt(2, userID);
	                preparedStatement.setInt(3, listID);
	                
	                int filas = preparedStatement.executeUpdate();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	 }
	 
	 public void addListFilm(int userID,int listID,int filmID) {
	        Array filmsArray = null;
	        List<Integer> filmsList = null;
	        
	        String sql = "SELECT films FROM lists WHERE userID=? AND listID=?";
	        
	        try (Connection connection = dataSource.getConnection();
	                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	                preparedStatement.setInt(1, userID);
	                preparedStatement.setInt(2, listID);
	                
	                ResultSet resultSet = preparedStatement.executeQuery();
	                if (resultSet.next()) {
	                	filmsArray = resultSet.getArray("films");
	                	Integer[] films = (Integer[]) filmsArray.getArray();
	                	 filmsList = new ArrayList<>(Arrays.asList(films));
	                	 filmsList.add(filmID);
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        
	        String sql2 = "UPDATE lists SET films=? WHERE userID=? AND listID=?";
	        
	        try (Connection connection = dataSource.getConnection();
	                PreparedStatement preparedStatement = connection.prepareStatement(sql2)) {
	        	
	        		java.sql.Array films = connection.createArrayOf("integer", filmsList.toArray(new Integer[0]));
	        		preparedStatement.setArray(1, films);
	        		
	                preparedStatement.setInt(2, userID);
	                preparedStatement.setInt(3, listID);
	                
	                int filas = preparedStatement.executeUpdate();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	 }
	 
	 public List<FilmList> getListFilm (int userID, int listID) {
		 List<FilmList> listFilms = new ArrayList<>();
		 List<Integer> idList = null;
		 Array filmsArray = null;
		 String sql = "SELECT films FROM lists WHERE userID=? AND listID=?";
		 
		 try (Connection connection = dataSource.getConnection();
	                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	                preparedStatement.setInt(1, userID);
	                preparedStatement.setInt(2, listID);
	                
	                ResultSet resultSet = preparedStatement.executeQuery();
	                if (resultSet.next()) {
	                	filmsArray = resultSet.getArray("films");
	                	Integer[] films = (Integer[]) filmsArray.getArray();
	                	 idList = new ArrayList<>(Arrays.asList(films));
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
		 
		 String filmServiceUrl = "http://localhost:8080/v1/content/films/{id}";
		 
		 for(int i=0;i<idList.size();i++) {
			 Mono<FilmList> filmMono = webClient.get()
		                .uri(filmServiceUrl, idList.get(i))
		                .retrieve()
		                .bodyToMono(Map.class) // Recibe la respuesta como un Map
		                .map(response -> {
		                    int filmID = ((Number) response.get("filmID")).intValue();
		                    String title = (String) response.get("title");
		                    return new FilmList(filmID, title);
		                }).onErrorResume(e -> Mono.empty());
			 FilmList finalFilm = filmMono.block();
			 listFilms.add(finalFilm);
		 }
		 
		 return listFilms;
	 }
	 
	 public void deleteListSerie(int userID,int listID,int serieID) {
	        java.sql.Array seriesArray = null;
	        List<Integer> seriesList = null;
	        
	        String sql = "SELECT series FROM lists WHERE userID=? AND listID=?";
	        
	        try (Connection connection = dataSource.getConnection();
	                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	                preparedStatement.setInt(1, userID);
	                preparedStatement.setInt(2, listID);
	                
	                ResultSet resultSet = preparedStatement.executeQuery();
	                if (resultSet.next()) {
	                	seriesArray = resultSet.getArray("series");
	                	Integer[] films = (Integer[]) seriesArray.getArray();
	                	seriesList = new ArrayList<>(Arrays.asList(films));
	                	seriesList.remove(Integer.valueOf(serieID));
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        
	        String sql2 = "UPDATE lists SET series=? WHERE userID=? AND listID=?";
	        
	        try (Connection connection = dataSource.getConnection();
	                PreparedStatement preparedStatement = connection.prepareStatement(sql2)) {
	        		
	        		java.sql.Array series = connection.createArrayOf("integer", seriesList.toArray(new Integer[0]));
	        		preparedStatement.setArray(1, series);
	        		
	                preparedStatement.setInt(2, userID);
	                preparedStatement.setInt(3, listID);
	                
	                int filas = preparedStatement.executeUpdate();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	 }
	 
	 public void addListSerie(int userID,int listID,int serieID) {
	        Array seriesArray = null;
	        List<Integer> seriesList = null;
	        
	        String sql = "SELECT series FROM lists WHERE userID=? AND listID=?";
	        
	        try (Connection connection = dataSource.getConnection();
	                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	                preparedStatement.setInt(1, userID);
	                preparedStatement.setInt(2, listID);
	                
	                ResultSet resultSet = preparedStatement.executeQuery();
	                if (resultSet.next()) {
	                	seriesArray = resultSet.getArray("series");
	                	Integer[] films = (Integer[]) seriesArray.getArray();
	                	seriesList = new ArrayList<>(Arrays.asList(films));
	                	seriesList.add(serieID);
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        
	        String sql2 = "UPDATE lists SET series=? WHERE userID=? AND listID=?";
	        
	        try (Connection connection = dataSource.getConnection();
	                PreparedStatement preparedStatement = connection.prepareStatement(sql2)) {
	        	
	        		java.sql.Array series = connection.createArrayOf("integer", seriesList.toArray(new Integer[0]));
	        		preparedStatement.setArray(1, series);
	        		
	                preparedStatement.setInt(2, userID);
	                preparedStatement.setInt(3, listID);
	                
	                int filas = preparedStatement.executeUpdate();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	 }
	 
	 public List<SeriesList> getListSeries (int userID, int listID) {
		 List<SeriesList> listSeries = new ArrayList<>();
		 List<Integer> idList = null;
		 Array seriesArray = null;
		 String sql = "SELECT series FROM lists WHERE userID=? AND listID=?";
		 
		 try (Connection connection = dataSource.getConnection();
	                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	                preparedStatement.setInt(1, userID);
	                preparedStatement.setInt(2, listID);
	                
	                ResultSet resultSet = preparedStatement.executeQuery();
	                if (resultSet.next()) {
	                	seriesArray = resultSet.getArray("series");
	                	Integer[] films = (Integer[]) seriesArray.getArray();
	                	 idList = new ArrayList<>(Arrays.asList(films));
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
		 
		 String filmServiceUrl = "http://localhost:8080/v1/content/series/{id}";
		 
		 for(int i=0;i<idList.size();i++) {
			 Mono<SeriesList> serieMono = webClient.get()
		                .uri(filmServiceUrl, idList.get(i))
		                .retrieve()
		                .bodyToMono(Map.class) // Recibe la respuesta como un Map
		                .map(response -> {
		                    int serieID = ((Number) response.get("serieID")).intValue();
		                    String title = (String) response.get("title");
		                    return new SeriesList(serieID, title);
		                }).onErrorResume(e -> Mono.empty());
			 SeriesList finalSerie = serieMono.block();
			 listSeries.add(finalSerie);
		 }
		 
		 return listSeries;
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
