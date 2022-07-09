package ajbc.doodle.calendar.daos;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ajbc.doodle.calendar.entities.Event;
import ajbc.doodle.calendar.entities.Notification;
import ajbc.doodle.calendar.entities.User;

@Transactional(rollbackFor = { DaoException.class }, readOnly = true)
public interface NotificationDao {
	
	
//create 
	@Transactional(readOnly = false)
	public default void addNotification(Notification notification) throws DaoException {
		throw new DaoException("Method not implemented");
	}
	
	@Transactional(readOnly = false)
	public default void addNotificationsFromList(List<Notification> notifications) throws DaoException {
		throw new DaoException("Method not implemented");
	}
	
//get
	public default Notification getNotificationById(Integer notificationId) throws DaoException {
		throw new DaoException("Method not implemented");
	}
	
	public default List<Notification> getNotificationsByEventId(Integer eventId) throws DaoException {
		throw new DaoException("Method not implemented");
	}
	
	public default List<Notification> getAllNotifications() throws DaoException {
		throw new DaoException("Method not implemented");
	}
	
//update 
	@Transactional(readOnly = false)
	public default void updateNotification(Notification notification) throws DaoException {
		throw new DaoException("Method not implemented");
	}
	
	@Transactional(readOnly = false)
	public default void updateNotificationsList(List<Notification> notifications) throws DaoException {
		throw new DaoException("Method not implemented");
	}
	
//delete 
	@Transactional(readOnly = false)
	public default void hardDeleteNotification(Integer notificationId) throws DaoException {
		throw new DaoException("Method not implemented");
	}
	
	@Transactional(readOnly = false)
	public default void hardDeleteNotificationsList(List<Integer> notificationsIdList) throws DaoException {
		throw new DaoException("Method not implemented");
	}
	
	@Transactional(readOnly = false)
	public default void softDeleteNotification(Integer notificationId) throws DaoException {
		throw new DaoException("Method not implemented");
	}
	
	@Transactional(readOnly = false)
	public default void softDeleteNotificationsList(List<Integer> notificationsIdList) throws DaoException {
		throw new DaoException("Method not implemented");
	}
	
}
