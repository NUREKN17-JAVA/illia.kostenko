package ua.nure.kn.kostenko.domain;

import junit.framework.TestCase;
import main.java.ua.nure.kn.kostenko.domain.User;

import java.util.Calendar;
import java.util.Date;

public class UserTest extends TestCase {
    /** Date of birth*/
    public static final int YEAR_OF_BIRTH = 2000;
    public static final int CURRENT_YEAR = 2019;

    /** Test case #1 the birth day in the next month of the year*/
    public static final int MONTH_OF_BIRTH1 = Calendar.DECEMBER;
    public static final int DATE_OF_BIRTH1 = 3;
    public static final int ETALONE_AGE1 = CURRENT_YEAR - YEAR_OF_BIRTH - 1;

    /** Test case #2 the birth day is today*/ //TODO Change data
    public static final int MONTH_OF_BIRTH2 = Calendar.SEPTEMBER;
    public static final int DATE_OF_BIRTH2 = 28;
    public static final int ETALONE_AGE2 = CURRENT_YEAR - YEAR_OF_BIRTH ;

    /** Test case #3 the birth day is this month (later than today)*/
    public static final int MONTH_OF_BIRTH3 = Calendar.DECEMBER;
    public static final int DATE_OF_BIRTH3 = 31;
    public static final int ETALONE_AGE3 = CURRENT_YEAR - YEAR_OF_BIRTH -1 ;

    /** Test case #4 the birth day is this month (earlier than today)*/
    public static final int MONTH_OF_BIRTH4 = Calendar.SEPTEMBER;
    public static final int DATE_OF_BIRTH4 = 26;
    public static final int ETALONE_AGE4 = CURRENT_YEAR - YEAR_OF_BIRTH;
//границы годов
    // высокостныц
    /** Test case #5 the birth day in month ago*/
    public static final int MONTH_OF_BIRTH5 = Calendar.AUGUST;
    public static final int DATE_OF_BIRTH5 = 30;
    public static final int ETALONE_AGE5 = CURRENT_YEAR - YEAR_OF_BIRTH;

    private User user;
    private Date dateOfBirth;

    public void testGetFullName() {
        user.setFirstName("John");
        user.setLastName("Doe");
        assertEquals("John Doe", user.getFullName());
    }

    /** Test case #1*/
     public void testGetAge1() { //день рождения прошёл но месяц идет
        Calendar calendar = Calendar.getInstance();//Singletone
        calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH1, DATE_OF_BIRTH1);
        dateOfBirth = calendar.getTime();
        user.setDateOfBirth(dateOfBirth);
        assertEquals(ETALONE_AGE1, user.getAge());
    }

    /** Test case #2*/
    public void testGetAge2() { //день рождения в тот же день
        Calendar calendar = Calendar.getInstance();//Singletone
        calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH2, DATE_OF_BIRTH2);
        dateOfBirth = calendar.getTime();
        user.setDateOfBirth(dateOfBirth);
        assertEquals(ETALONE_AGE2, user.getAge());
    }

    /** Test case #3*/
    public void testGetAge3() { //день рождения в тот же день
        Calendar calendar = Calendar.getInstance();//Singletone
        calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH3, DATE_OF_BIRTH3);
        dateOfBirth = calendar.getTime();
        user.setDateOfBirth(dateOfBirth);
        assertEquals(ETALONE_AGE3, user.getAge());
    }

    /** Test case #4*/
    public void testGetAge4() { //день рождения в тот же день
        Calendar calendar = Calendar.getInstance();//Singletone
        calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH4, DATE_OF_BIRTH4);
        dateOfBirth = calendar.getTime();
        user.setDateOfBirth(dateOfBirth);
        assertEquals(ETALONE_AGE4, user.getAge());
    }

    /** Test case #5*/
    public void testGetAge5() { //день рождения в тот же день
        Calendar calendar = Calendar.getInstance();//Singletone
        calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH5, DATE_OF_BIRTH5);
        dateOfBirth = calendar.getTime();
        user.setDateOfBirth(dateOfBirth);
        assertEquals(ETALONE_AGE5, user.getAge());
    }

    protected void setUp() throws Exception {
        super.setUp();
        user = new User();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        user = new User();
    }

}