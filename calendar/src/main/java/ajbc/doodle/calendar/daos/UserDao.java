package ajbc.doodle.calendar.daos;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ajbc.doodle.calendar.entities.User;

//@Transactional(rollbackFor = {DaoException.class}, readOnly = true)
public interface UserDao {
	
	//create 
	@Transactional(readOnly = false)
	public default void addUser(User user) throws DaoException {
		throw new DaoException("Method not implemented");
	}
	
	@Transactional(readOnly = false)
	public default void addUsersFromList(List<User> users) throws DaoException {
		throw new DaoException("Method not implemented");
	}
	
	//read 
	@Transactional(readOnly = true)
	public default List<User> getAllUsers() throws DaoException {
		throw new DaoException("Method not implemented");
	}
	
	@Transactional(readOnly = true)
	public default User getUserById(Integer userId) throws DaoException {
		throw new DaoException("Method not implemented");
	}
	
	@Transactional(readOnly = true)
	public default User getUserByEmail(String email) throws DaoException {
		throw new DaoException("Method not implemented");
	}
	
	//update 
	@Transactional(readOnly = false)
	public default void updateUser(User user) throws DaoException {
		throw new DaoException("Method not implemented");
	}
	
	@Transactional(readOnly = false)
	public default void updateUsersList(List<User> users) throws DaoException {
		throw new DaoException("Method not implemented");
	}
	
	//delete 
	@Transactional(readOnly = false)
	public default void hardDeleteUser(Integer UserId) throws DaoException {
		throw new DaoException("Method not implemented");
	}
	
	@Transactional(readOnly = false)
	public default void softDeleteUser(Integer UserId) throws DaoException {
		throw new DaoException("Method not implemented");
	}

}

	


