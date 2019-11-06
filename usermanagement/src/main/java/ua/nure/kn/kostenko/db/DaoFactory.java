package main.java.ua.nure.kn.kostenko.db;

import main.java.ua.nure.kn.kostenko.domain.User;


import java.io.IOException;
import java.util.Properties;

public abstract class DaoFactory {
	


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
	

}
