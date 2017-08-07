package com.amdocs.comcast.orion.output;
import java.util.HashMap;

public class EjbCallOutput {

	HashMap<String, Object> map;

	String errorCode;

	String errorText;

	public HashMap<String, Object> getMap() {
		return map;
	}

	public void setMap(HashMap<String, Object> map) {
		this.map = map;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorText() {
		return errorText;
	}

	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("EjbCallOutput [map=");
		stringBuilder.append(map);
		stringBuilder.append(", errorCode=");
		stringBuilder.append(errorCode);
		stringBuilder.append(", errorText=");
		stringBuilder.append(errorText);
		stringBuilder.append("]");
		return stringBuilder.toString();
	}

}
