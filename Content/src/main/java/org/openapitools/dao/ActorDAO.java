package org.openapitools.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.openapitools.model.Actor;
import org.springframework.stereotype.Repository;

@Repository
public class ActorDAO {

    private final DataSource dataSource;


    public ActorDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Actor getActorById(Integer actorID) {
        Actor actor = null;
        String sql = "SELECT * FROM actors WHERE actorID = ?";

        try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.clearParameters();
            preparedStatement.setInt(1, actorID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
            	
                actor = new Actor(
                    resultSet.getInt("actorID"),
                    resultSet.getString("name"),
                    resultSet.getDate("birthdayDate").toLocalDate().atStartOfDay().atOffset(ZoneOffset.UTC),
                    resultSet.getString("photoURL")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actor;
    }

    public  List<Actor> getAllActors() {
        List<Actor> actors = new ArrayList<>();
        String sql = "SELECT * FROM actors";

        try (Connection connection = dataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()) {
                Actor actor = new Actor(
                		resultSet.getInt("actorID"),
                        resultSet.getString("name"),
                        resultSet.getDate("birthdayDate").toLocalDate().atStartOfDay().atOffset(ZoneOffset.UTC),
                        resultSet.getString("photoURL")
                );
                actors.add(actor); 
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return actors;
    }

    public boolean deleteActorById(Integer actorID) {
        String sql = "DELETE FROM actors WHERE actorID = ?";

        try (Connection connection = dataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.clearParameters();
            ps.setInt(1, actorID);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Actor insertActor(Actor actor) {
        String sql = "INSERT INTO actors (name, birthdayDate, photoURL) VALUES (?, ?, ?)";
    
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.clearParameters();
            preparedStatement.setString(1, actor.getName());   
            
            java.sql.Date sqlDate = java.sql.Date.valueOf(actor.getBirthdayDate().toLocalDate());
            preparedStatement.setDate(2, sqlDate);
            
            preparedStatement.setString(3, actor.getPhotoURL());
    
            int rowsAffected = preparedStatement.executeUpdate();  
            
            if (rowsAffected > 0) {
                // Obtener el ID generado automáticamente
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        actor.setActorID(generatedKeys.getInt(1)); // Establecer el ID en el objeto Film
                    }
                }
            }
     
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return actor; 
    }
    

    public Actor updateActorById(Integer actorID, Actor actor) {
        String sql = "UPDATE actors SET name = ?, birthdayDate = ?, photoURL = ? WHERE actorID = ?";
        try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.clearParameters();
            preparedStatement.setString(1, actor.getName());
            
            java.sql.Date sqlDate = java.sql.Date.valueOf(actor.getBirthdayDate().toLocalDate());
            preparedStatement.setDate(2, sqlDate);
            
            preparedStatement.setString(3, actor.getPhotoURL());

            preparedStatement.setInt(4, actorID);

            int rowsAffected = preparedStatement.executeUpdate();
            
            if (rowsAffected > 0) {
                // Obtener el ID generado automáticamente
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        actor.setActorID(generatedKeys.getInt(1)); // Establecer el ID en el objeto Film
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return actor;
    }

    
}