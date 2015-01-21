package com.mobandme.android.bind.binder;

import com.mobandme.android.bind.compiler.Compiler;

public class BinderFactory {
    public static DataBinder getBinder(Compiler.Mapping mapping) {
        DataBinder result;

        try {
            result = (DataBinder)mapping.getBinder().newInstance();

            if (result instanceof DataBinder == false) {
                String fieldName = mapping.getField().getName();
                throw new IllegalArgumentException(String.format("The custom DataBinder defined by the field %s not extends of DataBinder class.", fieldName));
            }

        } catch (InstantiationException error) {
            throw new RuntimeException(error);
        } catch (IllegalAccessException error) {
            throw new RuntimeException(error);
        }

        return result;
    }
}
