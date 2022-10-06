package com.CarPolicy.CarPolicy.dtos;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {

    private int id;

	@NotEmpty()
    @Size(min=7, max=15)
	private String licensePlate;
	
    @NotNull()
    @Min(value = 1950)
    @Max(value = 2022)
	private int manufacturingYear;
	
    @NotNull()
    @Min(value = 0)
	private float mileage;
	
    @NotEmpty()
    @Size(min=3, max=10)
	private String color;
    
	private List<CarAccidentDto> carAccidents;
	
	private CustomerDto customer;
	
	private List<CarPolicyDto> policies;
	
	private ModelDto model;
	
	private int modelId;
	
	private String sortBy;

	public CarDto(String licensePlate,
			int manufacturingYear,float mileage,
			String color,int modelId) {
		super();
		this.licensePlate = licensePlate;
		this.manufacturingYear = manufacturingYear;
		this.mileage = mileage;
		this.color = color;
		this.modelId = modelId;
	}
	
	public CarDto(String licensePlate,
			int manufacturingYear,float mileage,
			String color, CustomerDto customer, ModelDto model, int modelId) {
		super();
		this.licensePlate = licensePlate;
		this.manufacturingYear = manufacturingYear;
		this.mileage = mileage;
		this.color = color;
		this.customer = customer;
		this.model = model;
		this.modelId = modelId;
	}
	
	public CarDto(int id,String licensePlate,
			int manufacturingYear,float mileage,
			String color, CustomerDto customer, ModelDto model) {
		super();
		this.id = id;
		this.licensePlate = licensePlate;
		this.manufacturingYear = manufacturingYear;
		this.mileage = mileage;
		this.color = color;
		this.customer = customer;
		this.model = model;
	}
	
}
