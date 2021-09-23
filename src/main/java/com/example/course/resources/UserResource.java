package com.example.course.resources;

import java.net.URI;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.course.entities.User;
import com.example.course.services.UserService;
//// controlador REST ....endpoint

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService service; ////user service tem q estar registrada como comp. do spring
	
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
	
	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User obj){
		obj = service.insert(obj);
		/////return ResponseEntity.ok().body(obj) ; return 200 ok
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri(); 
		return ResponseEntity.created(uri).body(obj) ; /// retorna ok 201 created
		
	}
	
	//// banco de dados só deixa deletar usuario sem pedido
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build(); ///retorna 204
	}
}