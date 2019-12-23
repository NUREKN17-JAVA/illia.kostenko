package ua.nure.kn.kostenko.db;

import ua.nure.kn.kostenko.domain.User;

import java.util.Collection;

public interface Dao <T> {
    T create(T entity) throws DatabaseException;

    void update(T entity) throws DatabaseException;

    void delete(T entity) throws DatabaseException;

    T find(Long id) throws DatabaseException;

    Collection<T> findAll() throws DatabaseException;

    Collection<User> find(String firstName, String lastName) throws DatabaseException;


    void setConnectionFactory(ConnectionFactory connectionFactory) throws DatabaseException;
}
