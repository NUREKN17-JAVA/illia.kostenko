package main.java.ua.nure.kn.kostenko.db;

import main.java.ua.nure.kn.kostenko.domain.User;

public class DaoFactoryImpl extends DaoFactory{

    @Override
    public Dao<User> getDao() {

            try {
                Class daoClass = Class.forName(properties.getProperty(USER_DAO));
                Dao userDao = (Dao) daoClass.newInstance();
                userDao.setConnectionFactory(getConnectionFactory());
                return userDao;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
}
