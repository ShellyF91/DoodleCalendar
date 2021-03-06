package ajbc.doodle.calendar.daos;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ajbc.doodle.calendar.entities.Event;
import ajbc.doodle.calendar.entities.Notification;
import ajbc.doodle.calendar.entities.User;

@SuppressWarnings("unchecked")
@Repository
public class HTNotificationDao implements NotificationDao {
	
	@Autowired
	private HibernateTemplate template;
	
//create 
	@Override
	public void addNotification(Notification notification) throws DaoException {
		template.persist(notification);
	}
		
	@Override
	public void addNotificationsFromList(List<Notification> notifications) throws DaoException {
		for(Notification notification : notifications) {
			addNotification(notification);
		}
	}
		
//get
	@Override
	public Notification getNotificationById(Integer notificationId) throws DaoException {
		Notification notification = template.get(Notification.class, notificationId);
		if (notification == null)
			throw new DaoException("event not found");
		return notification;
	}
	
	@Override
	public List<Notification> getNotificationsByEventId(Integer eventId) throws DaoException {
		DetachedCriteria criteria = DetachedCriteria.forClass(Notification.class);
		Criterion criterion = Restrictions.eq("eventId", eventId);
		criteria.add(criterion);
		List<Notification> notifications = (List<Notification>)template.findByCriteria(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY));
		return notifications;
	}
	
	@Override
	public List<Notification> getAllNotifications() throws DaoException {
		DetachedCriteria criteria = DetachedCriteria.forClass(Notification.class);
		return (List<Notification>)template.findByCriteria(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY));
	}
	
	@Override
	public Notification getLastNotification() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Notification.class);
		Criterion criterion = Restrictions.sqlRestriction("notificationId = (select max(notificationId) from Notifications)");
		criteria.add(criterion);
		List<Notification> list = (List<Notification>)template.findByCriteria(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY));
		return list.get(0);
	}
	
		
//update 
	@Override
	public void updateNotification(Notification notification) throws DaoException {
		template.merge(notification);
	}
		
	@Override
	public void updateNotificationsList(List<Notification> notifications) throws DaoException {
		for(Notification notification : notifications) 
			updateNotification(notification);
	}
	
		
//delete 
	@Override
	public void hardDeleteNotification(Integer notificationId) throws DaoException {
		Notification notification = getNotificationById(notificationId);
		template.delete(notification);
	}
		
	@Override
	public void hardDeleteNotificationsList(List<Integer> notificationsIdList) throws DaoException {
		for(Integer notificationsId : notificationsIdList)
			hardDeleteNotification(notificationsId);
	}
		
	@Override
	public void softDeleteNotification(Integer notificationId) throws DaoException {
		Notification notification = getNotificationById(notificationId);
		notification.setDeleted(true);
		updateNotification(notification);
	}
		
	@Override
	public void softDeleteNotificationsList(List<Integer> notificationsIdList) throws DaoException {
		for(Integer notificationsId : notificationsIdList)
			softDeleteNotification(notificationsId);
	}


	
}
