package ajbc.doodle.calendar.controllers;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ajbc.doodle.calendar.daos.DaoException;
import ajbc.doodle.calendar.entities.Notification;
import ajbc.doodle.calendar.entities.User;
import ajbc.doodle.calendar.notificationManager.NotificationManager;
import ajbc.doodle.calendar.services.MessagePushService;
import ajbc.doodle.calendar.services.NotificationService;
import ajbc.doodle.calendar.services.UserService;


/**
 * Responsible for API requests, regarding notifications.
 * 
 * @author Shelly
 *
 */


@RestController
@RequestMapping("/notifications")
public class NotificationController {
	
	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	MessagePushService messagePushService;
	
	@Autowired
	private NotificationManager notificationManager;
	
//get
	
	/**
	 * 
	 * @return
	 * @throws DaoException
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Notification>> getAllNotifications() throws DaoException {
		List<Notification> notifications = notificationService.getAllNotifications();
		return ResponseEntity.ok(notifications);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/id/{id}")
	public ResponseEntity<?> getNotificationById(@PathVariable Integer id) throws DaoException {
		try {
			Notification notification = notificationService.getNotificationById(id);
			return ResponseEntity.ok(notification);
		} catch (DaoException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param eventId
	 * @return
	 * @throws DaoException
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/eventId/{eventId}")
	public ResponseEntity<List<Notification>> getNotificationsByEventId(@PathVariable Integer eventId) throws DaoException {
		List<Notification> notifications = notificationService.getNotificationsByEventId(eventId);
		return ResponseEntity.ok(notifications);
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/last")
	public Notification getLastNotification() {
		return notificationService.getLastNotification();
	}
	
	
//add
	
	/**
	 * 
	 * @param userId
	 * @param eventId
	 * @param notification
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addNotification(@RequestParam int userId,
			@RequestParam Integer eventId, @RequestBody Notification notification) {
		try {
			notificationService.addNotification(userId, eventId, notification);
			Notification lastNotificationAdded = notificationService.getLastNotification();
			notificationManager.updateAfterAddNotification(lastNotificationAdded);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (DaoException e) {
			return ResponseEntity.status(HttpStatus.valueOf(500)).body(e.getMessage());
		}
	}

//update 
	
	/**
	 * 
	 * @param notification
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT, path = "/{id}")
	public ResponseEntity<?> updateNotification(@RequestBody Notification notification, @PathVariable Integer id) {
		try {
			notificationService.updateNotification(notification, id);
			notification = notificationService.getNotificationById(id);
			notificationManager.updateAfterUpdateNotification(notification);
			return ResponseEntity.status(HttpStatus.OK).body(notification);
		} catch (DaoException e) {
			return ResponseEntity.status(HttpStatus.valueOf(500)).body(e.getMessage());
		}
	}
	
//delete 
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	@RequestMapping(method = RequestMethod.DELETE, path = "/delete/hard/{id}")
	public ResponseEntity<?> hardDeleteNotification(@PathVariable Integer id) throws DaoException {	
		Notification notification = notificationService.getNotificationById(id);
		notificationService.hardDeleteNotification(id);
		notificationManager.deleteAfterUpdateNotification(notification);
		return ResponseEntity.status(HttpStatus.OK).body(notification);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	@RequestMapping(method = RequestMethod.DELETE, path = "/delete/soft/{id}")
	public ResponseEntity<?> SoftDeleteNotification(@PathVariable Integer id) throws DaoException {	
		Notification notification = notificationService.getNotificationById(id);
		notificationService.softDeleteNotification(id);
		notification = notificationService.getNotificationById(id);
		notificationManager.deleteAfterUpdateNotification(notification);
		return ResponseEntity.ok(notification);
	}
	/**
	 * 
	 * @return
	 */
	
	
//push controller methods
	
	/**
	 * 
	 * @return
	 */
	@GetMapping(path = "/publicSigningKey", produces = "application/octet-stream")
	public byte[] publicSigningKey() {
		return messagePushService.getServerKeys().getPublicKeyUncompressed();
	}
	
	/**
	 * 
	 * @return
	 */
	@GetMapping(path = "/publicSigningKeyBase64")
	public String publicSigningKeyBase64() {
		return messagePushService.getServerKeys().getPublicKeyBase64();
	}
	
	/**
	 * 
	 * @throws DaoException
	 */
	@Scheduled(initialDelay = 5_000, fixedDelay = 1000_000)
	public void activateNotificationSending() throws DaoException {
		notificationManager.start(notificationService.getAllNotifications());
	}


}
