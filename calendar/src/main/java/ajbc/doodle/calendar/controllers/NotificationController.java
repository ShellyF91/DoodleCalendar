package ajbc.doodle.calendar.controllers;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Notification>> getAllNotifications() throws DaoException {
		List<Notification> notifications = notificationService.getAllNotifications();
		return ResponseEntity.ok(notifications);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/id/{id}")
	public ResponseEntity<?> getNotificationById(@PathVariable Integer id) throws DaoException {
		try {
			Notification notification = notificationService.getNotificationById(id);
			return ResponseEntity.ok(notification);
		} catch (DaoException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/eventId/{eventId}")
	public ResponseEntity<List<Notification>> getNotificationsByEventId(@PathVariable Integer eventId) throws DaoException {
		List<Notification> notifications = notificationService.getNotificationsByEventId(eventId);
		return ResponseEntity.ok(notifications);
	}
	
	
//add
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createNotification(@RequestParam int userId,
			@RequestParam Integer eventId, @RequestBody Notification notification) {
		try {
			notificationService.addNotification(userId, eventId, notification);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (DaoException e) {
			return ResponseEntity.status(HttpStatus.valueOf(500)).body(e.getMessage());
		}
	}
	
//push controller methods
	@GetMapping(path = "/publicSigningKey", produces = "application/octet-stream")
	public byte[] publicSigningKey() {
		return messagePushService.getServerKeys().getPublicKeyUncompressed();
	}

	@GetMapping(path = "/publicSigningKeyBase64")
	public String publicSigningKeyBase64() {
		return messagePushService.getServerKeys().getPublicKeyBase64();
	}
	
	@PostConstruct
	public void activateNotificationSending() throws DaoException {
		notificationManager.start(notificationService.getAllNotifications());
	}


}
