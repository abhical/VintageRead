package com.example.ElectronicStore.Service;

import com.example.ElectronicStore.Dtos.CategoryDTO;
import com.example.ElectronicStore.Dtos.PageableResponse;

public interface CategoryService {
    
    CategoryDTO addCategory(CategoryDTO category);
    
    PageableResponse<CategoryDTO> getAllCategories(int pageNumber,int pageSize,String sortBy, String sortDir);
    
    CategoryDTO getCategoryById(String categoryId);
    
    void deleteCategoryById(String categoryId);
}