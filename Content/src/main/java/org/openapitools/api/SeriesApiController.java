package org.openapitools.api;

import org.openapitools.dao.SerieDAO;
import org.openapitools.model.Episode;
import org.openapitools.model.Serie;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.constraints.*;
import javax.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-11-06T18:04:35.581569900+01:00[Europe/Madrid]", comments = "Generator version: 7.9.0")
@Controller
@RequestMapping("${openapi.aPIDeContenido.base-path:/v1/content}")
public class SeriesApiController implements SeriesApi {

    private final NativeWebRequest request;
    private final SerieDAO serieDAO;

    @Autowired
    public SeriesApiController(NativeWebRequest request, SerieDAO serieDAO) {
        this.request = request;
        this.serieDAO = serieDAO;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<List<Serie>> seriesGet() {
        List<Serie> series = serieDAO.getAllSeries();

        if (series.isEmpty()) {
            return ResponseEntity.noContent().build();  // 204 No Content si la lista está vacía
        }
        return ResponseEntity.ok(series);  // Devuelve la lista de actores si hay contenido
    }

    @Override 
    public ResponseEntity<Void> seriesSerieIDDelete(@PathVariable("serieID") Integer serieID) {
        boolean  result = serieDAO.deleteSerieById(serieID);
        if (result) {
            return ResponseEntity.ok().build();  // 200 OK si se ha eliminado correctamente
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 si no existe el actor
    }

    @Override
    public ResponseEntity<Serie> seriesPost(@RequestBody Serie serie) {
        Serie newSerie = serieDAO.insertSerie(serie);
        
        if (newSerie != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(newSerie);  // 201 Created y devuelve el actor en el cuerpo
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();  // 400 Bad Request si hubo un problema en la inserción
    }

    @Override
    public ResponseEntity<Serie> seriesSerieIDPut(@PathVariable("serieID") Integer serieID, @RequestBody Serie serie) {
        boolean result = serieDAO.updateSerieById(serieID, serie);
        
        if (result) {
            return ResponseEntity.ok(serie);  // 200 OK y devuelve el actor actualizado
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 si no existe el actor
    }

    @Override
    public ResponseEntity<Episode> seriesSerieIDEpisodesEpisodeIDGet(@PathVariable("serieID") Integer serieID, 
    @PathVariable("episodeID")Integer episodeID) {
        Episode episode = serieDAO.getEpisodeById(episodeID);
        if (episode != null && episode.getSerieID() ==  serieID) {
            return ResponseEntity.ok(episode);  // Devuelve el episodio si existe
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 si no existe el episod
        }
    }

    @Override
    public ResponseEntity<List<Episode>> seriesSerieIDEpisodesGet(@PathVariable("serieID") Integer serieID) {
        List<Episode> episodes = serieDAO.getAllEpisodes(serieID);
        
        if (episodes.isEmpty()) {
            return ResponseEntity.noContent().build();  // 204 No Content si la lista está vacía
        }
        return ResponseEntity.ok(episodes);  // Devuelve la lista de actores si hay contenido
    }

    @Override
    public ResponseEntity<Void> seriesSerieIDEpisodesPost(@PathVariable("serieID") Integer serieID, @RequestBody Episode episode) {
        if (episode != null) {
            boolean result = serieDAO.insertEpisode(serieID,episode);
            if (result) {
                return ResponseEntity.status(HttpStatus.CREATED).build();  // 201 Created
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();  // 400 Bad Request si hubo un
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 si no existe el episod
    }

    @Override
    public ResponseEntity<Void> seriesSerieIDEpisodesEpisodeIDPut(@PathVariable("serieID") Integer serieID, 
    @PathVariable("episodeID") Integer episodeID, @RequestBody Episode episode) {
        if (episode.getSerieID() == serieID) {
            boolean result =  serieDAO.updateEpisodeById(episodeID, episode);
            if (result) {
                return ResponseEntity.ok().build();  // 200 OK
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 si no existe el episod
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();  // 400 Bad Request si el episod
    }

    @Override
    public ResponseEntity<Void> seriesSerieIDEpisodesEpisodeIDDelete(@PathVariable("serieID") Integer serieID,
    @PathVariable("episodeID") Integer episodeID) {
        Episode episode =  serieDAO.getEpisodeById(episodeID);
        if (episode != null &&  episode.getSerieID() == serieID) {
            boolean result =  serieDAO.deleteEpisodeById(episodeID);
            if (result) {
                return ResponseEntity.ok().build();  // 200 OK
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 si no existe el episod
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 si no existe el episod
    }

    @Override
    public ResponseEntity<Serie> seriesSerieIDGet(@PathVariable("serieID") Integer serieID) {
        Serie serie = serieDAO.getSerieById(serieID);

        if (serie != null) {
            return ResponseEntity.ok(serie);  // Devuelve la película si existe
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 si no existe
        }
    }

    @Override
    public ResponseEntity<List<Serie>> seriesGenreGenreIDGet(Integer genreId) {
        List<Serie> series = serieDAO.getSerieByGenre(genreId);

        if (series.isEmpty()) {
            return ResponseEntity.noContent().build();  // 204 No Content si la lista está vacía
        }
        return ResponseEntity.ok(series);  // Devuelve la lista de actores si hay contenido
    }

    @Override
    public ResponseEntity<List<Serie>> seriesActorActorIDGet(Integer actorId) {
        List<Serie> series = serieDAO.getSerieByActorID(actorId);

        if (series.isEmpty()) {
            return ResponseEntity.noContent().build();  // 204 No Content si la lista está vacía
        }
        return ResponseEntity.ok(series);  // Devuelve la lista de actores si hay contenido
    }

    @Override
    public ResponseEntity<Serie> seriesTitleTitleGet(String title) {
        Serie serie = serieDAO.getSerieBytitle(title);

        if (serie == null) {
            return ResponseEntity.noContent().build();  // 204 No Content si la lista está vacía
        }
        return ResponseEntity.ok(serie);  // Devuelve la lista de actores si hay contenido
    }


}
