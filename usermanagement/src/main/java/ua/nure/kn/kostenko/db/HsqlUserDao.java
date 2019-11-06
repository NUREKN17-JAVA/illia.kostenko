package main.java.ua.nure.kn.kostenko.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;

import main.java.ua.nure.kn.kostenko.domain.User;

public  class HsqlUserDao implements Dao<User> {
    @Override
    public User create(User entity) throws DatabaseException {
        return null;
    }



    @Override
    public void update(User entity) throws DatabaseException {

    }

    @Override
    public void delete(User entity) throws DatabaseException {

    }

    @Override
    public User find(Long id) throws DatabaseException {
        return null;
    }

    @Override
    public Collection<User> findAll() throws DatabaseException {
        return null;
    }



}