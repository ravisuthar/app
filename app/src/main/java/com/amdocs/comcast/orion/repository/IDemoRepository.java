package com.amdocs.comcast.orion.repository;

import java.util.List;

import com.amdocs.comcast.orion.input.EJBCallInput;

public interface IDemoRepository {

	boolean persist(EJBCallInput ejbCallInput);

	List<EJBCallInput> list();

	boolean remove(String flowname);

	EJBCallInput getbyId(String flowName);

	boolean update(EJBCallInput ejbCallInput);

}
