package com.cos.oauth2jwt.Query;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.cos.oauth2jwt.web.community.dto.CommunityListRespDto;
import com.cos.oauth2jwt.web.likes.dto.LikeClickRespDto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CommunityQuery {
	private final EntityManager em;
	
	public List<CommunityListRespDto> findAllByCategoryAndSort(String sort, Long categoryId, Long principalId, Pageable pageable ) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT com.id, com.title, ca.title categoryTitle, com.createDate, com.userId, u.name, u.imageUrl, ");
		sb.append("(SELECT COUNT(*) FROM CReply cr WHERE cr.communityId = com.id) replyCount, ");
		sb.append("(SELECT COUNT(*) FROM likes l WHERE l.communityId = com.id) likeCount, ");
		sb.append("if((select true FROM likes l WHERE l.communityId = com.id AND l.userId = ?), 'true', 'false') likeCheck ");
		sb.append("FROM Community com ");
		sb.append("INNER JOIN category ca ");
		sb.append("INNER JOIN User u ");
		sb.append("WHERE com.categoryId = ca.id AND com.userId = u.id ");
		if(categoryId != 0) {
			sb.append("AND com.categoryId = ? ");
		}
		if(sort.equals("new")) {
			sb.append("order by createDate DESC ");
		}else {
			sb.append("order by likeCount DESC ");	
		}
		
	    if (pageable != null && pageable.isPaged()) {
	        sb.append(" limit ").append(pageable.getOffset()).append(',').append(pageable.getPageSize());
	    }
	    
	    Query query = em.createNativeQuery(sb.toString()).setParameter(1, principalId);
	    if(categoryId != 0) {
	    	query.setParameter(2, categoryId);
	    }
	    JpaResultMapper result  = new JpaResultMapper();
	    List<CommunityListRespDto> respDto = result.list(query, CommunityListRespDto.class);
	    
	    System.out.println("커뮤니티 값은? : " + respDto);
		return respDto;
	}
	
	public LikeClickRespDto LikeClick(Long principalId, Long communityId) {
		StringBuffer sb = new StringBuffer();
		sb.append("select com.id, ");
		sb.append("(SELECT COUNT(*) FROM likes l WHERE l.communityId = ?) likeCount, ");
		sb.append("if((select true FROM likes l WHERE l.communityId = com.id AND l.userId = ?), 'true', 'false') likeCheck ");
		sb.append("FROM Community com ");
		sb.append("where com.id = ? ");
		
	    Query query = em.createNativeQuery(sb.toString());
	    query.setParameter(1, communityId);
	    query.setParameter(2, principalId);
	    query.setParameter(3, communityId);
	   
	    JpaResultMapper result  = new JpaResultMapper();
	    LikeClickRespDto respDto = result.uniqueResult(query,LikeClickRespDto.class);
	    
	    System.out.println("커뮤니티 값은? : " + respDto);
		return respDto;
	}

}
