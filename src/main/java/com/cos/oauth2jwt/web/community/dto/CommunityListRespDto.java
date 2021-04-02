package com.cos.oauth2jwt.web.community.dto;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.cos.oauth2jwt.domain.community.Category;
import com.cos.oauth2jwt.domain.community.CategoryRepository;
import com.cos.oauth2jwt.domain.community.Community;
import com.cos.oauth2jwt.domain.community.CommunityRepository;
import com.cos.oauth2jwt.domain.video.Video;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommunityListRespDto {
	
	private BigInteger id; //커뮤니티아이디
	private String title;  //커뮤니티제목
	private String categoryTitle;  //카테고리타이틀
	private Timestamp createDate;  // 커뮤니티크리에이트데이트
	private BigInteger userId;     // 유저 userId
	private String name;	       // 유저 name
	private String imageUrl;	   // 유저이미지url		
	private BigInteger replyCount; // 커뮤니티 리플리갯수
	private BigInteger likeCount;  // => 어떻게하지?
	private String likeCheck;	   // => 어떻게하지?
	
}
