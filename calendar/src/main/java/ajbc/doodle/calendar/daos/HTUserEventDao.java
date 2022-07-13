package ajbc.doodle.calendar.daos;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ajbc.doodle.calendar.entities.Notification;
import ajbc.doodle.calendar.entities.User;
import ajbc.doodle.calendar.entities.UserEvent;

@SuppressWarnings("unchecked")
@Repository
public class HTUserEventDao implements UserEventDao {
	
	@Autowired
	private HibernateTemplate template;
	
	@Override
	public List<UserEvent> getUserEventsOfEventByEventId(Integer eventId) throws DaoException {
		DetachedCriteria criteria = DetachedCriteria.forClass(UserEvent.class);
		String restriction = "eventId = " + eventId;
		Criterion criterion = Restrictions.sqlRestriction(restriction);
		criteria.add(criterion);
		List<UserEvent> list = (List<UserEvent>)template.findByCriteria(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY));
		return list; 
	}
	
	@Override
	public List<UserEvent> getUserEventsWithEventsInRange(LocalDateTime start, LocalDateTime end) throws DaoException {
		throw new DaoException("Method not implemented");
	}

}
