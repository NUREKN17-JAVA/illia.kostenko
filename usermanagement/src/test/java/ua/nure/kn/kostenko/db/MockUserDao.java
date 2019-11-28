package ua.nure.kn.kostenko.db;



import ua.nure.kn.kostenko.domain.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MockUserDao implements Dao {

	private long id = 0;
	private Map users = new HashMap();

	@Override
	public User create(Object user) throws DatabaseException {
		Long currentId = new Long(++id);
		User my_user = (User)user;
		my_user.setId(currentId);
		users.put(currentId, my_user);
		return (User) my_user;
	}

	@Override
	public void update(Object user) throws DatabaseException {
		User my_user = (User)user;
		Long currentId = my_user.getId();
		users.remove(currentId);
		users.put(currentId, my_user);
	}

	@Override
	public void delete(Object user) throws DatabaseException {
		User my_user = (User)user;
		Long currentId = my_user.getId();
		users.remove(currentId);
	}

	@Override
	public User find(Long id) throws DatabaseException {
		return (User) users.get(id);
	}

	@Override
	public Collection findAll() throws DatabaseException {
		return users.values();
	}


	@Override
	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		// TODO Auto-generated method stub

	}

}