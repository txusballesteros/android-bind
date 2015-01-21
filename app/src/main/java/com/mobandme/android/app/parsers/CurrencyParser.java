package com.mobandme.android.app.parsers;

import com.mobandme.android.bind.Binder;
import com.mobandme.android.bind.compiler.Compiler;
import com.mobandme.android.bind.parser.DataParser;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CurrencyParser extends DataParser {

    @Override
    public Object onParse(Compiler.Mapping mapping, Object value, int direction) {
        if (direction == Binder.DIRECTION_OBJECT_TO_VIEWS) {
            return String.format("%.02f €", value);
        } else {
            String cleanValue = value.toString().replace('€', ' ').trim();
            return Float.parseFloat(cleanValue);
        }
    }
}
