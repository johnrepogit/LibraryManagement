package com.john.library.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.john.library.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	 User findByEmail(String email);
	 User findByEmailAndPassword(String email, String password);
	 User findByIdAndRole(Long userId, String role);
}
