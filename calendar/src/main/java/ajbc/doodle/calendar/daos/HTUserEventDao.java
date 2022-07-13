package ajbc.doodle.calendar.daos;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ajbc.doodle.calendar.entities.User;

public class HTUserEventDao implements UserEventDao {
	
	@Override
	public List<User> getUsersOfEventByEventId(Integer eventId) throws DaoException {
		throw new DaoException("Method not implemented");
	}
	
	@Override
	public List<User> getUsersWithEventsInRange(LocalDateTime start, LocalDateTime end) throws DaoException {
		throw new DaoException("Method not implemented");
	}

}
