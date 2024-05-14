package com.example.ElectronicStore.Dtos;

import java.util.Date;

import com.example.ElectronicStore.Entities.Category;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ProductDTO {

	private String productId;

	@Size(min = 2, max = 30, message = "Not a Valid Product Name")
	private String productTitle;

	@NotNull(message = "Product Description can't be null")
	private String productDescription;

	@NotNull(message = "Product Price can't be null")
	private double productPrice;

	private int productquantity;

	private Date productAddedDate;

	private boolean islive;

	private boolean stock;
	
	private CategoryDTO category;

}
