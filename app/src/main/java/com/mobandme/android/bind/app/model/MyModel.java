/*
 * Copyright Txus Ballesteros 2015 (@txusballesteros)
 *
 * This file is part of some open source application.
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 * Contact: Txus Ballesteros <txus.ballesteros@gmail.com>
 */

package com.mobandme.android.bind.app.model;

import com.mobandme.android.bind.annotations.BindTo;
import com.mobandme.android.bind.annotations.Bindings;
import com.mobandme.android.bind.app.R;
import com.mobandme.android.bind.app.parsers.CurrencyParser;
import com.mobandme.android.bind.app.parsers.DateParser;
import com.mobandme.android.bind.app.parsers.LowerStringParser;
import com.mobandme.android.bind.app.parsers.UpperStringParser;

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

    @Bindings ({
        @BindTo(viewId = R.id.multiBindView1),
        @BindTo(viewId = R.id.multiBindView2, parser = UpperStringParser.class),
        @BindTo(viewId = R.id.multiBindView3, parser = LowerStringParser.class)
    })
    private String multiBindField = "Multi Bind field";

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public String getPostalAddress() { return address; }
    public void setPostalAddress(String address) { this.address = address; }

    public Calendar getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(Calendar dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public float getSalary() { return salary; }
    public void setSalary(float salary) { this.salary = salary; }

    public String getMultiBindField() { return multiBindField; }
    public void setMultiBindField(String multiBindField) { this.multiBindField = multiBindField; }

    public MyModel() {
        dateOfBirth = GregorianCalendar.getInstance(Locale.getDefault());
        dateOfBirth.set(Calendar.YEAR, 1980);
        dateOfBirth.set(Calendar.MONTH, Calendar.AUGUST);
        dateOfBirth.set(Calendar.DAY_OF_MONTH, 23);
    }
}
