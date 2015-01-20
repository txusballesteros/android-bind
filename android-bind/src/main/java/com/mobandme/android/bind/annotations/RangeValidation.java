package com.mobandme.android.bind.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import com.mobandme.android.bind.validators.RangeValidator;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RangeValidation {

	/**
	 * Define the minimum range value.
	 * @return
	 */
	public int minValue();
	
	/**
	 * Define the maximum range value.
	 * @return
	 */
	public int maxValue();
	
	/**
	 * Define the error message when the validation is not ok. 
	 * @return
	 */
	public String message() default "* Required Field.";
	
	/**
	 * Define the error message resource string when the validation is not ok.
	 * @return
	 */
	public int messageResourceId() default 0;
	
	/**
	 * Define the custom property getter suffix, Example: getXXXX() => getMySufix()
	 * @return
	 */
	public String getterSuffix() default "";
	
	/**
	 * Define the custom property setter suffix, Example: setXXXX(Object pVar) => setMySufix(Object pVar)
	 * @return
	 */
	public String setterSuffix() default "";
	
	/**
	 * Define the custom DataBinder Class.
	 * @return
	 */
	Class<?> validator() default RangeValidator.class;
}
