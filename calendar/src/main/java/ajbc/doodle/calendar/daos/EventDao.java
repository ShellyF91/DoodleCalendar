package ajbc.doodle.calendar.daos;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ajbc.doodle.calendar.entities.Event;
import ajbc.doodle.calendar.entities.User;

@Transactional(rollbackFor = { DaoException.class }, readOnly = true)
public interface EventDao {
	
//create 
	@Transactional(readOnly = false)
	public default void addEvent(Event event) throws DaoException {
		throw new DaoException("Method not implemented");
	}
	
	@Transactional(readOnly = false)
	public default void addEventsList(List<Event> events) throws DaoException {
		throw new DaoException("Method not implemented");
	}
	
//read 
	public default List<Event> getAllEvents() throws DaoException {
		throw new DaoException("Method not implemented");
	}
	
	public default List<Event> getAllEventsInRange(LocalDateTime start, LocalDateTime end) throws DaoException {
		throw new DaoException("Method not implemented");
	}

	public default List<Event> getAllEventsOfUser(Integer UserId) throws DaoException {
		throw new DaoException("Method not implemented");
	}
	
	public default List<Event> getAllUpcomingEventsOfUser(Integer UserId) throws DaoException {
		throw new DaoException("Method not implemented");
	}

	public default List<Event> getAllEventsOfUserInNextHoursMinutes(Integer UserId, int hours, int minutes) throws DaoException {
		throw new DaoException("Method not implemented");
	}
	
	public default List<Event> getAllEventsOfUserInRange(Integer UserId, LocalDateTime start, LocalDateTime end) throws DaoException {
		throw new DaoException("Method not implemented");
	}

	public default Event getEventById(Integer eventId) throws DaoException {
		throw new DaoException("Method not implemented");
	}

//update 
	@Transactional(readOnly = false)
	public default void updateEvent(Event event) throws DaoException {
		throw new DaoException("Method not implemented");
	}
	
	@Transactional(readOnly = false)
	public default void updateEventsList(List<Event> events) throws DaoException {
		throw new DaoException("Method not implemented");
	}
	
//delete 
	@Transactional(readOnly = false)
	public default void hardDeleteEvent(Integer EventId) throws DaoException {
		throw new DaoException("Method not implemented");
	}
	
	@Transactional(readOnly = false)
	public default void hardDeleteEventsList(List<Integer> eventsIdList) throws DaoException {
		throw new DaoException("Method not implemented");
	}
	
	@Transactional(readOnly = false)
	public default void softDeleteEvent(Integer EventId) throws DaoException {
		throw new DaoException("Method not implemented");
	}
	
	@Transactional(readOnly = false)
	public default void softDeleteEventsList(List<Integer> eventsIdList) throws DaoException {
		throw new DaoException("Method not implemented");
	}


}
