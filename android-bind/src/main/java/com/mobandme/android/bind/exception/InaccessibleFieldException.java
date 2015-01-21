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
