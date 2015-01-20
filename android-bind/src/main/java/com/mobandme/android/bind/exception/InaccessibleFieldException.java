package com.mobandme.android.bind.exception;

public class InaccessibleFieldException extends RuntimeException {

	private String entityName = "";
	private String propertyName = "";
	private String methodName = "";
	
	public String getEntityName() { return entityName; }
	public String getFieldName() { return propertyName; }
	public String getMethodName() { return methodName; }
	
	public InaccessibleFieldException(String pEntity, String pProperty, String pMethod) { 
		super(generateMessage(pEntity, pProperty, pMethod));
		entityName = pEntity;
		propertyName = pProperty;
		methodName = pMethod;
	}
	
	@Override
	public String toString() {
		return generateMessage(entityName, propertyName, methodName);
	}
	
	private static String generateMessage(String pEntity, String pProperty, String pMethod) {
		
		if (pMethod != null && !pMethod.trim().equals("")) {
			return String.format("Inaccessible Field on Entity %s and Property %s, Method Name: %s.", pEntity, pProperty, pMethod);
		} else {
			return String.format("Inaccessible Field on Entity %s and Property %s.", pEntity, pProperty);	
		}
	}
}
