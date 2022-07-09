package ajbc.doodle.calendar.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ajbc.doodle.calendar.daos.DaoException;
import ajbc.doodle.calendar.daos.HTEventDao;
import ajbc.doodle.calendar.daos.HTUserDao;
import ajbc.doodle.calendar.daos.UserDao;
import ajbc.doodle.calendar.entities.Event;
import ajbc.doodle.calendar.entities.User;

@Service
@Transactional
public class UserService {
	
	@Autowired
	HTUserDao userDao;
	
	@Autowired
	HTEventDao eventDao;
	
//add	
	public void addUser(User user) throws DaoException{
		userDao.addUser(user);
	}
	
	public void addUsersFromList(List<User> users) throws DaoException {
		userDao.addUsersFromList(users);
	}
	
//get
	public List<User> getAllUsers() throws DaoException {
		return userDao.getAllUsers();
	}
	
//	public List<User> getAllUsersOfEventsInRange(Integer EventId) throws DaoException {
//		throw new DaoException("Method not implemented");
//	}
//	
//	public List<User> getAllUsersOfEvent(Integer EventId) throws DaoException {
//		Event event = eventDao.getEventById(EventId);
//	}
	
	public User getUserById(Integer userId) throws DaoException {
		return userDao.getUserById(userId);
	}
	
	public User getUserByEmail(String email) throws DaoException {
		return userDao.getUserByEmail(email);
	}
	
//update	
	public void updateUser(User user) throws DaoException {
		userDao.updateUser(user);
	}
	
	public void updateUsersList(List<User> users) throws DaoException {
		userDao.updateUsersList(users);
	}
	
//delete	
	public void hardDeleteUser(Integer UserId) throws DaoException {
		userDao.hardDeleteUser(UserId);
	}
	
	public void softDeleteUser(Integer UserId) throws DaoException {
		userDao.softDeleteUser(UserId);
	}
	
}
