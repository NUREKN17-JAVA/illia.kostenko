
package test.java.ua.nure.kn.kostenko.db;

import java.util.Collection;
import java.util.Date;

import main.java.ua.nure.kn.kostenko.db.ConnectionFactory;
import main.java.ua.nure.kn.kostenko.db.ConnectionFactoryImpl;
import main.java.ua.nure.kn.kostenko.db.DatabaseException;
import main.java.ua.nure.kn.kostenko.db.HsqlUserDao;
import main.java.ua.nure.kn.kostenko.domain.User;
import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;



public class HsqldbUserDaoTest extends DatabaseTestCase {

	private static final Date DATE_OF_BIRTH_UPDATE2 = new Date(1999-11-15);
	private static final Date DATE_OF_BIRTH_UPDATE = new Date(2000-04-11);
	private static final String LAST_NAME_UPDATE2 = "Kostenko";
	private static final String FIRST_NAME_UPDATE2 = "Illia";
	private static final String FIRST_NAME2 = "Bill";
	private static final String LAST_NAME2 = "Gates";
	private static final long ID = 1000L;

	private static final String LAST_NAME_UPDATE = "Gates";
	private static final String FIRST_NAME_UPDATE = "Bill";
	private static final String LAST_NAME = "Due";
	private static final String FIRST_NAME = "John";

	private HsqlUserDao dao;


    private ConnectionFactory connectionFactory;

    protected void setUp() throws Exception {
        super.setUp();
        dao = new HsqlUserDao(connectionFactory);
    }

    public void testCreate() throws DatabaseException {
        User user = new User();
        user.setFirstName(FIRST_NAME);
        user.setLastName(LAST_NAME);
        user.setDateOfBirth(new Date());
        assertNull(user.getId());
        User userToChek = dao.create(user);
        assertNotNull(userToChek);
        assertNotNull(userToChek.getId());
        assertEquals(user.getFirstName(),userToChek.getFirstName());
        assertEquals(user.getLastName(),userToChek.getLastName());
        assertEquals(user.getDateOfBirth(),userToChek.getDateOfBirth());
    }
	public void testUpdate() throws DatabaseException{

	}
	public void testDelete() throws DatabaseException{

	}

	public void testFind() throws DatabaseException{

	}

	public void testFindAll() throws DatabaseException {
		Collection<User> users= dao.findAll();
		assertNotNull(users);
		assertEquals("Collection size   matches ",3, users.size());

	}
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@Override
	protected IDatabaseConnection getConnection() throws Exception {
		connectionFactory = new ConnectionFactoryImpl("org.hsqldb.jdbcDriver"
				,"jdbc:hsqldb:file:db/usermanagement"
				,"sa"
				,"");
		return new DatabaseConnection(connectionFactory.createConnection());
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		IDataSet dataSet = new XmlDataSet(getClass().getClassLoader().getResourceAsStream("usersDataSet.xml"));
		return dataSet;
	}



}