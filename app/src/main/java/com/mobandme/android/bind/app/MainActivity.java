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

package com.mobandme.android.bind.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.mobandme.android.bind.Binder;
import com.mobandme.android.bind.app.model.MyModel;

public class MainActivity extends Activity implements View.OnClickListener {

    private MyModel myModel = new MyModel();
    private ViewGroup rootView;
    private Binder myModelViewBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootView = (ViewGroup)findViewById(R.id.rootView);
                   findViewById(R.id.bind1).setOnClickListener(this);
                   findViewById(R.id.bind2).setOnClickListener(this);

        initializeBinders();
    }

    private void initializeBinders() {
        myModelViewBinder = new Binder.Builder()
                                .setSource(myModel)
                                .setDestination(rootView)
                                .build();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bind1)
            bindModelToView();
        else
            bindViewToModel();
    }

    private void bindModelToView() {
        myModelViewBinder.bind();
    }

    private void bindViewToModel() {
        myModelViewBinder.bindReverse();
        Toast.makeText(this, myModel.name, Toast.LENGTH_SHORT).show();
    }
}
