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

package com.mobandme.android.bind.parser;

import com.mobandme.android.bind.compiler.Compiler;

public class GenericDataParser extends DataParser {

    @Override
    public Object onParse(Compiler.Mapping mapping, Object value, int direction) {
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