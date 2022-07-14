package ajbc.doodle.calendar.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ajbc.doodle.calendar.daos.DaoException;
import ajbc.doodle.calendar.entities.Event;
import ajbc.doodle.calendar.entities.Notification;
import ajbc.doodle.calendar.services.EventService;

/**
 * Responsible for API requests, regarding events.
 * 
 * @author Shelly
 *
 */

@RequestMapping("/events")
@RestController
public class EventController {
	
	@Autowired
	private EventService eventService;
	
//get	
	
	/**
	 * This method returns all the events that exist in the DB.
	 * 
	 * @return
	 * @throws DaoException
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Event>> getAllEvents() throws DaoException {
		List<Event> events = eventService.getAllEvents();
		return ResponseEntity.ok(events);
	}
	
	/**
	 * This method receives an id of an event and returns the id's event from the DB.  
	 * 
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/id/{id}")
	public ResponseEntity<?> getEventById(@PathVariable Integer id) throws DaoException {
		try {
			Event event = eventService.getEventById(id);
			return ResponseEntity.ok(event);
		} catch (DaoException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	/**
	 * This method returns all the events in a given time range.
	 * 
	 * @param start
	 * @param end
	 * @return
	 * @throws DaoException
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/range")
	public ResponseEntity<List<Event>> getAllEventsInRange(@RequestParam String start, @RequestParam String end) throws DaoException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime startTime = LocalDateTime.parse(start, formatter);
		LocalDateTime endTime = LocalDateTime.parse(end, formatter);
		List<Event> events = eventService.getAllEventsInRange(startTime, endTime);
		return ResponseEntity.ok(events);
	}
	
	/**
	 * This method returns all the events of a user, given by his id. 
	 * 
	 * @param userId
	 * @return
	 * @throws DaoException
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/of-user/{userId}")
	public ResponseEntity<List<Event>> getAllEventOfUser(@PathVariable Integer userId) throws DaoException {
		List<Event> events = eventService.getAllEventsOfUser(userId);
		return ResponseEntity.ok(events);
	}
	
	/**
	 * This method returns all the future events of a user, given by his id.
	 * 
	 * @param userId
	 * @return
	 * @throws DaoException
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/upcoming-of-user/{userId}")
	public ResponseEntity<List<Event>> getAllUpcomingEventOfUser(@PathVariable Integer userId) throws DaoException {
		List<Event> events = eventService.getAllUpcomingEventsOfUser(userId);
		return ResponseEntity.ok(events);
	}
	
	/**
	 * This method returns all the events of a user in a given time range
	 * 
	 * @param userId
	 * @param start
	 * @param end
	 * @return
	 * @throws DaoException
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/in-range-of-user/{userId}")
	public ResponseEntity<List<Event>> getAllEventsOfUserInRange(@PathVariable Integer userId, 
							@RequestParam String start, @RequestParam String end) throws DaoException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime startTime = LocalDateTime.parse(start, formatter);
		LocalDateTime endTime = LocalDateTime.parse(end, formatter);
		List<Event> events = eventService.getAllEventsOfUserInRange(userId, startTime, endTime);
		return ResponseEntity.ok(events);
	}
	
	/**
	 * This method returns all the future events of a user which are scheduled before a given time, given by hours and minutes from the 
	 * time in which the function was called.
	 * 
	 * 
	 * @param userId
	 * @param hours
	 * @param minutes
	 * @return
	 * @throws DaoException
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/next-hours-minutes-of-user/{userId}/{hours}/{minutes}")
	public ResponseEntity<List<Event>> getAllEventsOfUserInNextHoursMinutes(@PathVariable Integer userId, 
			@PathVariable int hours, @PathVariable int minutes) throws DaoException {
		List<Event> events = eventService.getAllEventsOfUserInNextHoursMinutes(userId, hours, minutes);
		return ResponseEntity.ok(events);
	}


//add
	
	/**
	 * Creates a new user and adds it to the DB
	 * 
	 * @param userId
	 * @param event
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addEvent(@RequestParam int userId, @RequestBody Event event) {
		try {
			eventService.addEvent(userId, event);
			event = eventService.getEventById(event.getEventId());
			return ResponseEntity.status(HttpStatus.CREATED).body(event);
		} catch (DaoException e) {
			return ResponseEntity.status(HttpStatus.valueOf(500)).body(e.getMessage());
		}
	}
	
//update
	
	/**
	 * update the data of an existing user 
	 * 
	 * @param event
	 * @param eventId
	 * @param ownerId
	 * @return
	 * @throws DaoException
	 */
	public ResponseEntity<?> updateEvent(@RequestBody Event event, @PathVariable Integer eventId,
			@PathVariable Integer ownerId) throws DaoException{
		eventService.updateEvent(event, eventId, ownerId);
		event = eventService.getEventById(eventId);
		return ResponseEntity.status(HttpStatus.OK).body(event);
	}
	
	
//delete
	
	/**
	 * deletes a user from the DB
	 * 
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	@RequestMapping(method = RequestMethod.DELETE, path = "/delete/hard/{id}")
	public ResponseEntity<?> hardDeleteEvent(@PathVariable Integer id) throws DaoException {	
		Event event = eventService.getEventById(id);
		eventService.hardDeleteEvent(id);
		return ResponseEntity.status(HttpStatus.OK).body(event);
	}

	/**
	 * 
	 * mark a user as deleted in the DB 
	 * 
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	@RequestMapping(method = RequestMethod.DELETE, path = "/delete/soft/{id}")
	public ResponseEntity<?> SoftDeleteEvent(@PathVariable Integer id) throws DaoException {	
		Event event = eventService.getEventById(id);
		eventService.softDeleteEvent(id);
		event = eventService.getEventById(id);
		return ResponseEntity.ok(event);
	}
}
