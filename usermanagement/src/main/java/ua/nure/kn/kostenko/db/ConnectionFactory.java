package main.java.ua.nure.kn.kostenko.db;

import java.sql.Connection;

public interface ConnectionFactory {
	Connection createConnection() throws DatabaseException;
	
}
