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

package com.mobandme.android.bind.app.parsers;

import com.mobandme.android.bind.Binder;
import com.mobandme.android.bind.compiler.Compiler;
import com.mobandme.android.bind.parser.DataParser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateParser extends DataParser {

    @Override
    public Object onParse(Compiler.Mapping mapping, Object value, int direction) {
        Object result = value;
        SimpleDateFormat dateFormatter =  new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        if (direction == Binder.DIRECTION_OBJECT_TO_VIEWS) {
            Calendar date = (Calendar)value;
            result = dateFormatter.format(date.getTime());
        } else {
            try {
                Date date = dateFormatter.parse(value.toString());
                Calendar calendar = GregorianCalendar.getInstance(Locale.getDefault());
                calendar.setTime(date);

                result = calendar;

            } catch (ParseException error) {
            }
        }

        return result;
    }
}
