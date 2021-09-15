package com.example.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.course.entities.User;
import com.example.course.services.UserService;
//// controlador REST

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService service;
	
	@GetMapping
	 public ResponseEntity<List<User>> findAll(){
		 List<User> list= service.findAll();
		
		 /////User u = new User(1L,"Maria","Maria@gmail.com","999999","999999");
		/////return ResponseEntity.ok().body(u);
		 
		 return ResponseEntity.ok().body(list);
	 }
	
	@GetMapping(value = "/{id}")
	 public ResponseEntity<User> findById(@PathVariable Long id) {
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj) ;
	}
}