package com.example.ElectronicStore.Service;

import org.springframework.data.domain.Pageable;

import com.example.ElectronicStore.Dtos.PageableResponse;
import com.example.ElectronicStore.Dtos.ProductDTO;

public interface ProductService {

	public ProductDTO createProduct(ProductDTO ProductDto);

	public ProductDTO updateProduct(String productId, ProductDTO ProductDto);

	public void deleteProduct(String productId);

	public PageableResponse<ProductDTO> getAllProduct(int pageNumber, int pageSize, String sortBy, String sortDir);

	public ProductDTO getProductById(String ProductId);

	PageableResponse<ProductDTO> findByProductTitleContaining(String keyword,int pageNumber, int pageSize, String sortBy, String sortDir);

	PageableResponse<ProductDTO> findByIsliveTrue(int pageNumber, int pageSize, String sortBy, String sortDir);
	
	public ProductDTO createProductwithCategory(ProductDTO ProductDto, String categoryId);
	
	public ProductDTO updateProductWithCategoryId(String productId,String categoryId);

	PageableResponse<ProductDTO> getAllProductWithCategoryId(String categoryId, int pageNumber, int pageSize,
			String sortBy, String sortDir);
	

}
