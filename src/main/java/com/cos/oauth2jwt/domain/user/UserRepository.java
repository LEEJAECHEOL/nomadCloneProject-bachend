package com.cos.oauth2jwt.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findByUsername(String username);

	@Modifying
	@Query(value="update user set fileId = ? where id = ?", nativeQuery=true)
	public int updateProfile(long fileId, long id);
	
	@Modifying
	@Query(value="update user set name = ? where id = ?", nativeQuery=true)
	public int updateName(String name, long id);
}
