package com.mobandme.android.bind.parser;

public abstract class DataParser {

    public final Object parse(com.mobandme.android.bind.compiler.Compiler.Mapping mapping, Object value) {
        return onParse(mapping, value);
    }

    public abstract Object onParse(com.mobandme.android.bind.compiler.Compiler.Mapping mapping, Object value);
}
