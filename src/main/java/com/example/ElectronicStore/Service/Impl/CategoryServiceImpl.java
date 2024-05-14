package com.example.ElectronicStore.Service.Impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.ElectronicStore.Dtos.CategoryDTO;
import com.example.ElectronicStore.Dtos.PageableResponse;
import com.example.ElectronicStore.Dtos.UserDto;
import com.example.ElectronicStore.Entities.Category;
import com.example.ElectronicStore.Entities.User;
import com.example.ElectronicStore.Exception.ResourceNotFoundException;
import com.example.ElectronicStore.Helper.GeneratePageableResponse;
import com.example.ElectronicStore.Repository.CategoryRepository;
import com.example.ElectronicStore.Service.CategoryService;
import com.example.ElectronicStore.config.UniqueIdGenerator;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private UniqueIdGenerator uniqueIdGen;

	@Override
	public CategoryDTO addCategory(CategoryDTO categoryDto) {
		/*
		Category categoryData = DTOtoEntity(categoryDto);
		Category savedCategory = categoryRepository.save(categoryData);
		CategoryDTO newcategoryDto = EntitytoDTO(savedCategory);
		return newcategoryDto;
		*/
		//Using ModelMapper to map the entity and dto and vice-versa
		Category categoryData=mapper.map(categoryDto, Category.class);
		String uniqueId = uniqueIdGen.generateUniqueId();
		categoryData.setCategoryId(uniqueId);
		Category savedCategory=categoryRepository.save(categoryData);
		return mapper.map(savedCategory, CategoryDTO.class);	
	}
	
	@Override
	public PageableResponse<CategoryDTO> getAllCategories(int pageNumber,int pageSize,String sortBy,String sortDir) {
		Sort sort=(sortDir.equalsIgnoreCase("desc"))? (Sort.by(sortBy).descending()): (Sort.by(sortBy).ascending());
		Pageable pageable=PageRequest.of(pageNumber, pageSize,sort);
		Page<Category>page=categoryRepository.findAll(pageable);
		PageableResponse<CategoryDTO> result=GeneratePageableResponse.getPageableResponse(page,CategoryDTO.class);
		return result;
	}
	

	@Override
	public CategoryDTO getCategoryById(String categoryId) {
		Category savedCategory=categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
		CategoryDTO categoryDto=mapper.map(savedCategory, CategoryDTO.class);
		return categoryDto;
	}
	
	public void deleteCategoryById(String categoryId) {
		Category repoCategory = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("User Not Found: Can not Delete"));
		categoryRepository.delete(repoCategory);
	}


	private Category DTOtoEntity(CategoryDTO categoryDto) {
		Category category = Category.builder().categoryId(categoryDto.getCategoryId())
				.categoryName(categoryDto.getCategoryName()).categoryDescription(categoryDto.getCategoryDescription())
				.coverImage(categoryDto.getCoverImage())
				.build();
		return category;
	}

	private CategoryDTO EntitytoDTO(Category savedCategory) {
		CategoryDTO categoryDto = CategoryDTO.builder().categoryId(savedCategory.getCategoryId())
				.categoryName(savedCategory.getCategoryName())
				.categoryDescription(savedCategory.getCategoryDescription())
				.coverImage(savedCategory.getCoverImage())
				.build();
		return categoryDto;
	}

	

}