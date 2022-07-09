package ajbc.doodle.calendar.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ajbc.doodle.calendar.daos.DaoException;
import ajbc.doodle.calendar.entities.Notification;
import ajbc.doodle.calendar.entities.User;
import ajbc.doodle.calendar.services.NotificationService;
import ajbc.doodle.calendar.services.UserService;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
	
	@Autowired
	private NotificationService notificationService;
	
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
	
	

}
