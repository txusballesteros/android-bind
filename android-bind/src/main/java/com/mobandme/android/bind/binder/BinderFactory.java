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
