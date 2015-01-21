package com.mobandme.android.app.model;

import com.mobandme.android.app.parsers.CurrencyParser;
import com.mobandme.android.app.parsers.DateParser;
import com.mobandme.android.bind.annotations.BindTo;
import com.mobandme.android.bind.app.R;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class MyModel {

    @BindTo(viewId = R.id.active)
    public boolean active = true;

    @BindTo(viewId = R.id.name)
    public String name = "Txus";

    @BindTo(viewId = R.id.surname)
    private String surname = "Ballesteros";

    @BindTo(viewId = R.id.address, getter = "getPostalAddress", setter = "setPostalAddress")
    private String address = "My House Street, 21";

    @BindTo(viewId = R.id.dateOfBirth, parser = DateParser.class)
    private Calendar dateOfBirth;

    @BindTo(viewId = R.id.salary, parser = CurrencyParser.class)
    private float salary = 1234.567f;

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public String getPostalAddress() { return address; }
    public void setPostalAddress(String address) { this.address = address; }

    public Calendar getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(Calendar dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public float getSalary() { return salary; }
    public void setSalary(float salary) { this.salary = salary; }

    public MyModel() {
        dateOfBirth = GregorianCalendar.getInstance(Locale.getDefault());
        dateOfBirth.set(Calendar.YEAR, 1980);
        dateOfBirth.set(Calendar.MONTH, Calendar.AUGUST);
        dateOfBirth.set(Calendar.DAY_OF_MONTH, 23);
    }
}
