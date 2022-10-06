package com.CarPolicy.CarPolicy.Car;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.assertj.core.api.Assertions;
import org.hibernate.internal.build.AllowSysOut;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;

import com.CarPolicy.CarPolicy.entities.Car;
import com.CarPolicy.CarPolicy.entities.Customer;
import com.CarPolicy.CarPolicy.entities.Model;
import com.CarPolicy.CarPolicy.entities.PolicyType;
import com.CarPolicy.CarPolicy.repositories.CarRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class CarRepositoryTests {

	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private EntityManager entityManager;
	
//	@Test
//	@WithMockUser(username = "test3@hotmail.com", authorities = { "ROLE_ADMIN"})
//	public void testCreateCar()
//	{
//		Customer customer = entityManager.find(Customer.class, 19);
//		Model model = entityManager.find(Model.class, 1);
//		
//		Car car = new Car();
//		car.setModel(model);
//		car.setCustomer(customer);
//		car.setLicensePlate("test-adajdhkahjdh");
//		car.setColor("test-color");
//		car.setManufacturingYear(2018);
//		car.setMileage(25000);
//		
//		Car savedCar = carRepository.save(car);
//		
//		Assertions.assertThat(savedCar).isNotNull();
//		Assertions.assertThat(savedCar.getCustomer()).isNotNull();
//		Assertions.assertThat(savedCar.getModel()).isNotNull();
//		Assertions.assertThat(savedCar.getId()).isGreaterThan(0);
//	}
	
	@Test
	public void testListAllCars() {
		List<Car> cars = carRepository.findAll();
		
		cars.forEach(System.out::println);
	}
	
	@Test
	public void testGetCar()
	{
		int id = 1;
		Car car = carRepository.findById(id).get();
		System.out.println(car);
		
		Assertions.assertThat(car).isNotNull();
	}
	
	@Test
	public void testUpdateCar()
	{
		int id = 1;
		Car car = carRepository.findById(id).get();
		car.setLicensePlate("test-updated");
		
		carRepository.save(car);
		
		Car updatedCar = entityManager.find(Car.class, id);
		
		Assertions.assertThat(updatedCar.getId()).isEqualTo(id);
		Assertions.assertThat(updatedCar.getLicensePlate()).isEqualTo("test-updated");
		
	}
	
	@Test
	public void deleteCar()
	{
		int id = 1;
		carRepository.deleteById(id);
		
		Optional<Car> result = carRepository.findById(id);
		
		Assertions.assertThat(!result.isPresent());
	}
	
	@Test
	public void testFindByCustomerId()
	{
		Customer customer = entityManager.find(Customer.class, 1);
		
		List<Car> cars = carRepository.findByCustomerId(customer.getId());
	
		Assertions.assertThat(customer).isNotNull();
	    Assertions.assertThat(cars).isNotNull();
	    cars.forEach(System.out::println);
	}
	
	@Test
	public void testExistsCarByCarPolicies_PolicyType_NameAndId()
	{
		PolicyType policyType = entityManager.find(PolicyType.class, 1);
		int carId = 3;
		
		boolean result = carRepository.existsCarByCarPolicies_PolicyType_NameAndId(policyType.getName(), carId);
		
		Assertions.assertThat(result).isTrue();
	}
	
}
