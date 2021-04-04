package com.cos.oauth2jwt.domain.pay;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PayRepository extends JpaRepository<Pay, Long> {

	@Query(value="SELECT * FROM pay where userId = ?", nativeQuery=true)
	public List<Pay> findUserId(long userId);
	
	@Query(value="SELECT * FROM pay where couseId = ? and userId = ? ", nativeQuery=true)
	public Pay findByUserIdAndCourseId(long couseId, long userId);
	
	// 환불신청	
	@Modifying
	@Query(value="Update pay set status = 'refunding' where id = ?", nativeQuery=true)
	public int refunding(long payId);
	
	// 환불신청 취소	
	@Modifying
	@Query(value="Update pay set status = 'paid' where id = ?", nativeQuery=true)
	public int refundingCancle(long payId);
}
