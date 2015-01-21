package com.mobandme.android.bind.parser;

import com.mobandme.android.bind.compiler.Compiler;

public class ParserFactory {
    public static DataParser getParser(Compiler.Mapping mapping) {
        DataParser result;

        try {
            result = (DataParser)mapping.getParser().newInstance();

            if (result instanceof DataParser == false) {
                String fieldName = mapping.getField().getName();
                throw new IllegalArgumentException(String.format("The parser defined by the field %s not extends of DataParser class.", fieldName));
            }

        } catch (InstantiationException error) {
            throw new RuntimeException(error);
        } catch (IllegalAccessException error) {
            throw new RuntimeException(error);
        }

        return result;
    }
}
