package com.mobandme.android.app.parsers;

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
