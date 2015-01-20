package com.mobandme.android.bind.parser;

import com.mobandme.android.bind.compiler.Compiler;

public class ParserFactory {
    public static DataParser getParser(Compiler.Mapping mapping) {
        DataParser result;

        try {
            result = (DataParser)mapping.getParser().newInstance();
        } catch (Exception error) {
            throw new RuntimeException(error);
        }

        return result;
    }
}
