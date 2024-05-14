package com.example.ElectronicStore.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ElectronicStore.Dtos.ApiResponseMessage;
import com.example.ElectronicStore.Dtos.CategoryDTO;
import com.example.ElectronicStore.Dtos.PageableResponse;
import com.example.ElectronicStore.Service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping()
	public CategoryDTO addCategory( @Valid @RequestBody CategoryDTO categoryDto) {
		CategoryDTO categoryData = categoryService.addCategory(categoryDto);
		return categoryData;
	}

	@GetMapping("/getAllCategories")
	public ResponseEntity<PageableResponse<CategoryDTO>> getCategories(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
			//pageNumber is staring from 0
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = "categoryId", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
		PageableResponse<CategoryDTO> categoryList = categoryService.getAllCategories(pageNumber, pageSize, sortBy,
				sortDir);
		return new ResponseEntity<>(categoryList, HttpStatus.OK);
	}

	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable("categoryId") String categoryId) {
		CategoryDTO category = categoryService.getCategoryById(categoryId);
		return new ResponseEntity<>(category, HttpStatus.OK);
	}
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponseMessage> deleteCategoryById(@PathVariable("categoryId") String categoryId) {
		categoryService.deleteCategoryById(categoryId);
		ApiResponseMessage message=ApiResponseMessage.builder().message("Category Deleted Successfuly").success(true).status(HttpStatus.OK).build();
		return new ResponseEntity<>(message, HttpStatus.OK);
		
	}
	
	

}
