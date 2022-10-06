package com.CarPolicy.CarPolicy.Car;


import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import com.CarPolicy.CarPolicy.controller.CarController;
import com.CarPolicy.CarPolicy.dtos.CarDto;
import com.CarPolicy.CarPolicy.dtos.CustomerDto;
import com.CarPolicy.CarPolicy.dtos.ModelDto;
import com.CarPolicy.CarPolicy.entities.Customer;
import com.CarPolicy.CarPolicy.entities.Model;
import com.CarPolicy.CarPolicy.repositories.ModelRepository;
import com.CarPolicy.CarPolicy.services.CarService;
import com.CarPolicy.CarPolicy.services.ModelService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;





@WebMvcTest(CarController.class)
public class CarControllerTest {

	@MockBean
	private CarService carService;
	
    @MockBean
	private ModelService modelService;
	
	@Autowired
	private MockMvc mockMvc;
	
	
//	 @Test
//	 @WithMockUser(username = "test3@hotmail.com", authorities = { "ROLE_ADMIN"})
//	 public void testAddCar() throws Exception
//	 {
//		 CarDto carDto = new CarDto();
//		 carDto.setLicensePlate("adadadadadadad");
//		 carDto.setColor("mavi");
//		 carDto.setModelId(1);
//		 carDto.setManufacturingYear(2000);
//		 carDto.setMileage(25000);
//
//		 
//		 //CarDto resultCarDto = carService.addCar(carDto);
//		 
//		 ModelDto modelDto = modelService.getById(1);
//		 System.out.println(modelDto.getId());
//		 
//		 mockMvc.perform(MockMvcRequestBuilders.post("/cars/add")
//				    .param("licensePlate", "adadadadadad")
//				    .param("color","adadadadad")
//		            .with(csrf()))
//		            .andExpect(MockMvcResultMatchers.status().isOk());
//	 }
	
	 @Test
	 @WithMockUser(username = "test3@hotmail.com", authorities = { "ROLE_ADMIN"})
	 public void testGetAllCars() throws Exception
	 {
		 CustomerDto customerDto = new CustomerDto(19,"Customer");	
		 ModelDto modelDto = new ModelDto(1,"Model");
		 
		 List<CarDto> carDtos = new ArrayList<>();
		 carDtos.add(new CarDto(1,"licensePlate",2000,25000,"mavi",customerDto,modelDto));
		 carDtos.add(new CarDto(2,"licensePlate",2000,25000,"mavi",customerDto,modelDto));
		 carDtos.add(new CarDto(3,"licensePlate",2000,25000,"mavi",customerDto,modelDto));
		 
		Mockito.when(carService.getAllCars()).thenReturn(carDtos);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/cars/getAll"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("car/index"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("cars"))
				.andExpect(MockMvcResultMatchers.model().attribute("cars",carDtos));
		

		   
	     Mockito.verify(carService,times(1)).getAllCars();
 
	 }
	 
	 @Test
	 @WithMockUser(username = "test3@hotmail.com", authorities = { "ROLE_ADMIN"})
	 public void testGetCarById() throws Exception
	 {
		 CustomerDto customerDto = new CustomerDto(19,"Customer");	
		 ModelDto modelDto = new ModelDto(1,"Model");
		 
		 CarDto carDto = new CarDto(1,"licensePlate",2000,25000,"mavi",customerDto,modelDto);
		 
		 Mockito.when(carService.getCarById(1)).thenReturn(carDto);
		 
		 mockMvc.perform(MockMvcRequestBuilders.get("/cars/getById/1"))
		        .andExpect(MockMvcResultMatchers.status().isOk())
		        .andExpect(MockMvcResultMatchers.view().name("car/detail"))
		        .andExpect(MockMvcResultMatchers.model().attributeExists("car"))
		        .andExpect(MockMvcResultMatchers.model().attribute("car",carDto));
		 
		 Mockito.verify(carService,times(1)).getCarById(1);
				
	 }
}
