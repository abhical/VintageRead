package com.example.ElectronicStore.Entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Beautiful application of lombak -> Automatically create all getter and setters
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder


@Entity
@Table(name = "categories")
public class Category {
	@Id
	private String categoryId;
	
	@Column(name="Category_Name")
	private String categoryName;
	
	@Column(name="Category_Description")
	private String categoryDescription;
	
	@Column(name="coverImage")
	private String coverImage;
	
	@OneToMany(mappedBy="category", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Product> products=new ArrayList<>();

}
