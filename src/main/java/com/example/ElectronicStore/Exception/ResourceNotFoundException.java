package com.example.ElectronicStore.Exception;

public class ResourceNotFoundException extends RuntimeException{
	
	public ResourceNotFoundException(){
		super("Resource Not Found");
	}
	
	public ResourceNotFoundException(String message){
		super(message);
	}
	

}
