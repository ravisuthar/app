package com.amdocs.comcast.orion.input;

import java.io.Serializable;
import java.util.List;

public class EJBCallInput implements Serializable{

	private static final long serialVersionUID = 5120700205179215551L;

	private String flowName;

	private List<EJBCallInputParameters> businessFlowInputMap;

	public String getFlowName() {
		return flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	public List<EJBCallInputParameters> getBusinessFlowInputMap() {
		return businessFlowInputMap;
	}

	public void setBusinessFlowInputMap(List<EJBCallInputParameters> businessFlowInputMap) {
		this.businessFlowInputMap = businessFlowInputMap;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("EJBCallInput [flowName=");
		stringBuilder.append(flowName);
		stringBuilder.append(", businessFlowInputMap=");
		stringBuilder.append(businessFlowInputMap);
		stringBuilder.append("]");
		return stringBuilder.toString();
	}
}
