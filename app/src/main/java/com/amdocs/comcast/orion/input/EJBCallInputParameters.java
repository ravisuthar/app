package com.amdocs.comcast.orion.input;

import java.io.Serializable;



public class EJBCallInputParameters implements Serializable{

	private static final long serialVersionUID = -2850183441844923736L;

	private String key;
	
	private String value;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("EJBCallInputParameters [key=");
		stringBuilder.append(key);
		stringBuilder.append(", value=");
		stringBuilder.append(value);
		stringBuilder.append("]");
		return stringBuilder.toString();
	}
}
