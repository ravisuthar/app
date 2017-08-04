package com.amdocs.comcast.orion.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amdocs.comcast.orion.input.Demo;
import com.amdocs.comcast.orion.repository.IDemoRepository;
import com.amdocs.comcast.orion.service.IDemoService;

@Service
public class DemoServiceImpl implements IDemoService{

	@Autowired
	private IDemoRepository demoRepository;

	private List<Demo> list=new ArrayList<Demo>();
	
	@Override
	public String hi() {
		return "hi";
	}

	@Override
	public List<Demo> list() {
		return list;
	}

	@Override
	public boolean add(Demo demo) {
		return list.add(demo);
	}

	@Override
	public Demo getbyId(Long id) {
		for(Demo demo: list){
			if(demo.getId().equals(id)){
				return demo;
			}
		}
		return null;
	}

	@Override
	public boolean update(Demo demo) {
		
		for(Demo existingDemo: list){
			if(existingDemo.getId().equals(existingDemo.getId())){
				return true;
			}
		}
		
		return false;
	}

	@Override
	public boolean delete(Long id) {
		
		for(Demo existingDemo: list){
			if(existingDemo.getId().equals(id)){
				return list.remove(existingDemo);
			}
		}
		return false;
	}
	
	
	
}
