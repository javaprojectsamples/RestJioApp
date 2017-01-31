package com.jio.pe.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jio.pe.app.bo.IUserMasterBO;
import com.jio.pe.app.model.UserMaster;
import com.jio.pe.app.request.AbstractRequest;

@RestController
public class UserMasterRestController {

	@Autowired
	private IUserMasterBO iUserMasterBo;

	AbstractRequest abstractRequest;

	@GetMapping("/users")
	public List<UserMaster> getUsers() {
		return iUserMasterBo.getList(abstractRequest);
	}

	@GetMapping("/users/{id}")
	public ResponseEntity getUsers(@PathVariable("id") Long id) {

		UserMaster userMaster = iUserMasterBo.getEntity(id);
		if (userMaster == null) {
			return new ResponseEntity("No User found for ID " + id, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(userMaster, HttpStatus.OK);
	}

	@PostMapping(value = "/users")
	public ResponseEntity createUser(@RequestBody UserMaster userMaster) {

		int i = iUserMasterBo.add(userMaster);
		if (i == 0) {
			return new ResponseEntity(userMaster, HttpStatus.OK);
		} else {
			return new ResponseEntity("User not added", HttpStatus.NOT_IMPLEMENTED);
		}

	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity deleteUser(@PathVariable Long id) {
        
		UserMaster userMaster= new UserMaster();
		userMaster.setUserId(id);
		
		int i = iUserMasterBo.delete(userMaster);
		if (i  != 0 ) {
			return new ResponseEntity("No User found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(id, HttpStatus.OK);

	}

	@PutMapping("/users/{id}")
	public ResponseEntity updateUser(@PathVariable Long id, @RequestBody UserMaster userMaster) {

		int i = iUserMasterBo.update(userMaster);

		if (i != 0) {
			return new ResponseEntity("No User found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(userMaster, HttpStatus.OK);
	}

}