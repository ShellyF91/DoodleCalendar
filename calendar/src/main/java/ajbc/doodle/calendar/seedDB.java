package ajbc.doodle.calendar;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import ajbc.doodle.calendar.daos.DaoException;
import ajbc.doodle.calendar.daos.EventDao;
import ajbc.doodle.calendar.daos.NotificationDao;
import ajbc.doodle.calendar.daos.UserDao;
import ajbc.doodle.calendar.entities.Event;
import ajbc.doodle.calendar.entities.Event.Repeating;
import ajbc.doodle.calendar.entities.Notification;
import ajbc.doodle.calendar.entities.User;
import ajbc.doodle.calendar.services.EventService;
import ajbc.doodle.calendar.services.NotificationService;
import ajbc.doodle.calendar.services.UserService;

@Component
public class seedDB {
	
	
	@Autowired
	private UserService userService;
	@Autowired
	private EventService eventService;
	@Autowired
	private NotificationService notificationService;

	
	
	@EventListener
	public void seedDb(ContextRefreshedEvent event) throws DaoException{
		seedUsers();
		seedEvents();
		seedNotifications();
	}
	


	public void seedUsers() throws DaoException{
		List<User> users = userService.getAllUsers();
		if (users.size() == 0
				|| users == null ) {
			userService.addUser(new User("Shelly", "Foran", "foranshelly@gmail.com",
					LocalDateTime.of(1991, 11, 04, 0, 0),
					LocalDateTime.now()));
			userService.addUser(new User("Karin", "Foran", "karinforan@gmail.com",
					LocalDateTime.of(1987, 05, 26, 0, 0),
					LocalDateTime.now()));
			userService.addUser(new User("Emma", "Blum", "emma@gmail.com",
					LocalDateTime.of(2020, 04, 14, 0, 0),
					LocalDateTime.now()));
		}
	}
	
	public void seedEvents() throws DaoException{
		User user1 = userService.getUserById(9);
		User user2 = userService.getUserById(10);
		User user3 = userService.getUserById(11);
		Set<User> participants = new HashSet<>();
		participants.add(user1);
		participants.add(user2);
		participants.add(user3);

		List<Event> events = eventService.getAllEvents();
		if (events.size() == 0
				|| events == null ) {
			eventService.addEvent(new Event(9, user1, "Dinner", false, 
					LocalDateTime.of(2022, 07, 15, 20, 00), LocalDateTime.of(2022, 07, 15, 22, 00),
					"Karin's house", "italian dinner", 3, Repeating.NONE, participants));
			eventService.addEvent(new Event(10, user2, "Birthday", true, 
					LocalDateTime.of(2022, 11, 04, 00, 00), LocalDateTime.of(2022, 11, 04, 00, 00),
					"Shelly's house", "A party for Shelly's birthday", 3, Repeating.NONE, participants));
			eventService.addEvent(new Event(11, user3, "Movie", false, 
					LocalDateTime.of(2022, 07, 20, 16, 00),LocalDateTime.of(2022, 07, 20, 18, 00),
					"The movie theater", "The new movie", 3, Repeating.NONE, participants));
		}

	}
	
	private void seedNotifications() throws DaoException{
		User user1 = userService.getUserById(9);
		User user2 = userService.getUserById(10);
		User user3 = userService.getUserById(11);

		Event event1 = eventService.getEventById(4);
		Event event2 = eventService.getEventById(5);
		Event event3 = eventService.getEventById(6);
		
		List<Notification> notifications = notificationService.getAllNotifications();
		if (notifications.size() == 0
				|| notifications == null) {
			Notification n1 = new Notification(event1, user1, LocalDateTime.of(2022, 07, 9, 22, 00), "reminder");
			Notification n2 = new Notification(event2, user2, LocalDateTime.of(2022, 07, 9, 22, 00), "reminder");
			Notification n3 = new Notification(event3, user3, LocalDateTime.of(2022, 07, 9, 22, 00), "reminder");
			notificationService.addNotification(n1);
			notificationService.addNotification(n2);
			notificationService.addNotification(n3);
		}
	}

	

}
