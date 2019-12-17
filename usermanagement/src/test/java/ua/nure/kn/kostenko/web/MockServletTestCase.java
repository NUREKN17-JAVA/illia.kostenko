package ua.nure.kn.kostenko.web;

import java.util.Calendar;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mockobjects.dynamic.Mock;
import com.mockrunner.servlet.BasicServletTestCaseAdapter;
import ua.nure.kn.kostenko.db.DaoFactory;
import ua.nure.kn.kostenko.db.MockDaoFactory;


public class MockServletTestCase extends BasicServletTestCaseAdapter {

    private Mock mockUserDao;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Properties properties = new Properties();
        properties.setProperty("dao.Factory", MockDaoFactory.class.getName());
        DaoFactory.init(properties);
        setMockUserDao(((MockDaoFactory) DaoFactory.getInstance()).getMockUserDao());
    }

    @Override
    public void tearDown() throws Exception {
        getMockUserDao().verify();
        super.tearDown();
    }


    public Mock getMockUserDao() {
        return mockUserDao;
    }

    public void setMockUserDao(Mock mockUserDao) {
        this.mockUserDao = mockUserDao;
    }




}
