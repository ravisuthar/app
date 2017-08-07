package com.amdocs.comcast.orion.repository.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.amdocs.comcast.orion.input.EJBCallInput;
import com.amdocs.comcast.orion.repository.IDemoRepository;

@Repository
public class DemoRepository implements IDemoRepository {

	
	@Override
	public boolean update(EJBCallInput ejbCallInput) {
		List<EJBCallInput> list =this.readFromFile();
		EJBCallInput object = null;
		for(EJBCallInput ejb: list){
			if(ejb.getFlowName().equalsIgnoreCase(ejbCallInput.getFlowName())){
				object=ejb;
			}
		}
		list.remove(object);
		list.add(ejbCallInput);
		this.saveToFile(list);
		return true;
	}
	
	
	@Override
	public boolean persist(EJBCallInput ejbCallInput) {
		List<EJBCallInput> list =this.readFromFile();
		list.add(ejbCallInput);
		this.saveToFile(list);
		return true;
	} 
	
	
	@Override
	public List<EJBCallInput> list(){
		return this.readFromFile();
	}
	
	
	@Override
	public boolean remove(String flowname){
		List<EJBCallInput> list =this.readFromFile();
		EJBCallInput object = null;
		for(EJBCallInput ejb: list){
			if(ejb.getFlowName().equalsIgnoreCase(flowname)){
				object=ejb;
			}
		}
		list.remove(object);
		this.saveToFile(list);
		return true;
	}
	
	@Override
	public EJBCallInput getbyId(String flowName) {
		List<EJBCallInput> list =this.readFromFile();
		for(EJBCallInput ejb: list){
			if(ejb.getFlowName().equalsIgnoreCase(flowName)){
				return ejb;
			}
		}
		return null;
	}
	
	public void saveToFile(List<EJBCallInput> list) {
		try {
			FileOutputStream fileOut = new FileOutputStream(System.getProperty("java.io.tmpdir") + File.separator + "ejb1.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(list);
			out.close();
			fileOut.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
	}
	
	public List<EJBCallInput> readFromFile(){
		List<EJBCallInput> list=new ArrayList<EJBCallInput>();
		try {
	         FileInputStream fileIn = new FileInputStream(System.getProperty("java.io.tmpdir") + File.separator + "ejb1.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         list = (List<EJBCallInput>) in.readObject();
	         in.close();
	         fileIn.close();
	      }catch(ClassNotFoundException | IOException i) {
	         i.printStackTrace();
	      }
		return list;
	}

}
