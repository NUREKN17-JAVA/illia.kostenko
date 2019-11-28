package ua.nure.kn.kostenko.gui;

import com.mockobjects.dynamic.Mock;
import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.TestHelper;
import junit.extensions.jfcunit.eventdata.MouseEventData;
import junit.extensions.jfcunit.eventdata.StringEventData;
import junit.extensions.jfcunit.finder.NamedComponentFinder;
import ua.nure.kn.kostenko.db.DaoFactory;
import ua.nure.kn.kostenko.db.DaoFactoryImpl;
import ua.nure.kn.kostenko.db.MockDaoFactory;
import ua.nure.kn.kostenko.db.MockUserDao;
import ua.nure.kn.kostenko.gui.AddPanel;
import ua.nure.kn.kostenko.gui.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

public class MainFrameTest extends JFCTestCase {
    public static final String BROWSE_PANEL_COMPONENT_NAME = "browsePanel";
    public static final String ADD_BUTTON_COMPONENT_NAME = "addButton";
    public static final String DELETE_BUTTON_COMPONENT_NAME = "deleteButton";
    public static final String DETAILS_BUTTON_COMPONENT_NAME = "detailsButton";
    public static final String EDIT_BUTTON_COMPONENT_NAME = "editButton";
    public static final String ADD_PANEL_COMPONENT_NAME = "addPanel";
    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Doe";
    private static final Date DATE_OF_BIRTH = new Date();
    public static final String FIRST_NAME_FIELD_COMPONENT_NAME = "firstNameField";
    private static final String LAST_NAME_FIELD_COMPONENT_NAME = "lastNameField";
    private static final String DATE_OF_BIRTH_FIELD_COMPONENT_NAME = "dateOfBirthField";
    private static final String OK_BUTTON_COMPONENT_NAME = "okButton";
    private static final String CANCEL_BUTTON_COMPONENT_NAME = "cancelButton";
    public static final String USER_TABLE_COMPONENT_NAME = "userTable";

    private MainFrame mainFrame;
    private Mock mockUserDao;

    protected void setUp() throws Exception {
        super.setUp();

        Properties properties = new Properties();
        properties.setProperty("dao.Factory", MockDaoFactory.class.getName());
        DaoFactory.init(properties);
        mockUserDao = ((MockDaoFactory) DaoFactory.getInstance()).getMockUserDao();

        mockUserDao.expectAndReturn("findAll", new ArrayList());
        setHelper(new JFCTestHelper());
        mainFrame = new MainFrame();
        mainFrame.setVisible(true);
        }

    @Override
    public void tearDown() throws Exception {
        mainFrame.setVisible(false);
        getHelper();
        TestHelper.cleanUp(this);
        super.tearDown();
    }

    //komponent ne naiden/naiden
    private Component find(Class<?> componentClass, String name){
        NamedComponentFinder finder;
        finder = new NamedComponentFinder(componentClass,name);
        finder.setWait(0);
        Component component = finder.find(mainFrame, 0);// couldnot fdind system dialogues
        assertNotNull("Could not find component ' " + name + " ' ",component);
        return component;
    }

    public void testBrowseControls() {
        find(JPanel.class, BROWSE_PANEL_COMPONENT_NAME);
        JTable table  = (JTable) find(JTable.class, USER_TABLE_COMPONENT_NAME);
        assertEquals(3, table.getColumnCount());
        assertEquals("ID", table.getColumnName(0));
        assertEquals("Имя", table.getColumnName(1));
        assertEquals("Фамилия", table.getColumnName(2));

        //find user table
        find(JButton.class, ADD_BUTTON_COMPONENT_NAME);
        find(JButton.class, EDIT_BUTTON_COMPONENT_NAME);
        find(JButton.class, DETAILS_BUTTON_COMPONENT_NAME);
       find(JButton.class, DELETE_BUTTON_COMPONENT_NAME);
    }

    public void testAddUser() {
        JTable table = (JTable) find(JTable.class, USER_TABLE_COMPONENT_NAME );
        assertEquals(0, table.getRowCount());
        JButton addButton = (JButton) find(JButton.class, ADD_BUTTON_COMPONENT_NAME);
        getHelper().enterClickAndLeave(new MouseEventData(this, addButton));

        find(JPanel.class, ADD_PANEL_COMPONENT_NAME);

        JTextField firstNameField = (JTextField) find(JTextField.class, FIRST_NAME_FIELD_COMPONENT_NAME);
        JTextField lastNameField = (JTextField) find(JTextField.class, LAST_NAME_FIELD_COMPONENT_NAME);
        JTextField dateOfBirthField = (JTextField) find(JTextField.class, DATE_OF_BIRTH_FIELD_COMPONENT_NAME);
        JButton okButton = (JButton) find(JButton.class, OK_BUTTON_COMPONENT_NAME);
        JButton cancelButton = (JButton) find(JButton.class, CANCEL_BUTTON_COMPONENT_NAME);

        DateFormat formatter = DateFormat.getDateInstance();
        String date = formatter.format(new Date());
        getHelper().sendString(new StringEventData(this, firstNameField, "John"));
        getHelper().sendString(new StringEventData(this, lastNameField, "Doe"));
        getHelper().sendString(new StringEventData(this, dateOfBirthField, date));
        getHelper().enterClickAndLeave(new MouseEventData(this, okButton));

        find(JPanel.class, BROWSE_PANEL_COMPONENT_NAME);
        table = (JTable) find(JTable.class, USER_TABLE_COMPONENT_NAME );
        assertEquals(1, table.getRowCount());
    }

    private void fillFields(String firstName, String lastName, Date dateOfBirth) {
        JTextField firstNameField = (JTextField) find(JTextField.class, FIRST_NAME_FIELD_COMPONENT_NAME);
        JTextField lastNameField = (JTextField) find(JTextField.class, LAST_NAME_FIELD_COMPONENT_NAME);
        JTextField dateOfBirthField = (JTextField) find(JTextField.class, DATE_OF_BIRTH_FIELD_COMPONENT_NAME);
        getHelper().sendString(new StringEventData(this, firstNameField, firstName));
        getHelper().sendString(new StringEventData(this, lastNameField, lastName));
        DateFormat formatter = DateFormat.getDateInstance();
        String date = formatter.format(dateOfBirth);
        getHelper().sendString(new StringEventData(this, dateOfBirthField, date));

    }

}
