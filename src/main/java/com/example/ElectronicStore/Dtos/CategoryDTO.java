package com.example.ElectronicStore.Dtos;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CategoryDTO {
	
	private String categoryId;

	@NotBlank
	@Size(min = 2, max = 30, message = "Not a Valid Category Name")
	private String categoryName;
	
	private String categoryDescription;
	
	private String coverImage;

}
