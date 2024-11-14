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

import javax.validation.constraints.*;
import javax.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

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
}
