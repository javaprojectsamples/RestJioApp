package com.jio.pe.app.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jio.pe.app.bo.IUserMasterBO;
import com.jio.pe.app.model.UserMaster;

public class JioAppMain {

	public static void main(String arg[]) {

		ApplicationContext appContext = new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");

		IUserMasterBO iUserMasterBo = (IUserMasterBO) appContext.getBean("iUserMasterBO");

		UserMaster userMaster = iUserMasterBo.getEntity(1);

			
		System.out.println("userMaster::"+userMaster.getUserName());
		
		userMaster = new UserMaster();
		userMaster.setUserName("Everybody"); 
		int i= iUserMasterBo.add(userMaster);
		
		userMaster = iUserMasterBo.getEntity(9);
		System.out.println("userMaster::"+userMaster.getUserName());
	}
}
