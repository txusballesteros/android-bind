package com.mobandme.android.bind.parser;

import com.mobandme.android.bind.compiler.Compiler;

public abstract class DataParser {

    public final Object parse(Compiler.Mapping mapping, Object value, int direction) {
        return onParse(mapping, value, direction);
    }

    public abstract Object onParse(Compiler.Mapping mapping, Object value, int direction);
}
