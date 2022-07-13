package ajbc.doodle.calendar.daos;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ajbc.doodle.calendar.entities.User;
import ajbc.doodle.calendar.entities.UserEvent;

public interface UserEventDao {
	
	@Transactional(readOnly = true)
	public default List<UserEvent> getUserEventsOfEventByEventId(Integer eventId) throws DaoException {
		throw new DaoException("Method not implemented");
	}
	
	@Transactional(readOnly = true)
	public default List<UserEvent>  getUserEventsWithEventsInRange(LocalDateTime start, LocalDateTime end) throws DaoException {
		throw new DaoException("Method not implemented");
	}
	

}
