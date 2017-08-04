package com.amdocs.comcast.orion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amdocs.comcast.orion.input.Demo;
import com.amdocs.comcast.orion.service.IDemoService;


@SpringBootApplication(scanBasePackages="com.amdocs.comcast.orion")
@RestController
@RequestMapping(value="/demo")
public class DemoController {


	@Autowired
	private IDemoService demoService;
	
	@RequestMapping(method=RequestMethod.GET, value="/hi")
	public String hi(){
		return this.demoService.hi();
	}
	
	@RequestMapping(method=RequestMethod.GET,  value="/list")
	public List<Demo> list(){
		return this.demoService.list();
	}
	
	
	@RequestMapping(method=RequestMethod.POST,  value="/add")
	public boolean add(@RequestBody Demo demo){
		return this.demoService.add(demo);
	}
	
	
	@RequestMapping(method=RequestMethod.GET,  value="/getbyId/{id}")
	public Demo getbyId(@PathVariable(name="id") Long id){
		return this.demoService.getbyId(id);
	}
	
	@RequestMapping(method=RequestMethod.POST,  value="/update")
	public boolean update(@RequestBody Demo demo){
		return this.demoService.update(demo);
	}
	
	
	@RequestMapping(method=RequestMethod.GET,  value="/delete/{id}")
	public boolean delete(@PathVariable(name="id") Long id){
		return this.demoService.delete( id);
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(DemoController.class, args);
	}
}
