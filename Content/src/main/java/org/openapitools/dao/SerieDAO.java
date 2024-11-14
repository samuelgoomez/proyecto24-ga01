package org.openapitools.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.openapitools.model.Episode;
import org.openapitools.model.Serie;
import org.springframework.stereotype.Repository;

@Repository
public class SerieDAO {
    
    private final DataSource dataSource;


    public SerieDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public  List<Serie> getAllSeries() {
        List<Serie> series = new ArrayList<>();
        String sql = "SELECT * FROM series";

        try (Connection connection = dataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
            	
            	Integer[] actorArray = (Integer[]) rs.getArray("arrayActors").getArray();
                List<Integer> arrayActors = new ArrayList<>();
                for (Integer actorId : actorArray) {
                    arrayActors.add(actorId);
                }
            	
                Serie serie = new Serie(
                		rs.getInt("serieID"),
                        rs.getString("title"),
                        rs.getInt("seasons"),
                        rs.getInt("releaseYear"),
                        rs.getInt("genreID"),
                        rs.getString("description"),
                        rs.getString("photoURL"),
                        arrayActors
                );
                series.add(serie); 
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return series;
    }

}
