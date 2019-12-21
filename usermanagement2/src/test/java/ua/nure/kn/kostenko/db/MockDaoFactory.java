package ua.nure.kn.kostenko.db;

import com.mockobjects.dynamic.Mock;
import ua.nure.kn.kostenko.domain.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MockDaoFactory extends DaoFactory {
    private Mock mockUserDao;

    public MockDaoFactory() {
        mockUserDao = new Mock(Dao.class);
    }

    public Mock getMockUserDao() {
        return mockUserDao;
    }
    @SuppressWarnings("unchecked")
    @Override
    public Dao<User> getUserDao() {
        // TODO Auto-generated method stub
        return (Dao<User>) mockUserDao.proxy();
    }

}
