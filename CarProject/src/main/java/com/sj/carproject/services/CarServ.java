package com.sj.carproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sj.carproject.models.Car;
import com.sj.carproject.repositories.CarRepo;

@Service
public class CarServ {

	private final CarRepo carRepo;
	public CarServ (CarRepo carRepo) {
		this.carRepo = carRepo;
	}
	
	
	public List<Car> getAllCars (){
		return carRepo.findAll();
	}
	
	public Car findOneCar(Long id) {
		Optional<Car> optionalCar = carRepo.findById(id);
			if(optionalCar.isPresent()) {
				return optionalCar.get();
			}
			else {
				return null;
			}
	}
	
	public Car saveCar(Car c) {
		return carRepo.save(c);
	}


	public void deleteCarById(Long id) {
		carRepo.deleteById(id);
	}
	
}
