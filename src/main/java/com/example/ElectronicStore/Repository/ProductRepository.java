package com.example.ElectronicStore.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ElectronicStore.Entities.Category;
import com.example.ElectronicStore.Entities.Product;

public interface ProductRepository extends JpaRepository<Product,String>{
	
	Page <Product> findByProductTitleContaining(String keyword,Pageable pageable);
	
	Page <Product> findByIsliveTrue(Pageable pageable);
	
	Page <Product> findByCategory(String categoryId, Pageable pageable);
	
	Page<Product> findByCategory(Category category, Pageable pageable);


}