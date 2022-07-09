package ajbc.doodle.calendar.daos;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ajbc.doodle.calendar.entities.User;

@SuppressWarnings("unchecked")
@Repository
public class HTUserDao implements UserDao {
	
	@Autowired
	private HibernateTemplate template;
	

//add
	@Override
	public void addUser(User user) throws DaoException {
		template.persist(user);
	}
	
	@Override
	public void addUsersFromList(List<User> users) throws DaoException {
		for(User user : users) {
			addUser(user);
		}
	}
	
//get
	@Override
	public List<User> getAllUsers() throws DaoException {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		return (List<User>)template.findByCriteria(criteria);
	}
	
	@Override
	public User getUserById(Integer userId) throws DaoException {
		User user = template.get(User.class, userId);
		if (user == null)
			throw new DaoException("No such user in DB");
		return user;
	}
	
	@Override
	public User getUserByEmail(String email) throws DaoException {
		User user = template.get(User.class, email);
		if (user == null)
			throw new DaoException("No such user in DB");
		return user;
	}
	
//update
	@Override
	public void updateUser(User user) throws DaoException {
		template.merge(user);
	}
	
	@Override
	public void updateUsersList(List<User> users) throws DaoException {
		for(User user : users) {
			updateUser(user);
		}
	}
	
//delete 
	@Override
	public void hardDeleteUser(Integer UserId) throws DaoException {
		User user = getUserById(UserId);
		template.delete(user);
	}
	
	
	@Override
	public void softDeleteUser(Integer UserId) throws DaoException {
		User user = getUserById(UserId);
		user.setDeleted(true);
		updateUser(user);
	}

	
	
	
	
	
	


}
