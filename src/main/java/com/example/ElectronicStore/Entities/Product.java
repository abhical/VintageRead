package com.example.ElectronicStore.Entities;

import java.util.Date;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder



@Entity
@Table(name="Products")
public class Product {
	
	@Id
	private String productId;
	
	@Column(name="product_title", length=30)
	private String productTitle;
	
	@Column(name="product_description", length=10000)
	private String productDescription;
	
	@Column(name="product_price")
	private double productPrice;
	
	@Column(name="product_quantity")
	private int productquantity;
	
	@Column(name="product_added_date")
	private Date productAddedDate;
	
	@Column(name="is_product_live")
	private boolean islive;
	
	@Column(name="is_product_in_stock")
	private boolean stock;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="Category")
	private Category category;
	

}
