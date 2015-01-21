/*
 * Copyright Txus Ballesteros 2015 (@txusballesteros)
 *
 * This file is part of some open source application.
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 * Contact: Txus Ballesteros <txus.ballesteros@gmail.com>
 */

package com.mobandme.android.bind;

import android.view.ViewGroup;

import com.mobandme.android.bind.binder.DataBinder;
import com.mobandme.android.bind.binder.BinderFactory;
import com.mobandme.android.bind.compiler.Compiler;

import java.util.Collection;
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

        public Integer getBindDirectionReverse() {
            if (this.bindDirection == DIRECTION_OBJECT_TO_VIEWS)
                return DIRECTION_VIEWS_TO_OBJECT;
            else
                return DIRECTION_OBJECT_TO_VIEWS;
        }

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
     */
    public static final int DIRECTION_OBJECT_TO_VIEWS = 1;

    /**
     * Define the Data Binding direction between UI Views and Object.
     */
    public static final int DIRECTION_VIEWS_TO_OBJECT = 2;

    //endregion

    //region "PRIVATE VARIABLES"

    private Compiler compiler;
    private Builder builder;

    //endregion

    //region "PUBLIC METHODS"

    /**
     * Use this method to start the binding process.
     */
    public void bind() {
        int direction = this.builder.getBindDirection();
        bind(direction);
    }

    /**
     * Use this method to start a reversed binding progress.
     */
    public void bindReverse() {
        int direction = this.builder.getBindDirectionReverse();
        bind(direction);
    }

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

    private void bind(int direction) {
        Collection<Compiler.Mapping> mappingsList = this.compiler.getMappingsList();
        for(Compiler.Mapping mapping : mappingsList) {
            Object modelObject = getModelObject();
            DataBinder binder = BinderFactory.getBinder(mapping);
            binder.bind(mapping, modelObject, direction);
        }
    }

    //endregion
}
