package com.mobandme.android.bind.annotations;

import com.mobandme.android.bind.binder.DataBinder;
import com.mobandme.android.bind.binder.GenericDataBinder;
import com.mobandme.android.bind.parser.GenericDataParser;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BindTo {
	/**
	 * Define the View control ID linked to the Entity property.
	 * @return
	 */
	public int viewId();
	
	/**
	 * Define the custom property getter suffix, Example: getXXXX() => getMySufix()
	 * @return
	 */
	public String getter() default "";
	
	/**
	 * Define the custom property setter suffix, Example: setXXXX(Object pVar) => setMySufix(Object pVar)
	 * @return
	 */
	public String setter() default "";
	
	/**
	 * Define the custom DataBinder Class.
	 * @return
	 */
	public Class<?> binder() default GenericDataBinder.class;
	
	
	/**
	 * Define the custom dapa parser class.
	 * @return
	 */
	public Class<?> parser()  default GenericDataParser.class;
}
