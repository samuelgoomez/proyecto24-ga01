package org.openapitools.api;

import org.openapitools.dao.ActorDAO;
import org.openapitools.model.Actor;


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
import org.springframework.web.bind.annotation.CrossOrigin;
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

@CrossOrigin(origins = "*")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-11-06T18:04:35.581569900+01:00[Europe/Madrid]", comments = "Generator version: 7.9.0")
@Controller
@RequestMapping("${openapi.aPIDeContenido.base-path:/v1/content}")
public class ActorsApiController implements ActorsApi {

    private final NativeWebRequest request;
    private final ActorDAO actorDAO;

    @Autowired
    public ActorsApiController(NativeWebRequest request, ActorDAO actorDAO) {
        this.request = request;
        this.actorDAO = actorDAO;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }
    
    @Override
    public ResponseEntity<Actor> actorsActorIDGet(@PathVariable("actorID") Integer actorID) {
        Actor actor = actorDAO.getActorById(actorID);

        if (actor != null) {
            return ResponseEntity.ok(actor);  // Devuelve la película si existe
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 si no existe
        }
    }

    @Override
    public ResponseEntity<List<Actor>> actorsGet() {
        List<Actor> actors = actorDAO.getAllActors();

        if (actors.isEmpty()) {
            return ResponseEntity.noContent().build();  // 204 No Content si la lista está vacía
        }
        return ResponseEntity.ok(actors);  // Devuelve la lista de actores si hay contenido
    }

    @Override 
    public ResponseEntity<Void> actorsActorIDDelete(@PathVariable("actorID") Integer actorID) {
        boolean  result = actorDAO.deleteActorById(actorID);
        if (result) {
            return ResponseEntity.ok().build();  // 200 OK si se ha eliminado correctamente
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 si no existe el actor
    }

    @Override
    public ResponseEntity<Actor> actorsPost(@RequestBody Actor actor) {
        Actor newActor = actorDAO.insertActor(actor);
        
        if (newActor != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(newActor);  // 201 Created y devuelve el actor en el cuerpo
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();  // 400 Bad Request si hubo un problema en la inserción
    }

    @Override
    public ResponseEntity<Actor> actorsActorIDPut(@PathVariable("actorID") Integer actorID, @RequestBody Actor actor) {
    	Actor newActor = actorDAO.updateActorById(actorID,actor);
        
        if (newActor != null) {
            return ResponseEntity.ok(newActor);  // 200 OK y devuelve el actor actualizado
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 si no existe el actor
    }

}
