package ajbc.doodle.calendar.services;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import ajbc.doodle.calendar.daos.DaoException;
import ajbc.doodle.calendar.daos.HTEventDao;
import ajbc.doodle.calendar.daos.HTNotificationDao;
import ajbc.doodle.calendar.daos.HTUserDao;
import ajbc.doodle.calendar.entities.Event;
import ajbc.doodle.calendar.entities.Notification;
import ajbc.doodle.calendar.entities.User;

@Service
@Transactional
public class EventService {
	
	@Autowired
	HTEventDao eventDao;
	
	@Autowired
	HTNotificationDao notificationDao;
	
	@Autowired
	HTUserDao userDao;
	
	
//add 	
	public void addEvent(Integer userId, Event event) throws DaoException{
		event.setOwner(userDao.getUserById(userId));
		eventDao.addEvent(event);
//		Event lastEvent = eventDao.getLastEvent();
//		Set<Notification> notifications =  new HashSet<>();
//		notifications.add(new Notification(event, event.getOwner(),event.getStartTime(), "reminder: the event " + event.getTitle() + " is starting now."));
//		lastEvent.setNotifications(notifications);
//		updateEvent(lastEvent);
//		notificationDao.addNotification(new Notification(event, event.getOwner(),event.getStartTime(), "reminder: the event " + event.getTitle() + " is starting now."));
	}
	
	public void addEvent(Event event) throws DaoException{
		eventDao.addEvent(event);
	}
	
	public void addEventsList(List<Event> events) throws DaoException {
		eventDao.addEventsList(events);
	}
	
//get 
	public List<Event> getAllEvents() throws DaoException {
		return eventDao.getAllEvents();
	}
	
	public List<Event> getAllEventsInRange(LocalDateTime start, LocalDateTime end) throws DaoException {
		return eventDao.getAllEventsInRange(start, end);
	}
	
	public Event getEventById(Integer eventId) throws DaoException {
		return eventDao.getEventById(eventId);
	}
	
	public List<Event> getAllEventsOfUser(Integer userId) throws DaoException {
		return eventDao.getAllEventsOfUser(userId);
	}
	
	public List<Event> getAllUpcomingEventsOfUser(Integer userId) throws DaoException {
		return eventDao.getAllUpcomingEventsOfUser(userId);
	}
	
	public List<Event> getAllEventsOfUserInRange(Integer userId, LocalDateTime start, LocalDateTime end) throws DaoException {
		return eventDao.getAllEventsOfUserInRange(userId, start, end);
	}
	
	public List<Event> getAllEventsOfUserInNextHoursMinutes(Integer userId, int hours, int minutes) throws DaoException {
		return eventDao.getAllEventsOfUserInNextHoursMinutes(userId, hours, minutes);
	}
	
//update 	
	public void updateEvent(Event event, Integer eventId, Integer ownerId) throws DaoException {
		event.setEventId(eventId);
		event.setOwnerUserId(ownerId);
		eventDao.updateEvent(event);
	}
	
	public void updateEventsList(List<Event> events) throws DaoException {
		eventDao.updateEventsList(events);
	}

//delete
	public void hardDeleteEvent(Integer eventId) throws DaoException {
		eventDao.hardDeleteEvent(eventId);
	}
	
	public void hardDeleteEventsList(List<Integer> eventsIdList) throws DaoException {
		eventDao.hardDeleteEventsList(eventsIdList);
	}
	
	public void softDeleteEvent(Integer eventId) throws DaoException {
		eventDao.softDeleteEvent(eventId);
	}
	
	public void softDeleteEventsList(List<Integer> eventsIdList) throws DaoException {
		eventDao.softDeleteEventsList(eventsIdList);
	}
	
}

