package ajbc.doodle.calendar.daos;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ajbc.doodle.calendar.entities.User;

public interface UserEventDao {
	
	@Transactional(readOnly = true)
	public default List<User> getUsersOfEventByEventId(Integer eventId) throws DaoException {
		throw new DaoException("Method not implemented");
	}
	
	@Transactional(readOnly = true)
	public default List<User> getUsersWithEventsInRange(LocalDateTime start, LocalDateTime end) throws DaoException {
		throw new DaoException("Method not implemented");
	}
	

}
