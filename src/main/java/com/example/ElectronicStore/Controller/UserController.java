package com.example.ElectronicStore.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ElectronicStore.Dtos.ApiResponseMessage;
import com.example.ElectronicStore.Dtos.PageableResponse;
import com.example.ElectronicStore.Dtos.UserDto;
import com.example.ElectronicStore.Service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")

public class UserController {

	@Autowired
	private UserService userService;

	// create
	@PostMapping()
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userdto) {
		UserDto user = userService.createUser(userdto);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

	// update
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@PathVariable("userId") String userId,@Valid @RequestBody UserDto userdto) {
		UserDto user = userService.updateUser(userId, userdto);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	// delete user
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponseMessage> userDeleted(@PathVariable("userId") String userId) {
		userService.deleteUser(userId);
		ApiResponseMessage message=ApiResponseMessage.builder().message("User Deleted Successfuly").success(true).status(HttpStatus.OK).build();
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	// get all user
	@GetMapping()
	public ResponseEntity<PageableResponse<UserDto>> getAllUser(
			@RequestParam(value="pageNumber", defaultValue="0",required=false) int pageNumber,
			@RequestParam(value="pageSize", defaultValue="10", required=false) int pageSize,
			@RequestParam(value="sortBy", defaultValue="userName", required=false) String sortBy,
			@RequestParam(value="sortDir", defaultValue="asc", required=false) String sortDir
			) {
		PageableResponse<UserDto> userList = userService.getAllUser(pageNumber-1,pageSize,sortBy,sortDir);
		return new ResponseEntity<>(userList, HttpStatus.OK);
	}

	// get single user by Id
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUserById(@PathVariable("userId") String userId) {
		UserDto user = userService.getSingleUserById(userId);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	// get single user by email id
	@GetMapping("/email/{userEmail}")
	public ResponseEntity<UserDto> getUserByEmail(@PathVariable("userEmail") String userEmail) {
		UserDto user = userService.getSingleUserByEmail(userEmail);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping("/search/{keyword}")
	public List<UserDto> searchUser(@PathVariable String keyword) {
		List<UserDto> userList = userService.searchUser(keyword);
		return userList;

	}

}
