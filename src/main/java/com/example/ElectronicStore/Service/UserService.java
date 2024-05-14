package com.example.ElectronicStore.Service;

import java.util.List;

import com.example.ElectronicStore.Dtos.PageableResponse;
import com.example.ElectronicStore.Dtos.UserDto;

public interface UserService {
	
	//create
	public UserDto createUser(UserDto user);
	
	//update
	public UserDto updateUser(String userId, UserDto user);
	
	//delete
	public void deleteUser(String userId);
	
	//get all users
	public PageableResponse<UserDto> getAllUser(int pageNumber,int pageSize,String sortBy, String sortDir);
	
	
	//get single user by id
	public UserDto getSingleUserById(String userId);
	
	//get single user by email
	public UserDto getSingleUserByEmail(String userEmail);
	
	//search user
	public List<UserDto> searchUser(String keyword);
	//Any Other feature you can think of

}
