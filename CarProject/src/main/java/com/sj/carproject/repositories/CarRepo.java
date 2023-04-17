package com.sj.carproject.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sj.carproject.models.Car;

@Repository
public interface CarRepo extends CrudRepository<Car, Long>{

	List<Car> findAll();
	
	Optional<Car> findById(Long id);
	
	void deleteById(Long id);

}
