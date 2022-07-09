package ajbc.doodle.calendar.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ajbc.doodle.calendar.daos.DaoException;
import ajbc.doodle.calendar.entities.Event;
import ajbc.doodle.calendar.services.EventService;

@RequestMapping("/events")
@RestController
public class EventController {
	
	@Autowired
	private EventService eventService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Event>> getAllEvents() throws DaoException {
		List<Event> events = eventService.getAllEvents();
		return ResponseEntity.ok(events);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/userId/{id}")
	public ResponseEntity<?> getAllEventsOfUser(@PathVariable Integer id) throws DaoException {
		try {
			List<Event> events = eventService.getAllEventsOfUser(id);
			return ResponseEntity.ok(events);
		} catch (DaoException e) {
			return ResponseEntity.status(HttpStatus.valueOf(500)).body(e.getMessage());
		}
	}
	
//	@RequestMapping(method = RequestMethod.GET, path = "/inRange/{startYear, startMonth, startDay, startHour, startMinute, "
//						+ "endYear, endMonth, endDay, endHour, endMinute" + "}")
//	public ResponseEntity<?> getAllEventsInRange(@PathVariable int startYear, @PathVariable int startMonth,  
//			@PathVariable int startDay,@PathVariable int startHour, @PathVariable int startMinute,
//			@PathVariable int endYear, @PathVariable int endMonth,  @PathVariable int endDay
//			,@PathVariable int endHour, @PathVariable int endMinute) throws DaoException {
//		try {
//			LocalDateTime start = LocalDateTime.of(startYear, startMonth, startDay, startHour, startMinute);
//			LocalDateTime end = LocalDateTime.of(endYear, endMonth, endDay, endHour, endMinute);
//			List<Event> events = eventService.getAllEventsInRange(start, end);
//			return ResponseEntity.ok(events);
//		} catch (DaoException e) {
//			return ResponseEntity.status(HttpStatus.valueOf(500)).body(e.getMessage());
//		}
//	}
	
	
	
//	public ResponseEntity<List<Event>> getAllEventsInRange() throws DaoException {
//		
//	}
	
	

}
