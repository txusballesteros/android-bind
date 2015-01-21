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

public class GenericDataBinder extends DataBinder {
    /**
     * Use this method to implements the Binding process.
     * @param mapping Binding mapping information.
     * @param object Data memory object.
     * @param view UI view.
     * @param direction Binding direction.
     */
    public void onBind(Compiler.Mapping mapping, Object object, View view, int direction) {
        Object value = getValue(mapping, object, view, direction);
        Object parsedValue = parseValue(mapping, value, direction);

        if (direction == Binder.DIRECTION_OBJECT_TO_VIEWS)
            setViewValue(mapping, view, parsedValue);
        else
            setValue(mapping, object, parsedValue);
    }

    private Object parseValue(Compiler.Mapping mapping, Object value, int direction) {
        return ParserFactory.getParser(mapping).parse(mapping, value, direction);
    }

    /**
     * Use this method to set a value into a property of the memory data object.
     * @param mapping Binding mapping information.
     * @param object Data memory object.
     * @param value Value to be set.
     */
    private void setValue(Compiler.Mapping mapping, Object object, Object value) {
        try {

            if (mapping.getSetter() != null)
                mapping.getSetter().invoke(object, new Object[] { value });
            else
                mapping.getField().set(object, value);

        } catch (Exception error) {
            throw new RuntimeException(error);
        }
    }

    /**
     * Use this method to set a value into a UI view.
     * @param mapping Binding mapping information.
     * @param view UI view object.
     * @param value Value to be set.
     */
    private void setViewValue(Compiler.Mapping mapping, View view, Object value) {
        if (view instanceof CheckBox)
            setViewValue((CheckBox)view, (boolean)value);
        else if (view instanceof SeekBar)
            setViewValue((SeekBar)view, (int)value);
        else if (view instanceof ProgressBar)
            setViewValue((ProgressBar)view, (int)value);
        else if (view instanceof RatingBar)
            setViewValue((RatingBar)view, (float)value);
        else if (view instanceof RadioButton)
            setViewValue((RadioButton)view, (boolean)value);
        else if (view instanceof ToggleButton)
            setViewValue((ToggleButton)view, (boolean)value);
        else if (view instanceof TextView)
            setViewValue((TextView)view, (String)value);
    }

    private void setViewValue(TextView view, String value) { view.setText(value); }

    private void setViewValue(CheckBox view, boolean value) { view.setChecked(value); }

    private void setViewValue(SeekBar view, int value) { view.setProgress(value); }

    private void setViewValue(ProgressBar view, int value) { view.setProgress(value); }

    private void setViewValue(RatingBar view, float value) { view.setRating(value); }

    private void setViewValue(RadioButton view, boolean value) { view.setChecked(value); }

    private void setViewValue(ToggleButton view, boolean value) { view.setChecked(value); }

    /**
     * This method extract the value from the UI View or from the memory data object, it's depend of the binding direction.
     * @param mapping Binding mapping information.
     * @param object Data memory object.
     * @param view UI view.
     * @param direction Binding direction.
     * @return The value to set into the destination.
     */
    private Object getValue(Compiler.Mapping mapping, Object object, View view, int direction) {
        Object result = null;

        try {

            if (direction == Binder.DIRECTION_OBJECT_TO_VIEWS) {
                if (mapping.getGetter() != null)
                    result = mapping.getGetter().invoke(object, (Object[]) null);
                else
                    result = mapping.getField().get(object);
            } else {
                result = getViewValue(view);
            }

        } catch (Exception error) {
            String fieldName = mapping.getField().getName();
            throw new RuntimeException(String.format("Error getting value of field %s.", fieldName), error);
        }

        return result;
    }

    private Object getViewValue(View view) {
        if (view instanceof TextView)
            return getViewValue((TextView)view);
        else if (view instanceof CheckBox)
            return getViewValue((CheckBox)view);
        else if (view instanceof SeekBar)
            return getViewValue((SeekBar)view);
        else if (view instanceof ProgressBar)
            return getViewValue((ProgressBar)view);
        else if (view instanceof RatingBar)
            return getViewValue((RatingBar)view);
        else if (view instanceof RadioButton)
            return getViewValue((RadioButton)view);
        else if (view instanceof ToggleButton)
            return getViewValue((ToggleButton)view);
        else
            return null;
    }

    private Object getViewValue(TextView view) { return view.getText(); }

    private Object getViewValue(CheckBox view) { return view.isChecked(); }

    private Object getViewValue(SeekBar view) { return view.getProgress(); }

    private Object getViewValue(ProgressBar view) { return view.getProgress(); }

    private Object getViewValue(RatingBar view) { return view.getRating(); }

    private Object getViewValue(RadioButton view) { return view.isChecked(); }

    private Object getViewValue(ToggleButton view) { return view.isChecked(); }
}
