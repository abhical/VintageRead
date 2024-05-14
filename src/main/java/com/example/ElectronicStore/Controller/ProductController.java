package com.example.ElectronicStore.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ElectronicStore.Dtos.ApiResponseMessage;
import com.example.ElectronicStore.Dtos.PageableResponse;
import com.example.ElectronicStore.Dtos.ProductDTO;
import com.example.ElectronicStore.Service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping("/addProduct")
	public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDto) {
		ProductDTO productData = productService.createProduct(productDto);
		return ResponseEntity.ok(productData);
	}

	@PutMapping("/{productId}")
	public ResponseEntity<ProductDTO> updateProduct(@PathVariable(name = "productId") String productId,
			@RequestBody ProductDTO ProductDto) {
		ProductDTO productData = productService.updateProduct(productId, ProductDto);
		return new ResponseEntity<>(productData, HttpStatus.OK);
	}

	@DeleteMapping("/{productId}")
	public ResponseEntity<ApiResponseMessage> deleteProduct(@PathVariable(name = "productId") String productId) {
		productService.deleteProduct(productId);
		ApiResponseMessage message = ApiResponseMessage.builder().message("Product Deleted Successfuly").success(true)
				.status(HttpStatus.OK).build();
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@GetMapping()
	public ResponseEntity<PageableResponse<ProductDTO>> getAllProduct(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = "productId", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
		PageableResponse<ProductDTO> productList = productService.getAllProduct(pageNumber, pageSize, sortBy, sortDir);
		return new ResponseEntity<>(productList, HttpStatus.OK);
	}

	@GetMapping("/getById")
	public ResponseEntity<ProductDTO> getProductById(@RequestParam String ProductId) {
		ProductDTO productData = productService.getProductById(ProductId);
		return new ResponseEntity<>(productData, HttpStatus.OK);
	}

	@GetMapping("/getByTitleContainingKeyword")
	public ResponseEntity<PageableResponse<ProductDTO>> getAllProductContainingTitleKeyword(
			@RequestParam(value = "keyword", required = true) String keyword,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = "productId", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
		PageableResponse<ProductDTO> productList = productService.findByProductTitleContaining(keyword, pageNumber,
				pageSize, sortBy, sortDir);
		return new ResponseEntity<>(productList, HttpStatus.OK);
	}

	@GetMapping("/getByProductLive")
	public ResponseEntity<PageableResponse<ProductDTO>> getAllProductIsLiveTrue(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = "productId", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
		PageableResponse<ProductDTO> productList = productService.findByIsliveTrue(pageNumber, pageSize, sortBy,
				sortDir);
		return new ResponseEntity<>(productList, HttpStatus.OK);
	}

	@PostMapping("/addProduct/{categoryId}")
	public ResponseEntity<ProductDTO> createProductWithCategory(@Valid @RequestBody ProductDTO productDto,
			@PathVariable(name = "categoryId") String categoryId) {

		ProductDTO productData = productService.createProductwithCategory(productDto, categoryId);
		return ResponseEntity.ok(productData);
	}

	@PutMapping("/{productIdWithCategory}/{categoryId}")
	public ResponseEntity<ProductDTO> updateProductWithCategoryId(
			@PathVariable(name = "productIdWithCategory") String productId,
			@PathVariable(name = "categoryId") String categoryId)
	{
		ProductDTO productData=productService.updateProductWithCategoryId(productId,categoryId);
		return new ResponseEntity<>(productData, HttpStatus.OK);
	}
	
	@GetMapping("/getByCategoryId/{categoryId}")
	public ResponseEntity<PageableResponse<ProductDTO>> getAllProductWithCategoryId(
	        @PathVariable(name = "categoryId") String categoryId,
	        @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = "productId", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
	    PageableResponse<ProductDTO> productList = productService.getAllProductWithCategoryId(categoryId, pageNumber, pageSize,sortBy,sortDir);
	    return new ResponseEntity<>(productList, HttpStatus.OK);
	}
}
