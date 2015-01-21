package com.mobandme.android.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mobandme.android.app.model.MyModel;
import com.mobandme.android.bind.Binder;
import com.mobandme.android.bind.app.R;

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
