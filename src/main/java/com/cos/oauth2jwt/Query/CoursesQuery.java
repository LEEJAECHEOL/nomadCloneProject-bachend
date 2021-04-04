package com.cos.oauth2jwt.Query;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Component;

import com.cos.oauth2jwt.web.courses.dto.CoursesFilterPreviewRespDto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CoursesQuery {
	
	private final EntityManager em;
	
	public List<CoursesFilterPreviewRespDto> findByFilter(String level, String isFree) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT id, previewImage, title, subTitle, level, tech, price, videoId FROM courses ");
		if(level != "") {
			sb.append("WHERE level = '" + level + "' ");
		}else {
			if(isFree != "") {
				sb.append("WHERE ");
			}
		}
		if(isFree.equals("true")) {
			if(level != "") {
				sb.append("AND price = 0 ");
			}else {
				sb.append("price = 0 ");
			}
		}else if(isFree.equals("false")) {
			if(level != "") {
				sb.append("AND price != 0 ");
			}else {
				sb.append("price != 0 ");
			}
			
		}
		
	    Query query = em.createNativeQuery(sb.toString());
	    JpaResultMapper result  = new JpaResultMapper();
		return result.list(query, CoursesFilterPreviewRespDto.class);
	}

}
