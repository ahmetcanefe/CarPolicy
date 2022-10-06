package com.CarPolicy.CarPolicy;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.CarPolicy.CarPolicy.entities.Car;
import com.CarPolicy.CarPolicy.entities.Model;
import com.CarPolicy.CarPolicy.entities.Customer;
import com.CarPolicy.CarPolicy.repositories.CarRepository;

//@DataJpaTest
//@AutoConfigureTestDatabase(replace = Replace.NONE)
//@Rollback(false)
//public class CarRepositoryTests {
//
//	@Autowired
//	private CarRepository carRepository;
//	
//	@Autowired
//	private TestEntityManager entityManager;
//	
//	@Test
//	public void testCreateCar()
//	{
//		Model model = entityManager.find(Model.class, 1);
//		Customer customer = entityManager.find(Customer.class, 1);
//		
//		Car car = new Car();
//	    car.setLicensePlate("34fkg67");
//	    car.setManufacturingYear(2015);
//	    car.setMileage(25);
//	    car.setModel(model);
//	    car.setCustomer(customer);
//	    car.setColor("SİYAH");
//	    
//	    Car savedCar = carRepository.save(car);
//	    
//	    Assertions.assertThat(savedCar.getId()).isGreaterThan(0);   
//	}
//	
//	@Test
//	public void testListAllCars()
//	{
//		Iterable<Car> iterableCars = carRepository.findAll();
//		iterableCars.forEach(System.out::println);
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//}
