package com.amdocs.comcast.orion.service;

import java.util.HashMap;
import java.util.List;

import com.amdocs.comcast.orion.input.EJBCallInput;

public interface IDemoService {

	String hi();

	List<EJBCallInput> list();

	boolean add(EJBCallInput demo);

	EJBCallInput getbyId(String flowName);

	boolean update(EJBCallInput demo);

	boolean delete(String flowName);

	HashMap<String, Object> execute(String flowName);

}
