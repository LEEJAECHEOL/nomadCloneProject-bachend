package com.cos.oauth2jwt.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findByUsername(String username);

}
