package ua.nure.kn.kostenko.db;

import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.nure.kn.kostenko.db.Dao;
import ua.nure.kn.kostenko.db.DaoFactory;

class DaoFactoryTest extends TestCase {

	@BeforeEach
	protected void setUp() throws Exception {
		super.setUp();
	}

	@Test
	void testGetUserDao() {
		try {
			DaoFactory daoFactory = DaoFactory.getInstance();
			assertNotNull("DaoFactory instance is null", daoFactory);
			Dao userDao = daoFactory.getUserDao();
			assertNotNull("UserDao instance is null", userDao);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

}