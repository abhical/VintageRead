package com.example.ElectronicStore.Service.Impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.ElectronicStore.Dtos.PageableResponse;
import com.example.ElectronicStore.Dtos.UserDto;
import com.example.ElectronicStore.Entities.User;
import com.example.ElectronicStore.Exception.ResourceNotFoundException;
import com.example.ElectronicStore.Helper.GeneratePageableResponse;
import com.example.ElectronicStore.Repository.UserRepository;
import com.example.ElectronicStore.Service.UserService;
import com.example.ElectronicStore.config.UniqueIdGenerator;

@Service

public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private UniqueIdGenerator uniqueIdGen;

	@Override
	public UserDto createUser(UserDto userdto) {
		// Generate unique ID in String format
		String uniqueId = uniqueIdGen.generateUniqueId();
		userdto.setUserId(uniqueId);

		User user = DtoToEntity(userdto); // UserDto -> Entity : To perform database
		User savedUser = userRepo.save(user);
		UserDto newdto = EntityToDto(savedUser); // Entity-> UserDto : To return the
		return newdto;
	}

	@Override
	public UserDto updateUser(String userId, UserDto user) {
		// TODO Auto-generated method stub
		User repoUser = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
		repoUser.setUserName(user.getUserName());
		repoUser.setUserEmail(user.getUserEmail());
		repoUser.setUserGender(user.getUserGender());
		repoUser.setUserPassword(user.getUserPassword());
		repoUser.setUserImageName(user.getUserImageName());

		// saving data
		User savedUser = userRepo.save(repoUser);
		UserDto savedDto = EntityToDto(savedUser);
		return savedDto;
	}

	@Override
	public void deleteUser(String userId) {
		User repoUser = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User Not Found: Can not Delete"));
		userRepo.delete(repoUser);

	}

	@Override
	public PageableResponse<UserDto> getAllUser(int pageNumber,int pageSize, String sortBy, String sortDir) {
		Sort sort=(sortDir.equalsIgnoreCase("desc"))? (Sort.by(sortBy).descending()): (Sort.by(sortBy).ascending());
		Pageable pageable=PageRequest.of(pageNumber,pageSize,sort);
		Page<User>page=userRepo.findAll(pageable);
		PageableResponse<UserDto> res=GeneratePageableResponse.getPageableResponse(page, UserDto.class);
		return res;
	}

	@Override
	public UserDto getSingleUserById(String userId) {
		User repoUser = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
		UserDto savedDto = EntityToDto(repoUser);
		return savedDto;
	}

	@Override
	public UserDto getSingleUserByEmail(String userEmail) {
		User repoUser = userRepo.findByUserEmail(userEmail)
				.orElseThrow(() -> new ResourceNotFoundException("User Email Id is not found"));
		UserDto savedDto = EntityToDto(repoUser);
		return savedDto;

	}

	@Override
	public List<UserDto> searchUser(String keyword) {
		// TODO Auto-generated method stub
		List<User> userList = userRepo.findByUserNameContaining(keyword);
		List<UserDto> dtoList = userList.stream().map(user -> EntityToDto(user)).collect(Collectors.toList());
		return dtoList;
	}

	public User DtoToEntity(UserDto userDto) {
		User user = User.builder().userId(userDto.getUserId()).userName(userDto.getUserName())
				.userEmail(userDto.getUserEmail())
				// Password is not included in the DTO to prevent exposure
				.userPassword(userDto.getUserPassword()).userGender(userDto.getUserGender())
				.userImageName(userDto.getUserImageName()).build();
		return user;

		// return mapper.map(userDto, User.class);
	}

	private UserDto EntityToDto(User savedUser) {
		UserDto userDto = UserDto.builder().userId(savedUser.getUserId()).userName(savedUser.getUserName())
				.userEmail(savedUser.getUserEmail()).userGender(savedUser.getUserGender())
				.userImageName(savedUser.getUserImageName()).build();
		// return mapper.map(savedUser, UserDto.class);
		return userDto;
	}

}
