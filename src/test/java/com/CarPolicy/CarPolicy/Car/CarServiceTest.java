package com.CarPolicy.CarPolicy.Car;

import static org.mockito.Mockito.times;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.CarPolicy.CarPolicy.dtos.CarDto;
import com.CarPolicy.CarPolicy.dtos.CustomerDto;
import com.CarPolicy.CarPolicy.dtos.ModelDto;
import com.CarPolicy.CarPolicy.entities.Car;
import com.CarPolicy.CarPolicy.entities.Customer;
import com.CarPolicy.CarPolicy.entities.Model;
import com.CarPolicy.CarPolicy.repositories.CarPolicyRepository;
import com.CarPolicy.CarPolicy.repositories.CarRepository;
import com.CarPolicy.CarPolicy.repositories.CustomerRepository;
import com.CarPolicy.CarPolicy.repositories.ModelRepository;
import com.CarPolicy.CarPolicy.services.CarService;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CarServiceTest {

	@InjectMocks
	private CarService carService;
	
	@Autowired
	private EntityManager entityManager;
	
	private ModelMapper modelMapper;	
	private CarRepository carRepository;
	private CustomerRepository customerRepository;
	private CarPolicyRepository carPolicyRepository;
	private ModelRepository modelRepository;	
	
	@BeforeEach
	public void setUp() throws Exception
	{
		carRepository = Mockito.mock(CarRepository.class);
		customerRepository = Mockito.mock(CustomerRepository.class);
		carPolicyRepository = Mockito.mock(CarPolicyRepository.class);
		modelRepository = Mockito.mock(ModelRepository.class);
		modelMapper = Mockito.mock(ModelMapper.class);
		
		
		
	   	carService = new CarService(carRepository, modelRepository, modelMapper, customerRepository, carPolicyRepository);
	}	
	
	@Test
	@WithMockUser(username = "admin@hotmail.com", authorities = { "ROLE_ADMIN"})
	public void testAddCar()
	{
		Customer customer = entityManager.find(Customer.class, 6);
		Model model = entityManager.find(Model.class, 1);	
		CustomerDto customerDto = new CustomerDto(19,"Customer");	
		ModelDto modelDto = new ModelDto(1,"Model");

		CarDto carDto = new CarDto("licensePlate",2000,25000,"mavi",1);
		
		Car newCar = new Car("licensePlate",2000,(float)25000,"Siyah",customer,model);
		Car addedCar = new Car(46,"licensePlate",2000,(float)2500,"Siyah",customer,model);
		CarDto addedCarDto = new CarDto(36,"licensePlate",2000,25000,"mavi",customerDto,modelDto);
		
		
		
	    Mockito.when(customerRepository.findByEmail("admin@hotmail.com")).thenReturn(customer);
		Mockito.when(modelRepository.getById(1)).thenReturn(model);
		Mockito.when(carRepository.save(newCar)).thenReturn(addedCar);
		Mockito.when(modelMapper.map(carDto, Car.class)).thenReturn(newCar);
		Mockito.when(modelMapper.map(addedCar, CarDto.class)).thenReturn(addedCarDto);
		
		
		CarDto returnAddedCarDto = carService.addCar(carDto);
		
		Assertions.assertThat(returnAddedCarDto.getLicensePlate()).isEqualTo(carDto.getLicensePlate());
		Assertions.assertThat(returnAddedCarDto).isEqualTo(addedCarDto);
		
		Mockito.verify(customerRepository,times(1)).findByEmail(customer.getEmail());
		Mockito.verify(modelRepository,times(1)).getById(model.getId());
		Mockito.verify(carRepository,times(1)).save(newCar);
		Mockito.verify(modelMapper,times(1)).map(carDto,Car.class);
		Mockito.verify(modelMapper,times(1)).map(addedCar,CarDto.class);
	}
	
	@Test
	@WithMockUser(username = "admin@hotmail.com", authorities = { "ROLE_ADMIN"})
	public void testUpdateCar()
	{
		int carId=3;
		
		Customer customer = entityManager.find(Customer.class, 6);
		Model model = entityManager.find(Model.class, 1);
		CustomerDto customerDto = new CustomerDto(19,"Customer");	
		ModelDto modelDto = new ModelDto(1,"Model");
		
		CarDto carDto = new CarDto("licensePlate",2000,(float)25000,"mavi",1);
		
		Car foundCar = new Car(3,"licensePlate",2000,25000,"mavi",customer,model);	
		CarDto updatedCarDto = new CarDto(3,"licensePlate",2000,(float)25000,"mavi",customerDto,modelDto);
	
		Mockito.when(customerRepository.findByEmail("admin@hotmail.com")).thenReturn(customer);
		Mockito.when(modelRepository.getById(1)).thenReturn(model);
		Mockito.when(carRepository.findById(3)).thenReturn(Optional.of(foundCar));
		Mockito.when(carRepository.save(foundCar)).thenReturn(foundCar);
		Mockito.when(modelMapper.map(foundCar, CarDto.class)).thenReturn(updatedCarDto);
		
		
		CarDto returnUpdatedCarDto = carService.updateCar(carId, carDto);
		 
		Assertions.assertThat(returnUpdatedCarDto.getId()).isEqualTo(carId);
		Assertions.assertThat(returnUpdatedCarDto.getLicensePlate()).isEqualTo(carDto.getLicensePlate());
		
		
		Mockito.verify(customerRepository,times(1)).findByEmail(customer.getEmail());
		Mockito.verify(modelRepository,times(1)).getById(model.getId());
		Mockito.verify(carRepository,times(1)).findById(3);
		Mockito.verify(carRepository,times(1)).save(foundCar);
		Mockito.verify(modelMapper,times(1)).map(foundCar,CarDto.class);	
	}
}
