package com.cos.oauth2jwt.domain.community;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface CommunityRepository extends JpaRepository<Community, Long>{
	
//	@Query(value="SELECT * FROM community where categoryId = ?", nativeQuery=true)
//	public List<Community> categoryCommunity(long id);
//	
//	@Query(value="SELECT * FROM community where categoryId = ? ORDER BY count DESC", nativeQuery=true)
//	public List<Community> popularCommunity(long id);
//	
//	@Query(value="SELECT * FROM community where categoryId = ? ORDER BY createDate DESC", nativeQuery=true)
//	public List<Community> createDateCommunity(long id);
	
}
