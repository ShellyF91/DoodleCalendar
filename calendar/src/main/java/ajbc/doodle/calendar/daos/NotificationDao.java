package ajbc.doodle.calendar.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ajbc.doodle.calendar.entities.Event;
import ajbc.doodle.calendar.entities.Notification;
import ajbc.doodle.calendar.entities.User;

@Transactional(rollbackFor = { DaoException.class }, readOnly = true)
public interface NotificationDao{
	
	
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
	
	public default Notification getLastNotification() throws DaoException {
		throw new DaoException("Method not implemented");
	}
	
//	@Query("select * from Notifications where Notifications.notificationId = (select max(notificationId) from Notifications)")
//	public Notification getLastNotification() throws DaoException;
	
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
