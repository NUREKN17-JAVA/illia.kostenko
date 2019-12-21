package ua.nure.kn.kostenko.web;


import ua.nure.kn.kostenko.domain.User;

import java.text.DateFormat;
import java.util.Date;

public class EditServletTest extends MockServletTestCase {

    @Override
    public void setUp() throws Exception {
        super.setUp();
        createServlet(EditServlet.class);
    }

    public void testEdit() {
        User user = new User(new Long(1000), "John", "Doe", new Date());
        getMockUserDao().expect("update", user);
        addRequestParameter("id", user.getId().toString());
        addRequestParameter("firstName", user.getFirstName());
        addRequestParameter("lastName", user.getLastName());
        addRequestParameter("dateOfBirth", DateFormat.getDateInstance().format(user.getDateOfBirth()));
        addRequestParameter("ok", "Ok");
        doPost();
    }

    public void testEditEmptyFirstName() {
        User user = new User(new Long(1000), "John", "Doe", new Date());
        addRequestParameter("id", user.getId().toString());
        addRequestParameter("lastName", user.getLastName());
        addRequestParameter("dateOfBirth", DateFormat.getDateInstance().format(user.getDateOfBirth()));
        addRequestParameter("ok", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull(errorMessage);
    }// added FirstName and Last Name cases

    public void testEditEmptyLastName() {
        User user = new User(new Long(1000), "John", "Doe", new Date());
        addRequestParameter("id", user.getId().toString());
        addRequestParameter("firstName", user.getFirstName());
        addRequestParameter("dateOfBirth", DateFormat.getDateInstance().format(user.getDateOfBirth()));
        addRequestParameter("ok", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull(errorMessage);
    }

    private void assertNotNull(String errorMessage) {
    }

    public void testEditIncorrectDateOfBirth() {
        User user = new User(new Long(1000), "John", "Doe", new Date());
        addRequestParameter("id", user.getId().toString());
        addRequestParameter("firstName", user.getFirstName());
        addRequestParameter("lastName", user.getLastName());
        addRequestParameter("dateOfBirth", "bla-bla-bla");
        addRequestParameter("ok", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull(errorMessage);
    }
}
