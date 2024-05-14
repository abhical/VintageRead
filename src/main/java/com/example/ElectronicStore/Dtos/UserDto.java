package com.example.ElectronicStore.Dtos;

import com.example.ElectronicStore.Validations.ValidUserImageName;

import jakarta.validation.constraints.*;
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

public class UserDto {

	private String userId;

	@Size(min = 2, max = 30, message = "Not a Valid User Name")
	private String userName;

	// @Email(message="Email should be valid ")-> Not giving us the accurate results
	@NotBlank(message = "Email is required")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "Invalid email format")
	private String userEmail;

	@NotNull(message = "Password can't be null")
	private String userPassword;

	private String userGender;

	 @ValidUserImageName(message = "User image name must be a valid image file name with extension jpg, jpeg, png")
	private String userImageName;

}