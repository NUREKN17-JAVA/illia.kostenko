package main.java.ua.nure.kn.kostenko.db;

import main.java.ua.nure.kn.kostenko.domain.User;


import java.io.IOException;
import java.util.Properties;

public abstract class DaoFactory {

    private static final String DAO_FACTORY = "dao.Factory";
    protected static final String HSQL_USER_DAO = "dao.HsqlUserDao";

    protected static Properties properties;

    private static DaoFactory instance;

    static {
        properties = new Properties();
        try {
            properties.load(DaoFactory.class.getClassLoader().getResourceAsStream("settings.properties"));
        } catch (IOException e) {
            throw new RuntimeException("incorrect or missing settings");
        }
    }

    protected DaoFactory() {

    }

    public static synchronized DaoFactory getInstance() {
        if(instance==null) {
            Class<?> factoryClass;
            try {
                factoryClass = Class.forName(
                        properties.getProperty(DAO_FACTORY));
                instance = (DaoFactory) factoryClass.newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }

    protected ConnectionFactory getConnectionFactory() {
        return new ConnectionFactoryImpl(properties);
    }
    public abstract Dao<User> getUserDao();
}