package com.mobandme.android.bind;

import android.view.ViewGroup;

import com.mobandme.android.bind.binder.DataBinder;
import com.mobandme.android.bind.binder.BinderFactory;
import com.mobandme.android.bind.compiler.Compiler;

import java.util.Set;

public class Binder {

    //region "BUILDER"

    /**
     * Use this class to get and configure your Binder class.
     */
    public final static class Builder {

        private int bindDirection = Binder.DIRECTION_OBJECT_TO_VIEWS;
        private Object source;
        private Object destination;

        /**
         * Use this method to establish the origin of the data.
         * @param source Instance to the Data object or to the ViewGroup.
         */
        public void setSource(Object source) {
            if (source == null)
                throw new IllegalArgumentException("The argument source cannot be null.");

            this.source = source;
        }

        protected Object getSource() { return this.source; }

        /**
         * Use this method to establish the end of the data.
         * @param destination Instance to the Data object or to the ViewGroup.
         */
        public void setDestination(Object destination) {
            if (destination == null)
                throw new IllegalArgumentException("The argument destination cannot be null.");

            this.destination = destination;
        }

        protected Object getDestination() { return this.destination; }

        /**
         * Use this methos to configure the direction of the binding process.
         * @param direction Establish the direction of the binding process, please use one of these constants Binder.DIRECTION_OBJECT_TO_VIEWS or Binder.DIRECTION_VIEWS_TO_OBJECT.
         */
        public void setBindDirection(int direction) {
            if (direction != DIRECTION_OBJECT_TO_VIEWS || direction != DIRECTION_VIEWS_TO_OBJECT)
                throw new IllegalArgumentException("Invalid bind direction. Please use one of these constants Binder.DIRECTION_OBJECT_TO_VIEWS or Binder.DIRECTION_VIEWS_TO_OBJECT.");

            this.bindDirection = direction;
        }

        protected int getBindDirection() { return this.bindDirection; }

        /**
         * Use this method to make a Binder instance.
         * @return An instance of Binder.
         */
        public Binder build() {
            if (getSource() == null)
                throw new IllegalArgumentException("The parameter Source cannot be null. Please use the method setSource to establish it.");
            if (getDestination() == null)
                throw new IllegalArgumentException("The parameter Destination cannot be null. Please use the method setDestination to establish it.");

            if (this.bindDirection == Binder.DIRECTION_OBJECT_TO_VIEWS)
                if (this.destination instanceof ViewGroup == false)
                    throw new IllegalArgumentException("The parameter Destination not is an android.view.ViewGroup instance.");
            else
                if (this.source instanceof ViewGroup == false)
                    throw new IllegalArgumentException("The parameter Source not is an android.view.ViewGroup instance.");

            return new Binder(this);
        }
    }

    //endregion

    //region "CONSTANTS DEFINITION"

    /**
     * Define Data Binding direction between an Object and UI Views.
     * 	   Object --> UI Views
     */
    public static final int DIRECTION_OBJECT_TO_VIEWS = 1;

    /**
     * Define Data Binding direction between UI Views and Object.
     * 	   UI Views --> Object
     */
    public static final int DIRECTION_VIEWS_TO_OBJECT = 2;

    //endregion

    //region "PRIVATE VARIABLES"

    private Compiler compiler;
    private Builder builder;

    //endregion

    //region "PRIVATE METHODS"

    private Binder(Builder builder) {
        this.builder = builder;

        initializeCompiler();
    }

    private void initializeCompiler() {
        if (builder.getBindDirection() == Binder.DIRECTION_OBJECT_TO_VIEWS)
            this.compiler = new Compiler(builder.getSource(), (ViewGroup)builder.getDestination());
        else
            this.compiler = new Compiler(builder.getDestination(), (ViewGroup)builder.getSource());

        this.compiler.compile();
    }

    private Object getObject() {
        if (this.builder.getBindDirection() == DIRECTION_OBJECT_TO_VIEWS)
            return this.builder.getSource();
        else
            return this.builder.getDestination();
    }

    //endregion

    /**
     * Use this method to start the binding process.
     */
    public void bind() {
        Set<String> mappingsList = this.compiler.getMappingsList();
        for(String mappingKey : mappingsList) {
            Compiler.Mapping mapping = this.compiler.getMappig(mappingKey);

            Object object = getObject();
            DataBinder binder = BinderFactory.getBinder(mapping);
            binder.bind(mapping, object, this.builder.getBindDirection());
        }
    }
}
