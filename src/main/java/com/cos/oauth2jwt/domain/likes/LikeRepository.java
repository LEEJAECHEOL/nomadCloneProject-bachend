package com.cos.oauth2jwt.domain.likes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
public interface LikeRepository extends JpaRepository<Likes, Long>{
	
	@Query(value="SELECT count(*) FROM likes where communityId = ? and userId =?", nativeQuery=true)
	public int findbycommunityIdAndUserId(long communityId, long userId);
	
	@Modifying
	@Query(value="DELETE FROM likes where communityId = ? and userId =?", nativeQuery=true)
	public int deletebycommunityIdAndUserId(long communityId, long userId);
}
