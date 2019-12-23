package ua.nure.kn.kostenko.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class User implements Serializable {
    private static final long serialVersionUID = -279719653246514883L;
    private Long id;
    private String firstName;
    private String lastName;

    public User(String firstName, String lastName, Date date) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.dateOfBirth=date;
    }
    public User(Long id, String firstName, String lastName, Date date) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = date;
    }

    public User(Long aLong, String lastName, String nextToken) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    private Date dateOfBirth;

    public User() {
        firstName = "";
        lastName = "";
    }

    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }

    public long getAge() { //исправить меттод
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentDay = calendar.get(Calendar.DATE);
        calendar.setTime(getDateOfBirth());
        int birthYear = calendar.get(Calendar.YEAR);
        int birthMonth = calendar.get(Calendar.MONTH);
        int birthDay = calendar.get(Calendar.DATE);
        long age = currentYear - birthYear;
        if (currentMonth < birthMonth) {
            return --age;
        } else if (currentMonth == birthMonth) {
            if (currentDay < birthDay) {
                return --age;
            }
        }
        return age;
    }
}
