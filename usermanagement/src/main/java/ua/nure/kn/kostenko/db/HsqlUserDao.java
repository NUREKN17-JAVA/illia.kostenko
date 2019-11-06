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

    private static final String INSERT_QUERY = "INSERT INTO users (firstname,lastname,dateofbirth) VALUES (?,?,?)";;
    private static final String CALL_IDENTITY =  "call IDENTITY()";;
    ConnectionFactory connectionFactory;
    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public HsqlUserDao(ConnectionFactory connectionFactory) {
        this.connectionFactory=connectionFactory;
    }

    public User create(User entity) throws DatabaseException {
        Connection connection = connectionFactory.createConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
            preparedStatement.setString(1,entity.getFirstName());
            preparedStatement.setString(2,entity.getLastName());
            preparedStatement.setDate(3,new Date(entity.getDateOfBirth().getTime()));
            int numberOfRows = preparedStatement.executeUpdate();
            if(numberOfRows != 1) {
                throw new DatabaseException("Number of inserted rows: "+ numberOfRows);
            }
            CallableStatement callableStatement = connection.prepareCall(CALL_IDENTITY);
            ResultSet keys = callableStatement.executeQuery();
            if(keys.next()) {
                entity.setId(keys.getLong(1));
            }
            keys.close();
            callableStatement.close();
            preparedStatement.close();
            connection.close();
            return entity;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
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