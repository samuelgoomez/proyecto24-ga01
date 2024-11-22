package org.openapitools.api;

import org.openapitools.dao.UserDAO;
import org.openapitools.model.CancelSuscripcionPlan200Response;
import org.openapitools.model.DeleteList200Response;
import org.openapitools.model.DeletePaymentMethod200Response;
import org.openapitools.model.DeleteProfile200Response;
import org.openapitools.model.DeleteUser200Response;
import org.openapitools.model.FilmList;
import org.openapitools.model.LogoutUser200Response;
import org.openapitools.model.ModelList;
import org.openapitools.model.PaymentMethod;
import org.openapitools.model.Profile;
import org.openapitools.model.SeriesList;
import org.openapitools.model.SuscripcionPlan;
import org.openapitools.model.User;
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
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.validation.constraints.*;
import javax.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

@CrossOrigin(origins = "*")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-11-05T01:15:13.550616600+01:00[Europe/Madrid]", comments = "Generator version: 7.9.0")
@Controller
@RequestMapping("${openapi.aPIDeUsuario.base-path:/v1/user}")
public class UserApiController implements UserApi {

    private final NativeWebRequest request;
    private final UserDAO userDAO;

    @Autowired
    public UserApiController(NativeWebRequest request, UserDAO userDAO) {
        this.request = request;
        this.userDAO = userDAO;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }
    
    
    @Override
    public ResponseEntity<User> createUser(@RequestBody User user) {
    	User usuario = userDAO.addUser(user);
    	
    	if (usuario != null) {
            return ResponseEntity.ok(usuario);  // Devuelve la película si existe
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 si no existe
        }
    }
    
    @Override
    public ResponseEntity<DeleteUser200Response> deleteUser(@PathVariable("userID") Integer userID) {
    	boolean respuesta = userDAO.deleteUser(userID);
    	DeleteUser200Response mensaje = new DeleteUser200Response();
    	
    	if (respuesta) {
    		mensaje.message("Usuario eliminado");
            return ResponseEntity.ok(mensaje);  // Devuelve la película si existe
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 si no existe
        }
    }
    
    
    @Override
    public ResponseEntity<User> getUserById(@PathVariable("userID") Integer userID) {
    	User user = userDAO.getUser(userID);
    	
    	if (user != null) {
            return ResponseEntity.ok(user);  // Devuelve la película si existe
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 si no existe
        }
    }
    
    @Override
    public ResponseEntity<User> updateUser(@PathVariable("userID") Integer userID,@RequestBody User user) {
    	User usuario = userDAO.editUser(userID,user);
    	
    	if (usuario != null) {
            return ResponseEntity.ok(usuario);  // Devuelve la película si existe
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 si no existe
        }
    }

    @Override
    public ResponseEntity<User> loginUser(@RequestParam(value = "username", required = true) String username,@RequestParam(value = "password", required = true) String password) {
    	User user = userDAO.login(username,password);
    	
    	if (user != null) {
            return ResponseEntity.ok(user);  // Devuelve la película si existe
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 si no existe
        }
    }

    @Override
    public ResponseEntity<LogoutUser200Response> logoutUser() {
    	boolean respuesta = userDAO.logout();
    	LogoutUser200Response mensaje = new LogoutUser200Response();
    	
    	if (respuesta) {
    		mensaje.message("Sesión Cerrada");
            return ResponseEntity.ok(mensaje);  // Devuelve la película si existe
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 si no existe
        }
    }

    @Override
    public ResponseEntity<SuscripcionPlan> hireSuscripcionPlan(@PathVariable("userID") Integer userID,@RequestBody SuscripcionPlan suscripcionPlan) {
    	SuscripcionPlan plan = userDAO.addPlan(userID,suscripcionPlan);
    	
    	if (plan != null) {
            return ResponseEntity.ok(plan);  // Devuelve la película si existe
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 si no existe
        }
    }

    @Override
    public ResponseEntity<SuscripcionPlan> getSuscripcionPlan(@PathVariable("userID") Integer userID) {
    	SuscripcionPlan plan = userDAO.getPlan(userID);
    	
    	if (plan != null) {
            return ResponseEntity.ok(plan);  // Devuelve la película si existe
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 si no existe
        }
    }

    @Override
    public ResponseEntity<CancelSuscripcionPlan200Response> cancelSuscripcionPlan(@PathVariable("userID") Integer userID) {
    	boolean respuesta = userDAO.cancelSubPlan(userID);
    	CancelSuscripcionPlan200Response mensaje = new CancelSuscripcionPlan200Response();
    	
    	if (respuesta) {
    		mensaje.message("Plan de suscrición cancelado");
            return ResponseEntity.ok(mensaje);  // Devuelve la película si existe
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 si no existe
        }
    }

    @Override
    public ResponseEntity<PaymentMethod> getPaymentMethod(@PathVariable("userID") Integer userID) {
    	PaymentMethod payment = userDAO.getPayment(userID);
    	
    	if (payment != null) {
            return ResponseEntity.ok(payment);  // Devuelve la película si existe
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 si no existe
        }
    }

    @Override
    public ResponseEntity<PaymentMethod> addPaymentMethod(@PathVariable("userID") Integer userID, @RequestBody PaymentMethod paymentMethod) {
    	PaymentMethod payment = userDAO.addPayment(userID, paymentMethod);
    	
    	if (payment != null) {
            return ResponseEntity.ok(payment);  // Devuelve la película si existe
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 si no existe
        }
    }

    @Override
    public ResponseEntity<DeletePaymentMethod200Response> deletePaymentMethod(@PathVariable("userID") Integer userID) {
    	boolean respuesta = userDAO.deletePayment(userID);
    	DeletePaymentMethod200Response mensaje = new DeletePaymentMethod200Response();
    	
    	if (respuesta) {
    		mensaje.message("Metodo de pago eliminado");
            return ResponseEntity.ok(mensaje);  // Devuelve la película si existe
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 si no existe
        }
    }

    @Override
    public ResponseEntity<Profile> getProfileById(@PathVariable("userID") Integer userID,@PathVariable("profileID") Integer profileID) {
    	Profile perfil = userDAO.getProfile(userID,profileID);
    	
    	if (perfil != null) {
            return ResponseEntity.ok(perfil);  // Devuelve la película si existe
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 si no existe
        }
    }

    @Override
    public ResponseEntity<Profile> addProfile(@PathVariable("userID") Integer userID, @RequestBody Profile profile) {
    	Profile perfil = userDAO.addProfile(userID, profile);
    	
    	if (perfil != null) {
            return ResponseEntity.ok(perfil);  // Devuelve la película si existe
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 si no existe
        }
    }

    @Override
    public ResponseEntity<Profile> editProfile(@PathVariable("userID") Integer userID,@PathVariable("profileID") Integer profileID,@RequestBody Profile profile) {
    	Profile perfil = userDAO.editProfile(userID,profileID,profile);
    	
    	if (perfil != null) {
            return ResponseEntity.ok(perfil);  // Devuelve la película si existe
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 si no existe
        }
    }

    @Override
    public ResponseEntity<DeleteProfile200Response> deleteProfile(@PathVariable("userID") Integer userID,@PathVariable("profileID") Integer profileID) {
    	boolean respuesta = userDAO.deleteProfile(userID,profileID);
    	DeleteProfile200Response mensaje = new DeleteProfile200Response();
    	
    	if (respuesta) {
    		mensaje.message("Perfil eliminado");
            return ResponseEntity.ok(mensaje);  // Devuelve la película si existe
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 si no existe
        }
    }

    @Override
    public ResponseEntity<List<Profile>> listProfiles(@PathVariable("userID") Integer userID) {
    	List<Profile> lists = userDAO.getProfiles(userID);
    	
    	if (!lists.isEmpty()) {
            return ResponseEntity.ok(lists);  // Devuelve la película si existe
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 si no existe
        }
    }

    @Override
    public ResponseEntity<ModelList> addList(@PathVariable("userID") Integer userID, @RequestBody ModelList modelList) {
    	ModelList lista = userDAO.addList(userID, modelList);
    	
    	if (lista != null) {
            return ResponseEntity.ok(lista);  // Devuelve la película si existe
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 si no existe
        }
    }

    @Override
    public ResponseEntity<DeleteList200Response> deleteList(@PathVariable("userID") Integer userID,@PathVariable("listID") Integer listID) {
    	boolean respuesta = userDAO.deleteList(userID,listID);
    	DeleteList200Response mensaje = new DeleteList200Response();
    	
    	if (respuesta) {
    		mensaje.message("Lista eliminada");
            return ResponseEntity.ok(mensaje);  // Devuelve la película si existe
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 si no existe
        }
    }

    @Override
    public ResponseEntity<ModelList> editList(@PathVariable("userID") Integer userID,@PathVariable("listID") Integer listID,@RequestBody ModelList modelList) {
    	ModelList lista = userDAO.editList(userID,listID,modelList);
    	
    	if (lista != null) {
            return ResponseEntity.ok(lista);  // Devuelve la película si existe
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 si no existe
        }
    }

    @Override
    public ResponseEntity<ModelList> getList(@PathVariable("userID") Integer userID,@PathVariable("listID") Integer listID) {
    	ModelList list = userDAO.getList(userID,listID);
    	
    	if (list != null) {
            return ResponseEntity.ok(list);  // Devuelve la película si existe
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 si no existe
        }
    }

    @Override
    public ResponseEntity<List<ModelList>> getUserLists(@PathVariable("userID") Integer userID) {
    	List<ModelList> lists = userDAO.getLists(userID);
    	
    	if (!lists.isEmpty()) {
            return ResponseEntity.ok(lists);  // Devuelve la película si existe
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 si no existe
        }
    }

    @Override
    public ResponseEntity<Void> userUserIDListListIDSerieSerieIDDelete(@PathVariable("userID") Integer userID,@PathVariable("listID") Integer listID,@PathVariable("serieID") Integer serieID) {
    	userDAO.deleteListSerie(userID,listID,serieID);
    	return null;
    }
    
    @Override
    public ResponseEntity<Void> userUserIDListListIDSerieSerieIDPost(@PathVariable("userID") Integer userID,@PathVariable("listID") Integer listID,@PathVariable("serieID") Integer serieID) {
    	userDAO.addListSerie(userID,listID,serieID);
    	return null;
    }
    
    @Override
    public ResponseEntity<List<SeriesList>> userUserIDListListIDSeriesGet(@PathVariable("userID") Integer userID,@PathVariable("listID") Integer listID) {
    	List<SeriesList> series = userDAO.getListSeries(userID,listID);
    	
    	if (series != null) {
            return ResponseEntity.ok(series);  // Devuelve la película si existe
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 si no existe
        }
    }

    @Override
    public ResponseEntity<Void> userUserIDListListIDFilmFilmIDDelete(@PathVariable("userID") Integer userID,@PathVariable("listID") Integer listID,@PathVariable("filmID") Integer filmID) {
    	userDAO.deleteListFilm(userID,listID,filmID);
    	return null;
    }
    
    @Override
    public ResponseEntity<Void> userUserIDListListIDFilmFilmIDPost(@PathVariable("userID") Integer userID,@PathVariable("listID") Integer listID,@PathVariable("filmID") Integer filmID) {
    	userDAO.addListFilm(userID,listID,filmID);
    	return null;
    }
    
    @Override
    public ResponseEntity<List<FilmList>> userUserIDListListIDFilmsGet(@PathVariable("userID") Integer userID,@PathVariable("listID") Integer listID) {
    	List<FilmList> films = userDAO.getListFilm(userID,listID);
    	
    	if (films != null) {
            return ResponseEntity.ok(films);  // Devuelve la película si existe
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 si no existe
        }
    }
}
