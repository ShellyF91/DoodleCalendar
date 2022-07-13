package ajbc.doodle.calendar.daos;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ajbc.doodle.calendar.entities.Event;
import ajbc.doodle.calendar.entities.User;

@SuppressWarnings("unchecked")
@Repository
public class HTEventDao implements EventDao {
	
	@Autowired
	private HibernateTemplate template;
	
//add 	
	@Override
	public void addEvent(Event event) throws DaoException {
		template.persist(event);
	}
	
	@Override
	public void addEventsList(List<Event> events) throws DaoException {
		for(Event event : events) {
			addEvent(event);
		}
	}
	
//get 
	@Override
	public List<Event> getAllEvents() throws DaoException {
		DetachedCriteria criteria = DetachedCriteria.forClass(Event.class);
		return(List<Event>)template.findByCriteria(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY));
	}
	
	@Override
	public List<Event> getAllEventsInRange(LocalDateTime start, LocalDateTime end) throws DaoException {
		DetachedCriteria criteria = DetachedCriteria.forClass(Event.class);
		Criterion criterion = Restrictions.between("startTime", start, end);
		criteria.add(criterion);
		return (List<Event>)template.findByCriteria(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY));
	}
	
	@Override
	public Event getEventById(Integer eventId) throws DaoException {
		Event event = template.get(Event.class, eventId);
		if (event == null)
			throw new DaoException("event not found");
		return event;
	}
	
	@Override
	public List<Event> getAllEventsOfUser(Integer userId) throws DaoException {
		DetachedCriteria criteria = DetachedCriteria.forClass(Event.class);
		Criterion criterion = Restrictions.eq("userId", userId);
		criteria.add(criterion);
		List<Event> EventsOfUser = (List<Event>)template.findByCriteria(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY));
		return EventsOfUser;
	}
	
	@Override
	public List<Event> getAllUpcomingEventsOfUser(Integer userId) throws DaoException {
		List<Event> EventsOfUser = getAllEventsOfUser(userId);
		List<Event> upcomingEventsOfUser = EventsOfUser.stream()
					.filter(event -> event.getStartTime().isAfter( LocalDateTime.now()))
					.collect(Collectors.toList());
		return upcomingEventsOfUser;
	}
	
	@Override
	public List<Event> getAllEventsOfUserInRange(Integer userId, LocalDateTime start, LocalDateTime end) throws DaoException {
		List<Event> EventsOfUser = getAllEventsOfUser(userId);
		List<Event> EventsOfUserInRange = EventsOfUser.stream()
				.filter(event -> event.getStartTime().isAfter(start) 
							   && event.getStartTime().isBefore(end))
				.collect(Collectors.toList());
		return EventsOfUserInRange;
	}
	
	@Override
	public List<Event> getAllEventsOfUserInNextHoursMinutes(Integer userId, int hours, int minutes) throws DaoException {
		LocalDateTime timeNow = LocalDateTime.now();
		LocalDateTime timeLimit = timeNow.plusHours(hours).plusMinutes(minutes);
		DetachedCriteria criteria = DetachedCriteria.forClass(Event.class);
		Criterion criterion1 = Restrictions.eq("userId", userId);
		criteria.add(criterion1);
		Criterion criterion2 = Restrictions.between("startTime", timeNow, timeLimit);
		criteria.add(criterion2);
		List<Event> EventsOfUserInNextHoursMinutes = (List<Event>)template.findByCriteria(criteria);
		return EventsOfUserInNextHoursMinutes;
	}
	
	
//update 
	
	@Override
	public void updateEvent(Event event) throws DaoException {
		template.merge(event);
	}
	
	@Override
	public void updateEventsList(List<Event> events) throws DaoException {
		for(Event event : events) 
			updateEvent(event);
	}
	
	
	
//delete
	@Override
	public void hardDeleteEvent(Integer eventId) throws DaoException {
		Event event = getEventById(eventId);
		template.delete(event);
	}
	
	@Override
	public void hardDeleteEventsList(List<Integer> eventsIdList) throws DaoException {
		for(Integer eventId : eventsIdList)
			hardDeleteEvent(eventId);
	}
	
	@Override
	public void softDeleteEvent(Integer eventId) throws DaoException {
		Event event = getEventById(eventId);
		event.setDeleted(true);
		updateEvent(event);
	}
	
	@Override
	public void softDeleteEventsList(List<Integer> eventsIdList) throws DaoException {
		for(Integer eventId : eventsIdList)
			softDeleteEvent(eventId);
	}
	
	

}
