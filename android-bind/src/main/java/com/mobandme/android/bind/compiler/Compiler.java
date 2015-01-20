package com.mobandme.android.bind.compiler;

import android.view.View;
import android.view.ViewGroup;

import com.mobandme.android.bind.Binder;
import com.mobandme.android.bind.annotations.BindTo;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

public final class Compiler {

    //region "PRIVATE VARIABLES"

    private Object object;
    private ViewGroup rootView;
    private int direction;
    private HashMap<String, Mapping> mappings;

    //endregion

    public Compiler(Object object, ViewGroup rootView) {
        this.object = object;
        this.rootView = rootView;
        this.mappings = new HashMap<String, Mapping>();

        readObjectConfiguration();
    }

    public Compiler compile() {
        readObjectConfiguration();

        return this;
    }


    private void readObjectConfiguration() {
        Field[] declaredFields = this.object.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            BindTo bindAnnotation = field.getAnnotation(BindTo.class);
            if (bindAnnotation != null) {
                String fieldName = field.getName();
                int viewId = bindAnnotation.viewId();
                View view = extractView(viewId);
                Method getterMethod = extractGetterMethod(this.object, field, bindAnnotation);
                Method setterMethod = extractSetterMethod(this.object, field, bindAnnotation);

                Mapping mapping = new Mapping(field, view, getterMethod, setterMethod);
                this.mappings.put(fieldName, mapping);
            }
        }
    }

    private Method extractGetterMethod(Object object, Field field, BindTo bind) {
        Method result = null;

        String methodName = null;
        if (bind.getter().equals(""))
            if (field.getType() != Boolean.class)
                methodName = String.format("get%s", field.getName());
            else
                methodName = String.format("is%s", field.getName());
        else
            methodName = bind.getter();

        result = extractMethod(object.getClass(), methodName, null);
        return result;
    }

    private Method extractSetterMethod(Object object, Field field, BindTo bind) {
        Method result = null;

        String methodName = null;
        if (bind.setter().equals(""))
            methodName = String.format("set%s", field.getName());
        else
            methodName = bind.setter();

        result = extractMethod(object.getClass(), methodName, new Class[]{field.getType()});
        return result;
    }

    private Method extractMethod(Class objectClass, String methodName, Class[] argumentsTypes) {
        Method result = null;

        try {
            result = objectClass.getMethod(methodName, argumentsTypes);
        } catch (Exception error) {
            new RuntimeException(String.format("Error getting method %s", methodName), error);
        }

        return result;
    }

    private View extractView(int viewId) {
        return this.rootView.findViewById(viewId);
    }

    //region "PRIVATE CLASES"

    protected class Mapping {

        private Field field;
        private View  view;
        private Method getter;
        private Method setter;

        public Mapping(Field field, View view, Method getter, Method setter) {
            this.field = field;
            this.view = view;
            this.getter = getter;
            this.setter = setter;
        }
    }

    //endregion
}
