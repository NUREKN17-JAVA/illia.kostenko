package ua.nure.kn.kostenko.db;

import ua.nure.kn.kostenko.domain.User;

public class DaoFactoryImpl extends DaoFactory {

    @Override
    public Dao getUserDao() {
        Dao result = null;
        Class clazz;
        try {
            clazz = Class.forName(properties.getProperty(USER_DAO));
            result = (Dao) clazz.newInstance();
            result.setConnectionFactory(getConnectionFactory());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}

