package com.amdocs.comcast.orion.service;

import java.util.List;

import com.amdocs.comcast.orion.input.Demo;

public interface IDemoService {

	String hi();

	List<Demo> list();

	boolean add(Demo demo);

	Demo getbyId(Long id);

	boolean update(Demo demo);

	boolean delete(Long id);

}
