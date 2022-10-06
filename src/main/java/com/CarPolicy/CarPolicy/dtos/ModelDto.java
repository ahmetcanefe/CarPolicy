package com.CarPolicy.CarPolicy.dtos;

import javax.validation.constraints.NotEmpty;
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
public class ModelDto {

	private int id;
	
	@NotEmpty()
    @Size(min=3, max=30)
	private String name;

}
