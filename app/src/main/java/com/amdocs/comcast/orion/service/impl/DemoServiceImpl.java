package com.amdocs.comcast.orion.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.naming.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amdocs.comcast.orion.input.EJBCallInput;
import com.amdocs.comcast.orion.input.EJBCallInputParameters;
import com.amdocs.comcast.orion.repository.IDemoRepository;
import com.amdocs.comcast.orion.service.IDemoService;

@Service
public class DemoServiceImpl implements IDemoService {

	@Autowired
	private IDemoRepository demoRepository;

	@Override
	public String hi() {
		return "hi";
	}

	@Override
	public List<EJBCallInput> list() {
		return this.demoRepository.list();
	}

	@Override
	public boolean add(EJBCallInput ejbCallInput) {
		return this.demoRepository.persist(ejbCallInput);
	}

	@Override
	public EJBCallInput getbyId(String flowName) {
		return this.demoRepository.getbyId(flowName);
	}

	@Override
	public boolean update(EJBCallInput ejbCallInput) {
		return this.demoRepository.update(ejbCallInput);
	}

	@Override
	public boolean delete(String flowName) {
		return this.demoRepository.remove(flowName);
	}

	@Override
	public HashMap<String, Object> execute(String flowName) {
		EJBCallInput ejb=this.getbyId(flowName);
		HashMap<String, String> inputMap = prepareInputMap(ejb.getBusinessFlowInputMap());
		return this.callRMService(ejb.getFlowName(), inputMap);
	}

	public static HashMap<String, String> prepareInputMap(List<EJBCallInputParameters> list) {
		HashMap<String, String> map = new HashMap<String, String>();
		for (EJBCallInputParameters parameter : list) {
			map.put(parameter.getKey(), parameter.getValue());
		}
		return map;
	}

	public HashMap<String, Object> callRMService(String flowName, HashMap<String, String> businessFlowInputMap) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Context ic = null;
		System.out.println("operation name :" + flowName);
		System.out.println("MAP--------->" + businessFlowInputMap);
		System.out.println("callRMService EJB Call : Called" + "  EJB Operation : " + flowName + " EJB Request : " + businessFlowInputMap);

		long startTime = System.currentTimeMillis();
		//resultMap = (HashMap) RMQueryServicesConsumer.getInstance().consume(flowName, businessFlowInputMap, "");
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		System.out.println(" Query Executed at RM in " + elapsedTime + " Miliseconds.");
		System.out.println("callRMService EJB Call : EJB Response : " + resultMap);

		System.out.println("callRMService EJB Call : Ended");
		return resultMap;
	}

}
