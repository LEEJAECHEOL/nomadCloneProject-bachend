package com.cos.oauth2jwt.domain.pay;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PayRepository extends JpaRepository<Pay, Long> {

	@Query(value="SELECT * FROM pay where userId = ?", nativeQuery=true)
	public List<Pay> findUserId(long userId);
}
