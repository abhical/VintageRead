package com.example.ElectronicStore.Service.Impl;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.ElectronicStore.Dtos.PageableResponse;
import com.example.ElectronicStore.Dtos.ProductDTO;
import com.example.ElectronicStore.Entities.Category;
import com.example.ElectronicStore.Entities.Product;
import com.example.ElectronicStore.Exception.ResourceNotFoundException;
import com.example.ElectronicStore.Helper.GeneratePageableResponse;
import com.example.ElectronicStore.Repository.CategoryRepository;
import com.example.ElectronicStore.Repository.ProductRepository;
import com.example.ElectronicStore.Service.ProductService;
import com.example.ElectronicStore.config.UniqueIdGenerator;

import lombok.experimental.Helper;

@Service

public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private UniqueIdGenerator uniqueIdGen;

	@Autowired
	private CategoryRepository categoryRepo;

	@Override
	public ProductDTO createProduct(ProductDTO ProductDto) {
		Product productData = mapper.map(ProductDto, Product.class);
		productData.setProductAddedDate(new Date());
		String uniqueId = uniqueIdGen.generateUniqueId();
		productData.setProductId(uniqueId);
		Product savedProduct = productRepo.save(productData);
		return mapper.map(savedProduct, ProductDTO.class);
	}

	@Override
	public ProductDTO updateProduct(String productId, ProductDTO ProductDto) {
		Product productData = productRepo.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product Not Found"));
		productData.setProductTitle(ProductDto.getProductTitle());
		productData.setProductDescription(ProductDto.getProductDescription());
		productData.setProductPrice(ProductDto.getProductPrice());
		productData.setProductquantity(ProductDto.getProductquantity());
		productData.setProductAddedDate(new Date());
		productData.setIslive(ProductDto.isIslive());
		productData.setStock(ProductDto.isStock());

		// saving data
		Product savedUser = productRepo.save(productData);
		return mapper.map(savedUser, ProductDTO.class);
	}

	@Override
	public void deleteProduct(String productId) {
		Product productData = productRepo.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product Not Found"));
		productRepo.delete(productData);
	}

	@Override
	public PageableResponse<ProductDTO> getAllProduct(int pageNumber, int pageSize, String sortBy, String sortDir) {
		Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		Page<Product> page = productRepo.findAll(pageable);
		PageableResponse<ProductDTO> res = GeneratePageableResponse.getPageableResponse(page, ProductDTO.class);
		return res;
	}

	@Override
	public ProductDTO getProductById(String ProductId) {
		Product productData = productRepo.findById(ProductId)
				.orElseThrow(() -> new ResourceNotFoundException("Product Not Found"));
		return mapper.map(productData, ProductDTO.class);
	}

	@Override
	public PageableResponse<ProductDTO> findByProductTitleContaining(String keyword, int pageNumber, int pageSize,
			String sortBy, String sortDir) {
		Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		Page<Product> page = productRepo.findByProductTitleContaining(keyword, pageable);
		PageableResponse<ProductDTO> res = GeneratePageableResponse.getPageableResponse(page, ProductDTO.class);
		return res;
	}

	@Override
	public PageableResponse<ProductDTO> findByIsliveTrue(int pageNumber, int pageSize, String sortBy, String sortDir) {
		Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		Page<Product> page = productRepo.findByIsliveTrue(pageable);
		PageableResponse<ProductDTO> res = GeneratePageableResponse.getPageableResponse(page, ProductDTO.class);
		return res;
	}

	@Override
	public ProductDTO createProductwithCategory(ProductDTO ProductDto, String categoryId) {
		Category categoryData = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category Not Found"));
		Product productData = mapper.map(ProductDto, Product.class);
		productData.setProductAddedDate(new Date());
		String uniqueId = uniqueIdGen.generateUniqueId();
		productData.setProductId(uniqueId);
		productData.setCategory(categoryData);
		Product savedProduct = productRepo.save(productData);
		return mapper.map(savedProduct, ProductDTO.class);
	}

	@Override
	public ProductDTO updateProductWithCategoryId(String productId, String categoryId) {
		Category categoryData = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category Not Found"));
		Product productData = productRepo.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product Not Found"));
		productData.setCategory(categoryData);
		// saving data
		Product savedUser = productRepo.save(productData);
		return mapper.map(savedUser, ProductDTO.class);
	}

	@Override
	public PageableResponse<ProductDTO> getAllProductWithCategoryId(String categoryId, int pageNumber, int pageSize,
			String sortBy, String sortDir) {
		Category categoryData = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category Not Found"));
		Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		Page<Product> page = productRepo.findByCategory(categoryData, pageable);
		PageableResponse<ProductDTO> res = GeneratePageableResponse.getPageableResponse(page, ProductDTO.class);
		return res;
	}

}
