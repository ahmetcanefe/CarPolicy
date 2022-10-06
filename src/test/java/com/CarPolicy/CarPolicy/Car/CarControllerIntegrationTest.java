package com.CarPolicy.CarPolicy.Car;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import javax.persistence.EntityManager;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.CarPolicy.CarPolicy.controller.CarController;
import com.CarPolicy.CarPolicy.dtos.CarDto;
import com.CarPolicy.CarPolicy.entities.Car;
import com.CarPolicy.CarPolicy.entities.Customer;
import com.CarPolicy.CarPolicy.entities.Model;
import com.CarPolicy.CarPolicy.repositories.CarRepository;
import com.CarPolicy.CarPolicy.services.CarService;
import com.jayway.jsonpath.JsonPath;



@SpringBootTest()
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration
@EnableWebMvc
public class CarControllerIntegrationTest {
	
	 @Autowired
	 private MockMvc mockMvc;
	 
	 @Autowired
	 private EntityManager entityManager;
	 
	 
	 @Test
	 @WithMockUser(username = "admin@hotmail.com", authorities = { "ROLE_ADMIN"})
	 public void testGetCar() throws Exception
	 {	 
	
		       mockMvc.perform(MockMvcRequestBuilders.get("/cars/getById/29"))
		               .andExpect(MockMvcResultMatchers.status().isOk())
		               .andExpect(MockMvcResultMatchers.view().name("car/detail"))
		               .andExpect(MockMvcResultMatchers.model().attributeExists("car"));
		
	 }
	 
	 @Test
	 @WithMockUser(username = "admin@hotmail.com", authorities = { "ROLE_ADMIN"})
	 public void testAddCarGet() throws Exception
	 {
		 mockMvc.perform(MockMvcRequestBuilders.get("/cars/add"))
              .andExpect(MockMvcResultMatchers.status().isOk())
              .andExpect(MockMvcResultMatchers.view().name("car/add"))
			  .andExpect(MockMvcResultMatchers.model().attributeExists("car"))
			  .andExpect(MockMvcResultMatchers.model().attributeExists("models"));
	 }
	 
	 @Test
	 @WithMockUser(username = "admin@hotmail.com", authorities = { "ROLE_ADMIN"})
	 public void testAddCarPost() throws Exception
	 {	 	 
		 mockMvc.perform(MockMvcRequestBuilders.post("/cars/add")
		            .param("licensePlate", "34EFaE4345")
		            .param("color", "MAVi")
		            .param("manufacturingYear", "2015")
		            .param("mileage", "25000")
		            .param("modelId", "1"))
		            .andExpect(MockMvcResultMatchers.status().is3xxRedirection());   
	 }
	 
	 @Test
	 @WithMockUser(username = "admin@hotmail.com", authorities = { "ROLE_ADMIN"})
	 public void testUpdateCarGet() throws Exception
	 {
		 mockMvc.perform(MockMvcRequestBuilders.get("/cars/update/23"))
              .andExpect(MockMvcResultMatchers.status().isOk())
              .andExpect(MockMvcResultMatchers.view().name("car/update"))
			  .andExpect(MockMvcResultMatchers.model().attributeExists("models"))
			  .andExpect(MockMvcResultMatchers.model().attributeExists("car"));	
	 }
	 
	 @Test
	 @WithMockUser(username = "admin@hotmail.com", authorities = { "ROLE_ADMIN"})
	 public void testUpdateCarPost() throws Exception
	 {	 
		 mockMvc.perform(MockMvcRequestBuilders.post("/cars/update/23")
				    .param("licensePlate", "test-34EFE4345")
		            .param("color", "MAVi")
		            .param("manufacturingYear", "2015")
		            .param("mileage", "25000")
		            .param("modelId", "1"))
              .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
		 
	 }
	 
	 @Test
	 @WithMockUser(username = "admin@hotmail.com", authorities = { "ROLE_ADMIN"})
	 public void testDeleteCar() throws Exception
	 {
		 mockMvc.perform(MockMvcRequestBuilders.get("/cars/delete/23"))
              .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
	 }
}










