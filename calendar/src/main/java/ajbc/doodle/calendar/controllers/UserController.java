package ajbc.doodle.calendar.controllers;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ajbc.doodle.calendar.Application;
import ajbc.doodle.calendar.daos.DaoException;
import ajbc.doodle.calendar.entities.User;
import ajbc.doodle.calendar.entities.webpush.PushMessage;
import ajbc.doodle.calendar.entities.webpush.Subscription;
import ajbc.doodle.calendar.entities.webpush.SubscriptionEndpoint;
import ajbc.doodle.calendar.services.CryptoService;
import ajbc.doodle.calendar.services.MessagePushService;
import ajbc.doodle.calendar.services.UserService;

/**
 * Responsible for API requests, regarding users.
 * 
 * 
 * @author Shelly
 *
 */

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;

//add
	
	/** This method adds a new user to the DB, by receiving a user object. 
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addUser(@RequestBody User user) {
		try {
			userService.addUser(user);
			user = userService.getUserById(user.getUserId());
			return ResponseEntity.status(HttpStatus.CREATED).body(user);
		} catch (DaoException e) {
			return ResponseEntity.status(HttpStatus.valueOf(500)).body(e.getMessage());
		}
	}
	
//get
	
	/**This method returns all the users that exist in the DB.  
	 * 
	 * @return
	 * @throws DaoException
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUsers() throws DaoException {
		List<User> users = userService.getAllUsers();
		if (users == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.ok(users);
	}
	
	/**
	 * This method receives an id of a user and returns the user from the DB who has this id. 
	 * 
	 * @param id 
	 * @return the user with this id
	 * @throws DaoException
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/id/{id}")
	public ResponseEntity<?> getUserById(@PathVariable Integer id) throws DaoException {
		try {
			User user = userService.getUserById(id);
			return ResponseEntity.ok(user);
		} catch (DaoException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	/**
	 * This method receives an email address of a user and returns the user from the DB who has this email address. 
	 * 
	 * @param email
	 * @return the user with this email 
	 * @throws DaoException
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/email/{email}")
	public ResponseEntity<?> getUserByEmail(@PathVariable String email) throws DaoException {
		try {
			User user = userService.getUserByEmail(email);
			return ResponseEntity.ok(user);
		} catch (DaoException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
//update
	
	/**
	 * This method takes an updated user object and update the user in the DB, by his id, given as parameter.  
	 * 
	 * @param user - updated user 
	 * @param id - the user'd ID from the DB 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT, path = "/{id}")
	public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable Integer id) {
		try {
			user.setUserId(id);
			userService.updateUser(user);
			user = userService.getUserById(id);
			return ResponseEntity.status(HttpStatus.OK).body(user);
		} catch (DaoException e) {
			return ResponseEntity.status(HttpStatus.valueOf(500)).body(e.getMessage());
		}
	}
	
//delete
	
	/**
	 * This method deletes a user, given by his id, from the DB
	 * 
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	@RequestMapping(method = RequestMethod.DELETE, path = "/delete/hard/{id}")
	public ResponseEntity<?> hardDeleteUser(@PathVariable Integer id) throws DaoException {	
		User user = userService.getUserById(id);
		userService.hardDeleteUser(id);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
	/**
	 * This method marks a user, given by his id,  as deleted. 
	 * 
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	@RequestMapping(method = RequestMethod.DELETE, path = "/delete/soft/{id}")
	public ResponseEntity<?> SoftDeleteUser(@PathVariable Integer id) throws DaoException {	
		User user = userService.getUserById(id);
		userService.softDeleteUser(id);
		return ResponseEntity.ok(user);
	}

	
//login
	
	/**
	 * This method is updating the user information in the DB, by marking it as logged in.  
	 * 
	 * @param subscription
	 * @param email - user's input, used to update his data in the DB.
	 * @return
	 * @throws DaoException
	 * @throws InvalidKeyException
	 * @throws JsonProcessingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws InvalidAlgorithmParameterException
	 * @throws NoSuchPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/login/{email}")
	public ResponseEntity<?> login(@RequestBody Subscription subscription, @PathVariable(required = false) String email) throws DaoException, InvalidKeyException, JsonProcessingException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		try {
			User user = userService.getUserByEmail(email);
			userService.login(user, subscription);
			return ResponseEntity.ok().body("Logged in");
		} catch (DaoException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	/**
	 * This method is updating the user information in the DB, by marking it as logged out.  
	 * 
	 * @param email - user's input, used to update his data in the DB. 
	 * @return  
	 * @throws DaoException
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/logout/{email}")
	public ResponseEntity<?> logout(@PathVariable(required = false) String email) throws DaoException {
		try {
			User user = userService.getUserByEmail(email);
			userService.logout(user);
			return ResponseEntity.ok().body("Logged out");
		} catch (DaoException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	/** 
	 * This method checks if a user exist in the DB.
	 * 
	 * @param subscription - contains the information of a user's connection, which is described as endpoint. 
	 * @return true if the user exists in the DB, false if it doesn't. 
	 * @throws DaoException
	 */
	@PostMapping("/isSubscribed")
	public boolean isSubscribed(@RequestBody SubscriptionEndpoint subscription) throws DaoException {
		List<User> users = userService.getAllUsers();
		for(User user : users) {
			if(user.getEndPoint() != null) {
				if(user.getEndPoint().equals(subscription.getEndpoint()))
					return true;
			}
		}
		return false;
	}

}
