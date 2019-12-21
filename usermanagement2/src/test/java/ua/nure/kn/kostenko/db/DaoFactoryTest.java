package ua.nure.kn.kostenko.db;


import junit.framework.TestCase;

public class DaoFactoryTest extends TestCase {

    public void testGetUserDao() {
        try {
			DaoFactory daoFactory = DaoFactory.getInstance();
			assertNotNull("DaoFactory is null",daoFactory);
			Dao userDao = daoFactory.getUserDao();
			assertNotNull("UserDao is null",userDao);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		}
    }
}