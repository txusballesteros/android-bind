package com.mobandme.android.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.mobandme.android.app.model.MyModel;
import com.mobandme.android.bind.Binder;
import com.mobandme.android.bind.app.R;

public class MainActivity extends Activity implements View.OnClickListener {

    private MyModel myModel = new MyModel();
    private ViewGroup rootView;
    private Binder myModelBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootView = (ViewGroup)findViewById(R.id.rootView);
                   findViewById(R.id.bind).setOnClickListener(this);

        initializeMyModelBinder();
    }

    private void initializeMyModelBinder() {
        myModelBinder = new Binder.Builder()
                                .setSource(myModel)
                                .setDestination(rootView)
                                .build();
    }

    @Override
    public void onClick(View v) {
        bindModel();
    }

    private void bindModel() {
        myModelBinder.bind();
    }
}
