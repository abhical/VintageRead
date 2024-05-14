package com.example.ElectronicStore.Dtos;

import org.springframework.http.HttpStatus;
import lombok.*;

//Beautiful application of lombak -> Automatically create all getter and setters
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ApiResponseMessage {
	
	
	
	private String message;
	private boolean success;
	private HttpStatus status;
	

}
