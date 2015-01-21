package com.mobandme.android.app.model;

import com.mobandme.android.bind.annotations.BindTo;
import com.mobandme.android.bind.app.R;

public class MyModel {

    //@BindTo(viewId = R.id.name)
    //public String name = "Txus";

    @BindTo(viewId = R.id.surname)
    private String surname = "Ballesteros";

    @BindTo(viewId = R.id.address, getter = "getPostalAddress", setter = "setPostalAddress")
    private String address = "My House Street, 21";

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public String getPostalAddress() { return address; }
    public void setPostalAddress(String address) { this.address = address; }
}
