package ajbc.doodle.calendar.services;

import java.util.List;

import org.aspectj.apache.bcel.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ajbc.doodle.calendar.daos.DaoException;
import ajbc.doodle.calendar.daos.EventDao;
import ajbc.doodle.calendar.daos.HTEventDao;
import ajbc.doodle.calendar.daos.HTNotificationDao;
import ajbc.doodle.calendar.daos.HTUserDao;
import ajbc.doodle.calendar.daos.NotificationDao;
import ajbc.doodle.calendar.daos.UserDao;
import ajbc.doodle.calendar.entities.Notification;

@Service
@Transactional
public class NotificationService {
	
	@Autowired
	HTNotificationDao notificationDao;
	
	@Autowired
	HTUserDao userDao;
	
	@Autowired
	HTEventDao eventsDao;
	
//add
	public void addNotification(Integer userId, Integer eventId, Notification notification) throws DaoException {
		notification.setEvent(eventsDao.getEventById(eventId));
		notification.setUser(userDao.getUserById(userId));
		notificationDao.addNotification(notification);
	}
	
	public void addNotificationsFromList(List<Notification> notifications) throws DaoException {
		notificationDao.addNotificationsFromList(notifications);
	}
	
//get
	public Notification getNotificationById(Integer notificationId) throws DaoException {
		return notificationDao.getNotificationById(notificationId);
	}
	
	public List<Notification> getNotificationsByEventId(Integer eventId) throws DaoException {
		return notificationDao.getNotificationsByEventId(eventId);
	}
	
	public List<Notification> getAllNotifications() throws DaoException {
		return notificationDao.getAllNotifications();
	}
	
	public Notification getLastNotification() {
		return notificationDao.getLastNotification();
	}
	
//update
	public void updateNotification(Notification notification, Integer id) throws DaoException {
		notification.setNotificationId(id);
		Notification notificationFromDb = getNotificationById(id);
		notification.setEvent(notificationFromDb.getEvent());
		notification.setUser(notificationFromDb.getUser());
		notificationDao.updateNotification(notification);
	}
	
	public void updateNotificationsList(List<Notification> notifications) throws DaoException {
		notificationDao.updateNotificationsList(notifications);
	}

//delete
	public void hardDeleteNotification(Integer notificationId) throws DaoException {
		notificationDao.hardDeleteNotification(notificationId);
	}
	
	public void hardDeleteNotificationsList(List<Integer> notificationsIdList) throws DaoException {
		notificationDao.hardDeleteNotificationsList(notificationsIdList);
	}
	
	public void softDeleteNotification(Integer notificationId) throws DaoException {
		notificationDao.softDeleteNotification(notificationId);
	}
	
	public void softDeleteNotificationsList(List<Integer> notificationsIdList) throws DaoException {
		notificationDao.softDeleteNotificationsList(notificationsIdList);
	}

}
