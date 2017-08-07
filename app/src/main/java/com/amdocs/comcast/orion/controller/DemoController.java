package com.amdocs.comcast.orion.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amdocs.comcast.orion.input.EJBCallInput;
import com.amdocs.comcast.orion.output.EjbCallOutput;
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
	public List<EJBCallInput> list(){
		return this.demoService.list();
	}
	
	@RequestMapping(method=RequestMethod.POST,  value="/add")
	public boolean add(@RequestBody EJBCallInput demo){
		return this.demoService.add(demo);
	}
	
	@RequestMapping(method=RequestMethod.GET,  value="/getbyId/{flowName}")
	public EJBCallInput getbyId(@PathVariable(name="flowName") String flowName){
		return this.demoService.getbyId(flowName);
	}
	
	@RequestMapping(method=RequestMethod.POST,  value="/update")
	public boolean update(@RequestBody EJBCallInput demo){
		return this.demoService.update(demo);
	}
	
	
	@RequestMapping(method=RequestMethod.GET,  value="/delete/{flowName}")
	public boolean delete(@PathVariable(name="flowName") String flowName){
		return this.demoService.delete(flowName);
	}
	
	@RequestMapping(method=RequestMethod.GET,  value="/execute/{flowName}")
	public EjbCallOutput execute(@PathVariable(name="flowName") String flowName){
		EjbCallOutput output=new EjbCallOutput();
		HashMap<String, Object> map= this.demoService.execute(flowName);
		output.setMap(map);
		return output;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(DemoController.class, args);
	}
	
}
