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

import android.view.View;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.mobandme.android.bind.Binder;
import com.mobandme.android.bind.compiler.Compiler;
import com.mobandme.android.bind.parser.ParserFactory;

public abstract class DataBinder {
    /**
     * Use this method to start binding process.
     * @param mapping Binding mapping information.
     * @param object Data memory object.
     * @param direction Binding direction.
     */
    public final void bind(Compiler.Mapping mapping, Object object, int direction) {
        onBind(mapping, object, mapping.getView(), direction);
    }

    /**
     * Use this method to implements the Binding process.
     * @param mapping Binding mapping information.
     * @param object Data memory object.
     * @param view UI view.
     * @param direction Binding direction.
     */
    public abstract void onBind(Compiler.Mapping mapping, Object object, View view, int direction);
}
