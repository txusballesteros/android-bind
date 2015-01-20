package com.mobandme.android.bind.parser;

import com.mobandme.android.bind.compiler.Compiler;

public class DataParser {

    public Object parse(Compiler.Mapping mapping, Object value) {
        return onParse(mapping, value);
    }

    public Object onParse(Compiler.Mapping mapping, Object value) {
        Object result = value;

        if (value != null) {
            final Class<?> fieldType = mapping.getField().getType();

            if (fieldType == String.class) {
                if (value.getClass() == String.class) {
                    result = (String)value;
                } else {
                    result = value.toString();
                }
            } else if (fieldType == Integer.class || fieldType == int.class) {
                if (value.getClass() == String.class) {
                    if (!((String)value).trim().equals("")) {
                        result = Integer.parseInt((String)value);
                    }
                } else {
                    if (!((String)value).trim().equals("")) {
                        result = Integer.parseInt(value.toString());
                    }
                }
            } else if (fieldType == Long.class || fieldType == long.class) {
                if (value.getClass() == String.class) {
                    if (!((String)value).trim().equals("")) {
                        result = Long.parseLong((String)value);
                    }
                } else {
                    if (!((String)value).trim().equals("")) {
                        result = Long.parseLong(value.toString());
                    }
                }
            } else if (fieldType == Double.class || fieldType == double.class) {
                if (value.getClass() == String.class) {
                    if (!((String)value).trim().equals("")) {
                        result = Double.parseDouble((String)value);
                    }
                } else {
                    if (!((String)value).trim().equals("")) {
                        result = Double.parseDouble(value.toString());
                    }
                }
            }  else if (fieldType == Float.class || fieldType == float.class) {
                if (value.getClass() == String.class) {
                    if (!((String)value).trim().equals("")) {
                        result = Float.parseFloat((String)value);
                    }
                } else {
                    if (!((String)value).trim().equals("")) {
                        result = Float.parseFloat(value.toString());
                    }
                }
            }  else if (fieldType == Short.class || fieldType == short.class) {
                if (value.getClass() == String.class) {
                    if (!((String)value).trim().equals("")) {
                        result = Short.parseShort((String)value);
                    }
                } else {
                    if (!((String)value).trim().equals("")) {
                        result = Short.parseShort(value.toString());
                    }
                }
            }  else if (fieldType == Boolean.class || fieldType == boolean.class || fieldType == android.R.bool.class) {
                if (value.getClass() == String.class) {
                    if (!((String)value).trim().equals("")) {
                        result = Boolean.parseBoolean((String)value);
                    }
                } else {
                    if (value.toString().trim() != "") {
                        result = Boolean.parseBoolean(value.toString());
                    }
                }
            }
        }

        return result;
    }
}