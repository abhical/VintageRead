package com.example.ElectronicStore.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ElectronicStore.Entities.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {
    // Add custom queries if needed
}