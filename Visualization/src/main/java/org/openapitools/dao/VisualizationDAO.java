package org.openapitools.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.openapitools.model.OpcionesVisualizacion;
import org.openapitools.model.Visualizacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
public class VisualizationDAO {
	private final DataSource dataSource;
	
	@Autowired
    public VisualizationDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	public OpcionesVisualizacion playFilm (int userID,int filmID) {
		String verificacion = "SELECT COUNT(*) FROM visualizations WHERE userID=? AND filmID=?";
		String playAgain = "UPDATE visualizations SET progreso=?, estado=? WHERE userID=? AND filmID=?";
		String setVisualization = "INSERT INTO visualizations (userID,filmID,visualizationDate,progreso,estado,idioma,subtitulos) VALUES (?,?,?,?,?,?,?)";
		String getOptions = "SELECT visualizationID,estado,idioma,subtitulos FROM visualizations WHERE userID = ? AND filmID = ?";
		
		OpcionesVisualizacion opciones = null;
		
		boolean existente = false;
		
		Timestamp visualizationDate = Timestamp.from(Instant.now());
		
		try (Connection connection = dataSource.getConnection(); // Obtener conexión
		        PreparedStatement preparedStatement3 = connection.prepareStatement(verificacion)) {
				preparedStatement3.setInt(1, userID);
		        preparedStatement3.setInt(2, filmID); 
		        ResultSet resultSet = preparedStatement3.executeQuery();
		        if (resultSet.next()) {
		        	existente = resultSet.getInt(1) > 0;
		        }
		        
		    } catch (SQLException e) {
		        e.printStackTrace(); // Manejo de excepciones
		    }
		
		if (!existente) {
		
		try (Connection connection = dataSource.getConnection(); // Obtener conexión
		        PreparedStatement preparedStatement = connection.prepareStatement(setVisualization)) {
				preparedStatement.setInt(1, userID);
		        preparedStatement.setInt(2, filmID); 
		        preparedStatement.setTimestamp(3, visualizationDate); 
		        preparedStatement.setString(4, "Viendo");
		        preparedStatement.setString(5, "Reproduciendo");
		        preparedStatement.setString(6, "Español");
		        preparedStatement.setBoolean(7, false);
		        int rowsAffected = preparedStatement.executeUpdate();
		        
		    } catch (SQLException e) {
		        e.printStackTrace(); // Manejo de excepciones
		    }
		}
		
		else {
			try (Connection connection = dataSource.getConnection(); // Obtener conexión
			        PreparedStatement preparedStatement4 = connection.prepareStatement(playAgain)) {
					preparedStatement4.setString(1, "Viendo");
					preparedStatement4.setString(2, "Reproduciendo");
			        preparedStatement4.setInt(3, userID); 
			        preparedStatement4.setInt(4, filmID); 
			        int rowsAffected = preparedStatement4.executeUpdate();
			        
			    } catch (SQLException e) {
			        e.printStackTrace(); // Manejo de excepciones
			    }
		}
		
		try (Connection connection = dataSource.getConnection(); // Obtener conexión
		        PreparedStatement preparedStatement2 = connection.prepareStatement(getOptions)) {
		        preparedStatement2.setInt(1, userID); 
		        preparedStatement2.setInt(2, filmID); 
		        ResultSet resultSet = preparedStatement2.executeQuery();
		        
		        if (resultSet.next()) {
		        	 opciones = new OpcionesVisualizacion(
			                resultSet.getInt("visualizationID"),
			                resultSet.getString("estado"),
			                resultSet.getString("idioma"),
			                resultSet.getBoolean("subtitulos")
			            );
		        }
		        
		    } catch (SQLException e) {
		        e.printStackTrace(); // Manejo de excepciones
		    }
		
		return opciones;
	}
	
	public OpcionesVisualizacion playSerie (int userID, int serieID) {
		String verificacion = "SELECT COUNT(*) FROM visualizations WHERE userID=? AND serieID=?";
		String playAgain = "UPDATE visualizations SET progreso=?, estado=? WHERE userID=? AND serieID=?";
		String setVisualization = "INSERT INTO visualizations (userID,serieID,visualizationDate,progreso,estado,idioma,subtitulos) VALUES (?,?,?,?,?,?,?)";
		String getOptions = "SELECT visualizationID,estado,idioma,subtitulos FROM visualizations WHERE userID = ? AND serieID = ?";
		
		OpcionesVisualizacion opciones = null;
		
		boolean existente = false;
		
		Timestamp visualizationDate = Timestamp.from(Instant.now());
		
		try (Connection connection = dataSource.getConnection(); // Obtener conexión
		        PreparedStatement preparedStatement3 = connection.prepareStatement(verificacion)) {
				preparedStatement3.setInt(1, userID);
		        preparedStatement3.setInt(2, serieID); 
		        ResultSet resultSet = preparedStatement3.executeQuery();
		        if (resultSet.next()) {
		        	existente = resultSet.getInt(1) > 0;
		        }
		        
		    } catch (SQLException e) {
		        e.printStackTrace(); // Manejo de excepciones
		    }
		
		if(!existente) {
		
		try (Connection connection = dataSource.getConnection(); // Obtener conexión
		        PreparedStatement preparedStatement = connection.prepareStatement(setVisualization)) {
				preparedStatement.setInt(1, userID);
		        preparedStatement.setInt(2, serieID); 
		        preparedStatement.setTimestamp(3, visualizationDate); 
		        preparedStatement.setString(4, "Viendo");
		        preparedStatement.setString(5, "Reproduciendo");
		        preparedStatement.setString(6, "Español");
		        preparedStatement.setBoolean(7, false);
		        int rowsAffected = preparedStatement.executeUpdate();
		        
		    } catch (SQLException e) {
		        e.printStackTrace(); // Manejo de excepciones
		    }
		}
		
		else {
			try (Connection connection = dataSource.getConnection(); // Obtener conexión
			        PreparedStatement preparedStatement4 = connection.prepareStatement(playAgain)) {
					preparedStatement4.setString(1, "Viendo");
					preparedStatement4.setString(2, "Reproduciendo");
			        preparedStatement4.setInt(3, userID); 
			        preparedStatement4.setInt(4, serieID); 
			        int rowsAffected = preparedStatement4.executeUpdate();
			        
			    } catch (SQLException e) {
			        e.printStackTrace(); // Manejo de excepciones
			    }
		}
		
		try (Connection connection = dataSource.getConnection(); // Obtener conexión
		        PreparedStatement preparedStatement2 = connection.prepareStatement(getOptions)) {
		        preparedStatement2.setInt(1, userID); 
		        preparedStatement2.setInt(2, serieID); 
		        ResultSet resultSet = preparedStatement2.executeQuery();
		        
		        if (resultSet.next()) {
		        	 opciones = new OpcionesVisualizacion(
			                resultSet.getInt("visualizationID"),
			                resultSet.getString("estado"),
			                resultSet.getString("idioma"),
			                resultSet.getBoolean("subtitulos")
			            );
		        }
		        
		    } catch (SQLException e) {
		        e.printStackTrace(); // Manejo de excepciones
		    }
		
		return opciones;
	}

	public OpcionesVisualizacion changePauseFilm (int userID,int filmID, String estado) {
		String updateVisualization = "UPDATE visualizations SET estado = ? WHERE userID = ? AND filmID = ?";
		String getOptions = "SELECT visualizationID,estado,idioma,subtitulos FROM visualizations WHERE userID = ? AND filmID = ?";
		
		OpcionesVisualizacion opciones = null;
		
		try (Connection connection = dataSource.getConnection(); // Obtener conexión
		        PreparedStatement preparedStatement = connection.prepareStatement(updateVisualization)) {
				preparedStatement.setString(1, estado);
		        preparedStatement.setInt(2, userID); 
		        preparedStatement.setInt(3, filmID); 
		        int rowsAffected = preparedStatement.executeUpdate();
		        
		    } catch (SQLException e) {
		        e.printStackTrace(); // Manejo de excepciones
		    }
		
		try (Connection connection = dataSource.getConnection(); // Obtener conexión
		        PreparedStatement preparedStatement2 = connection.prepareStatement(getOptions)) {
		        preparedStatement2.setInt(1, userID); 
		        preparedStatement2.setInt(2, filmID); 
		        ResultSet resultSet = preparedStatement2.executeQuery();
		        
		        if (resultSet.next()) {
		        	 opciones = new OpcionesVisualizacion(
			                resultSet.getInt("visualizationID"),
			                resultSet.getString("estado"),
			                resultSet.getString("idioma"),
			                resultSet.getBoolean("subtitulos")
			            );
		        }
		        
		    } catch (SQLException e) {
		        e.printStackTrace(); // Manejo de excepciones
		    }
		
		return opciones;
	}

	public OpcionesVisualizacion changePauseSerie (int userID,int serieID, String estado) {
		String updateVisualization = "UPDATE visualizations SET estado = ? WHERE userID = ? AND serieID = ?";
		String getOptions = "SELECT visualizationID,estado,idioma,subtitulos FROM visualizations WHERE userID = ? AND serieID = ?";
		
		OpcionesVisualizacion opciones = null;
		
		try (Connection connection = dataSource.getConnection(); // Obtener conexión
		        PreparedStatement preparedStatement = connection.prepareStatement(updateVisualization)) {
				preparedStatement.setString(1, estado);
		        preparedStatement.setInt(2, userID); 
		        preparedStatement.setInt(3, serieID); 
		        int rowsAffected = preparedStatement.executeUpdate();
		        
		    } catch (SQLException e) {
		        e.printStackTrace(); // Manejo de excepciones
		    }
		
		try (Connection connection = dataSource.getConnection(); // Obtener conexión
		        PreparedStatement preparedStatement2 = connection.prepareStatement(getOptions)) {
		        preparedStatement2.setInt(1, userID); 
		        preparedStatement2.setInt(2, serieID); 
		        ResultSet resultSet = preparedStatement2.executeQuery();
		        
		        if (resultSet.next()) {
		        	 opciones = new OpcionesVisualizacion(
			                resultSet.getInt("visualizationID"),
			                resultSet.getString("estado"),
			                resultSet.getString("idioma"),
			                resultSet.getBoolean("subtitulos")
			            );
		        }
		        
		    } catch (SQLException e) {
		        e.printStackTrace(); // Manejo de excepciones
		    }
		
		return opciones;
	}

	public boolean endFilm (int userID,int filmID) {
		String updateVisualization = "UPDATE visualizations SET progreso = ? WHERE userID = ? AND filmID = ?";
		boolean result = false;
		
		OpcionesVisualizacion opciones = null;
		
		try (Connection connection = dataSource.getConnection(); // Obtener conexión
		        PreparedStatement preparedStatement = connection.prepareStatement(updateVisualization)) {
				preparedStatement.setString(1, "Finalizado");
		        preparedStatement.setInt(2, userID); 
		        preparedStatement.setInt(3, filmID); 
		        int rowsAffected = preparedStatement.executeUpdate();
		        result = rowsAffected > 0;
		        
		    } catch (SQLException e) {
		        e.printStackTrace(); // Manejo de excepciones
		    }
		
		return result;
	}

	public boolean endSerie (int userID,int serieID) {
		String updateVisualization = "UPDATE visualizations SET progreso = ? WHERE userID = ? AND serieID = ?";
		boolean result = false;
		
		OpcionesVisualizacion opciones = null;
		
		try (Connection connection = dataSource.getConnection(); // Obtener conexión
		        PreparedStatement preparedStatement = connection.prepareStatement(updateVisualization)) {
				preparedStatement.setString(1, "Finalizado");
		        preparedStatement.setInt(2, userID); 
		        preparedStatement.setInt(3, serieID); 
		        int rowsAffected = preparedStatement.executeUpdate();
		        result = rowsAffected > 0;
		        
		    } catch (SQLException e) {
		        e.printStackTrace(); // Manejo de excepciones
		    }
		
		return result;
	}

	public OpcionesVisualizacion changeLanguageFilm (int userID,int filmID, String language) {
		String updateVisualization = "UPDATE visualizations SET idioma = ? WHERE userID = ? AND filmID = ?";
		String getOptions = "SELECT visualizationID,estado,idioma,subtitulos FROM visualizations WHERE userID = ? AND filmID = ?";
		
		OpcionesVisualizacion opciones = null;
		
		try (Connection connection = dataSource.getConnection(); // Obtener conexión
		        PreparedStatement preparedStatement = connection.prepareStatement(updateVisualization)) {
				preparedStatement.setString(1, language);
		        preparedStatement.setInt(2, userID); 
		        preparedStatement.setInt(3, filmID); 
		        int rowsAffected = preparedStatement.executeUpdate();
		        
		    } catch (SQLException e) {
		        e.printStackTrace(); // Manejo de excepciones
		    }
		
		try (Connection connection = dataSource.getConnection(); // Obtener conexión
		        PreparedStatement preparedStatement2 = connection.prepareStatement(getOptions)) {
		        preparedStatement2.setInt(1, userID); 
		        preparedStatement2.setInt(2, filmID); 
		        ResultSet resultSet = preparedStatement2.executeQuery();
		        
		        if (resultSet.next()) {
		        	 opciones = new OpcionesVisualizacion(
			                resultSet.getInt("visualizationID"),
			                resultSet.getString("estado"),
			                resultSet.getString("idioma"),
			                resultSet.getBoolean("subtitulos")
			            );
		        }
		        
		    } catch (SQLException e) {
		        e.printStackTrace(); // Manejo de excepciones
		    }
		
		return opciones;
	}

	public OpcionesVisualizacion changeLanguageSerie (int userID,int serieID, String language) {
		String updateVisualization = "UPDATE visualizations SET idioma = ? WHERE userID = ? AND serieID = ?";
		String getOptions = "SELECT visualizationID,estado,idioma,subtitulos FROM visualizations WHERE userID = ? AND serieID = ?";
		
		OpcionesVisualizacion opciones = null;
		
		try (Connection connection = dataSource.getConnection(); // Obtener conexión
		        PreparedStatement preparedStatement = connection.prepareStatement(updateVisualization)) {
				preparedStatement.setString(1, language);
		        preparedStatement.setInt(2, userID); 
		        preparedStatement.setInt(3, serieID); 
		        int rowsAffected = preparedStatement.executeUpdate();
		        
		    } catch (SQLException e) {
		        e.printStackTrace(); // Manejo de excepciones
		    }
		
		try (Connection connection = dataSource.getConnection(); // Obtener conexión
		        PreparedStatement preparedStatement2 = connection.prepareStatement(getOptions)) {
		        preparedStatement2.setInt(1, userID); 
		        preparedStatement2.setInt(2, serieID); 
		        ResultSet resultSet = preparedStatement2.executeQuery();
		        
		        if (resultSet.next()) {
		        	 opciones = new OpcionesVisualizacion(
			                resultSet.getInt("visualizationID"),
			                resultSet.getString("estado"),
			                resultSet.getString("idioma"),
			                resultSet.getBoolean("subtitulos")
			            );
		        }
		        
		    } catch (SQLException e) {
		        e.printStackTrace(); // Manejo de excepciones
		    }
		
		return opciones;
	}
	
}
