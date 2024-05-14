package com.example.ElectronicStore.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ElectronicStore.Entities.User;

public interface UserRepository extends JpaRepository<User,String> {
	
	Optional <User> findByUserEmail(String userEmail);
	
	List<User> findByUserNameContaining(String keyword);

}
