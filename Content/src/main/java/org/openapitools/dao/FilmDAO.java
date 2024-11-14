package org.openapitools.dao;

import org.openapitools.model.Film;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
public class FilmDAO {
	private final DataSource dataSource;

    public FilmDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public List<Film> getFilms() {
    	List<Film> films = new ArrayList<>();
        String sql = "SELECT * FROM films";
        
        try (Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery()) {

               // Iterar sobre el ResultSet y construir los objetos Film
               while (resultSet.next()) {
                   Film film = new Film();
                   film.setFilmID(resultSet.getInt("filmID"));
                   film.setTitle(resultSet.getString("title"));
                   film.setGenreID(resultSet.getInt("genreID"));
                   film.setReleaseYear(resultSet.getInt("releaseYear"));
                   film.setDuration(resultSet.getInt("duration"));
                   film.setDescription(resultSet.getString("description"));
                   film.setPhotoURL(resultSet.getString("photoURL"));

                   java.sql.Array actorsArray = resultSet.getArray("arrayActors");
                   if (actorsArray != null) {
                       Integer[] actors = (Integer[]) actorsArray.getArray();
                       film.setArrayActors(Collections.unmodifiableList(Arrays.asList(actors)));
                   }

                   // Agregar la película a la lista
                   films.add(film);
               }

           } catch (SQLException e) {
               e.printStackTrace();
           }
        
    	return films;
    }
    
    public Film postFilm(Film film) {
        String sql = "INSERT INTO films (title, genreID, releaseYear, duration, description, photoURL, arrayActors) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Establecer los parámetros para la consulta
            preparedStatement.setString(1, film.getTitle());
            preparedStatement.setInt(2, film.getGenreID());
            preparedStatement.setInt(3, film.getReleaseYear());
            preparedStatement.setInt(4, film.getDuration());
            preparedStatement.setString(5, film.getDescription());
            preparedStatement.setString(6, film.getPhotoURL());

            java.sql.Array actorsArray = connection.createArrayOf("integer", film.getArrayActors().toArray(new Integer[0]));
            preparedStatement.setArray(7, actorsArray);

            // Ejecutar la consulta
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                // Obtener el ID generado automáticamente
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        film.setFilmID(generatedKeys.getInt(1)); // Establecer el ID en el objeto Film
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al insertar la película", e);
        }

        return film;  // Devolver el objeto Film con el ID generado
    }

    public Film getFilmById(Integer filmID) {
        Film film = null;
        String sql = "SELECT * FROM films WHERE filmID = ?";

        try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, filmID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
            	
            	Integer[] actorArray = (Integer[]) resultSet.getArray("arrayActors").getArray();
                List<Integer> arrayActors = new ArrayList<>();
                for (Integer actorId : actorArray) {
                    arrayActors.add(actorId);
                }
                             
                film = new Film(
                    resultSet.getInt("filmID"),
                    resultSet.getString("title"),
                    resultSet.getInt("genreID"),
                    resultSet.getInt("releaseYear"),
                    resultSet.getInt("duration"),
                    resultSet.getString("description"),
                    resultSet.getString("photoURL"),
                    arrayActors
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return film;
    }
    
    public Film putFilmById(Integer filmID, Film film) {
        Film newFilm = null;
        String sql = "UPDATE films SET title=?, genreID=?, releaseYear=?, duration=?, description=?, photoURL=?, arrayActors=? WHERE filmID = ?";
        
        try (Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, film.getTitle());
                preparedStatement.setInt(2, film.getGenreID());
                preparedStatement.setInt(3, film.getReleaseYear());
                preparedStatement.setInt(4, film.getDuration());
                preparedStatement.setString(5, film.getDescription());
                preparedStatement.setString(6, film.getPhotoURL());
                java.sql.Array actorsArray = connection.createArrayOf("integer", film.getArrayActors().toArray(new Integer[0]));
                preparedStatement.setArray(7, actorsArray);
                preparedStatement.setInt(8, filmID);
                int filas = preparedStatement.executeUpdate();
                if (filas > 0) {
                	newFilm = film;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return newFilm;
    }
    
    public boolean deleteFilmById(Integer filmID) {
    	boolean resultado = false;
        String sql = "DELETE FROM films WHERE filmID=?";

        try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, filmID);
            int filas = preparedStatement.executeUpdate();
            
            if(filas > 0) {
            	resultado = true;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return resultado;
    }
}
