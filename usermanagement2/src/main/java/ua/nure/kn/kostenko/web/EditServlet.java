package ua.nure.kn.kostenko.web;


import ua.nure.kn.kostenko.db.DaoFactory;
import ua.nure.kn.kostenko.db.DatabaseException;
import ua.nure.kn.kostenko.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;

public class EditServlet extends HttpServlet {

    private static final long serialVersionUID = 983780000794673230L;

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("ok") != null) {
            doOk(req, resp);
        } else if (req.getParameter("cancel") != null) {
            doCancel(req, resp);
        } else {
            showPage(req, resp);
        }
    }

    protected void showPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/edit.jsp").forward(req, resp);
    }

    private void doCancel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/browse").forward(req, resp);
    }//updated servlet classes

    protected void doOk(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = null;
        try {
            user = getUser(req);
        } catch (ValidationException e) {
            req.setAttribute("error", e.getMessage());
            showPage(req, resp);
            return;
        } catch (ParseException e) {
            req.setAttribute("error", e.getMessage());
            showPage(req, resp);
            return;
        }
        try {
            DaoFactory.getInstance().getUserDao().update(user);
        } catch (DatabaseException e) {
            req.setAttribute("error", "Error in the database: " + e.getMessage());
            showPage(req, resp);
            return;
        }
        req.getRequestDispatcher("/browse").forward(req, resp);
    }

    protected User getUser(HttpServletRequest req) throws ValidationException, ParseException {
        User user = new User();
        user.setId(Long.valueOf(req.getParameter("id")));
        if (req.getParameter("firstName") == null) {
            throw new ValidationException("First name field is empty!");
        }
        user.setFirstName(req.getParameter("firstName"));
        if (req.getParameter("lastName") == null) {
            throw new ValidationException("Last name field is empty!");
        }
        user.setLastName(req.getParameter("lastName"));
        if (req.getParameter("dateOfBirth") == null) {
            throw new ValidationException("Date of birth field is empty!");
        }
        user.setDateOfBirth(DateFormat.getDateInstance().parse(req.getParameter("dateOfBirth")));
        return user;
    }
}


