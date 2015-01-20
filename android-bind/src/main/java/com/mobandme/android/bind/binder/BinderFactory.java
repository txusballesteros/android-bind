package com.mobandme.android.bind.binder;

import com.mobandme.android.bind.compiler.Compiler;

public class BinderFactory {
    public static DataBinder getBinder(Compiler.Mapping mapping) {
        DataBinder result;

        try {
            result = (DataBinder)mapping.getBinder().newInstance();
        } catch (Exception error) {
            throw new RuntimeException(error);
        }

        return result;
    }
}
