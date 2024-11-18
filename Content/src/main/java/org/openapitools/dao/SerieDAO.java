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

    public boolean deleteSerieById(Integer serieID) {
        String sql = "DELETE FROM series WHERE serieID = ?";

        try (Connection connection = dataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.clearParameters();
            ps.setInt(1, serieID);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Serie insertSerie(Serie serie) {
        String sql = "INSERT INTO series (title, seasons,releaseYear, genreID,description,photoURL,arrayActors) VALUES (?, ?, ?, ?, ?,?,?)";
    
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.clearParameters();
            preparedStatement.setString(1, serie.getTitle()); 
            preparedStatement.setInt(2, serie.getSeasons());
            preparedStatement.setInt(3, serie.getReleaseYear());
            preparedStatement.setInt(4, serie.getGenreID());
            preparedStatement.setString(5, serie.getDescription()); 
            preparedStatement.setString(6, serie.getPhotoURL()); 
            
            java.sql.Array actorsArray = connection.createArrayOf("integer", serie.getArrayActors().toArray(new Integer[0]));
            preparedStatement.setArray(7, actorsArray);
    
            int rowsAffected = preparedStatement.executeUpdate();  
            
            if (rowsAffected > 0) {
                // Obtener el ID generado automáticamente
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        serie.setSerieID(generatedKeys.getInt(1)); // Establecer el ID en el objeto Film
                    }
                }
            }
     
        } catch (SQLException e) {
            e.printStackTrace();  
        }
        
        return serie;
    }

    public boolean updateSerieById(Integer serieID, Serie serie) {
        String sql = "UPDATE series SET title = ?, seasons = ?, releaseYear = ?,  genreID = ?, description = ?, photoURL = ?, arrayActors = ? WHERE serieID = ?";

        try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.clearParameters();
            preparedStatement.setString(1, serie.getTitle());
            preparedStatement.setInt(2, serie.getSeasons());
            preparedStatement.setInt(3, serie.getReleaseYear());
            preparedStatement.setInt(4, serie.getGenreID());
            preparedStatement.setString(5, serie.getDescription());
            preparedStatement.setString(6, serie.getPhotoURL());
            
            java.sql.Array actorsArray = connection.createArrayOf("integer", serie.getArrayActors().toArray(new Integer[0]));
            preparedStatement.setArray(7, actorsArray);
            
            preparedStatement.setInt(8, serieID);

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Episode> getAllEpisodes(Integer serieID) {
        List<Episode> episodes = new ArrayList<>();
        String sql = "SELECT *  FROM episodes WHERE serieID = ?";

        try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.clearParameters();
                preparedStatement.setInt(1, serieID);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Episode episode = new Episode(
                        resultSet.getInt("episodeID"),
                        resultSet.getInt("serieID"), 
                        resultSet.getInt("numEpisodio"),
                        resultSet.getInt("numTemporada"),
                        resultSet.getString("titulo"),
                        resultSet.getString("photoURL")
                    );

                    episodes.add(episode);
                }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return episodes;
    }

    public Episode getEpisodeById(Integer episodeID) {
        Episode episode = null;
        String  query = "SELECT * FROM episodes WHERE episodeID = ?";

        try  (Connection connection = dataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement(query)) {
            ps.clearParameters();
            ps.setInt(1, episodeID);

            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                episode = new Episode(
                		resultSet.getInt("episodeID"),
                        resultSet.getInt("serieID"), 
                        resultSet.getInt("numEpisodio"),
                        resultSet.getInt("numTemporada"),
                        resultSet.getString("titulo"),
                        resultSet.getString("photoURL")
                );
            } 
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return episode;
    }

    public boolean insertEpisode(Integer serieID,Episode episode) {
        String sql = "INSERT INTO episodes (serieID,numEpisodio, numTemporada, titulo,photoURL) VALUES (?, ?, ?, ?,?)";
    
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.clearParameters(); 
            preparedStatement.setInt(1, serieID); 
            preparedStatement.setInt(2, episode.getNumEpisodio());
            preparedStatement.setInt(3, episode.getNumTemporada());
            preparedStatement.setString(4, episode.getTitulo());
            preparedStatement.setString(5, episode.getPhotoURL());
    
            int rowsAffected = preparedStatement.executeUpdate();  
    
            return rowsAffected > 0;  
        } catch (SQLException e) {
            e.printStackTrace();
            return false;  
        }
    }

    public boolean updateEpisodeById(Integer episodeID, Episode episode) {
        String sql = "UPDATE episodes SET episodeID = ?, serieID = ?,  numEpisodio = ?,numTemporada = ?, titulo = ?, photoURL = ? WHERE episodeID = ?";

        try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.clearParameters();
            preparedStatement.setInt(1, episodeID);
            preparedStatement.setInt(2, episode.getSerieID());
            preparedStatement.setInt(3, episode.getNumEpisodio());
            preparedStatement.setInt(4, episode.getNumTemporada());
            preparedStatement.setString(5, episode.getTitulo());
            preparedStatement.setString(6, episode.getPhotoURL());
            preparedStatement.setInt(7, episodeID);

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteEpisodeById(Integer episodeID) {
        String sql = "DELETE FROM episodes WHERE episodeID = ?";

        try (Connection connection = dataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.clearParameters();
            ps.setInt(1, episodeID);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Serie getSerieById(Integer serieID) {
        Serie serie = null;
        String  query = "SELECT * FROM series WHERE serieID = ?";

        try  (Connection connection = dataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement(query)) {
            ps.clearParameters();
            ps.setInt(1, serieID);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	
            	Integer[] actorArray = (Integer[]) rs.getArray("arrayActors").getArray();
                List<Integer> arrayActors = new ArrayList<>();
                for (Integer actorId : actorArray) {
                    arrayActors.add(actorId);
                }
            	
                serie = new Serie(
                    rs.getInt("serieID"),
                    rs.getString("title"),
                    rs.getInt("seasons"),
                    rs.getInt("releaseYear"),
                    rs.getInt("genreID"),
                    rs.getString("description"),
                    rs.getString("photoURL"),
                    arrayActors
                );
            } 
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return serie;
    }

    public List<Serie> getSerieByGenre(Integer genreID) {
        List <Serie> series = new ArrayList<>();
        String  query = "SELECT * FROM series WHERE genreID = ?";

        try  (Connection connection = dataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement(query)) {
            ps.clearParameters();
            ps.setInt(1, genreID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	
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

    public Serie getSerieBytitle(String title) {
        Serie serie = null;
        String  query = "SELECT * FROM series WHERE title = ?";

        try  (Connection connection = dataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement(query)) {
            ps.clearParameters();
            ps.setString(1, title);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	
            	Integer[] actorArray = (Integer[]) rs.getArray("arrayActors").getArray();
                List<Integer> arrayActors = new ArrayList<>();
                for (Integer actorId : actorArray) {
                    arrayActors.add(actorId);
                }
            	
                serie = new Serie(
                		rs.getInt("serieID"),
                        rs.getString("title"),
                        rs.getInt("seasons"),
                        rs.getInt("releaseYear"),
                        rs.getInt("genreID"),
                        rs.getString("description"),
                        rs.getString("photoURL"),
                        arrayActors
                );
            } 
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return serie;
    }

    public List<Serie> getSerieByActorID(Integer actorId) {
        List<Serie> series = new ArrayList<>();
        String query = "SELECT * FROM series WHERE ? = ANY(arrayActors)";
    
        try (Connection connection = dataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement(query)) {
            ps.clearParameters();
            ps.setInt(1, actorId);
    
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
                Integer[] actorArray = (Integer[]) rs.getArray("arrayActors").getArray();
                List<Integer> arrayActors = new ArrayList<>();
                for (Integer aID : actorArray) {
                    arrayActors.add(aID);
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return series;
    }

}
