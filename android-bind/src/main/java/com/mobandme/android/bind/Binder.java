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

        private Integer bindDirection = Binder.DIRECTION_UNKNOW;
        private Object source;
        private Object destination;

        /**
         * Use this method to establish the origin of the data.
         * @param source Instance to the Data object or to the ViewGroup.
         */
        public Builder setSource(Object source) {
            if (source == null)
                throw new IllegalArgumentException("The argument source cannot be null.");

            this.source = source;
            return this;
        }

        protected Object getSource() { return this.source; }

        /**
         * Use this method to establish the end of the data.
         * @param destination Instance to the Data object or to the ViewGroup.
         */
        public Builder setDestination(Object destination) {
            if (destination == null)
                throw new IllegalArgumentException("The argument destination cannot be null.");

            this.destination = destination;
            return this;
        }

        protected Object getDestination() { return this.destination; }

        /**
         * Use this method to obtain the data flow direction.
         * @return
         */
        public Integer getBindDirection() { return this.bindDirection; }

        /**
         * Use this method to make a Binder instance.
         * @return An instance of Binder.
         */
        public Binder build() {
            if (getSource() == null || getDestination() == null)
                throw new IllegalArgumentException("The parameters Source and Destination cannot be null. Please use the method setSource and setDestination to establish these.");

            if (this.source instanceof ViewGroup)
                this.bindDirection = Binder.DIRECTION_VIEWS_TO_OBJECT;

            if (this.destination instanceof ViewGroup)
                this.bindDirection = Binder.DIRECTION_OBJECT_TO_VIEWS;

            if (this.bindDirection == Binder.DIRECTION_UNKNOW)
                throw new IllegalArgumentException("The parameters Source or Destination not is an android.view.ViewGroup member.");

            return new Binder(this);
        }
    }

    //endregion

    //region "CONSTANTS DEFINITION"

    /**
     * Define the unknow Data Binding direction.
     */
    public static final int DIRECTION_UNKNOW = 0;

    /**
     * Define the Data Binding direction between an Object and UI Views.
     * 	   Object --> UI Views
     */
    public static final int DIRECTION_OBJECT_TO_VIEWS = 1;

    /**
     * Define the Data Binding direction between UI Views and Object.
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

    private Object getModelObject() {
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

            Object modelObject = getModelObject();
            DataBinder binder = BinderFactory.getBinder(mapping);
            binder.bind(mapping, modelObject, this.builder.getBindDirection());
        }
    }
}
