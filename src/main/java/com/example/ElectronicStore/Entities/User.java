package com.example.ElectronicStore.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

//Beautiful application of lombak -> Automatically create all getter and setters
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder


@Entity
@Table(name = "users")
public class User {
	@Id
	private String userId;
	
	@Column(name="User_Name")
	private String userName;
	
	@Column(name="User_Email", unique=true)
	private String userEmail;
	
	@Column(name="User_Password", length=30)
	private String userPassword;
	
	@Column(name="User_Gender",length=10)
	private String userGender;
	
	@Column(name="User_Image_Name")
	private String userImageName;
	
	
	

}
