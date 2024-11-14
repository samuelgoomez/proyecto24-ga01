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
    
  
}
