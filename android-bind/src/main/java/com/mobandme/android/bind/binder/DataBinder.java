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
