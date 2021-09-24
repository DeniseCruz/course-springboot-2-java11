package com.example.course.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.course.entities.User;
import com.example.course.repositories.UserRepository;
import com.example.course.services.exceptions.DatabaseException;
import com.example.course.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
	///	return obj.get(); //// linha sem tratamento de exceção para retorna 404 ao inves de 500
		return obj.orElseThrow(() -> new ResourceNotFoundException(id)) ;
	}

	public User insert(User obj){
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		try {
		   repository.deleteById(id);
		} catch(EmptyResultDataAccessException e)	{
			throw new ResourceNotFoundException(id); /// tratamento personalizado
			/////e.printStackTrace();
		} catch (DataIntegrityViolationException e) {
			///// exceção especifica de serviço do spring com BD
			////e.printStackTrace();
			throw new DatabaseException(e.getMessage()) ;
		}
	}
	
	@SuppressWarnings("deprecation")
	public User update(Long id, User obj) {
		try {
		User entity = repository.getOne(id);
		updateData(entity,obj);
		return repository.save(entity);
		} catch(EntityNotFoundException e ) {
			/////excecao javax JPA
			throw new ResourceNotFoundException(id);
		}
		
	}

	private void updateData(User entity, User obj) {
	   entity.setName(obj.getName());
	   entity.setEmail(obj.getEmail());
	   entity.setPhone(obj.getPhone());
	   		
	}
}
