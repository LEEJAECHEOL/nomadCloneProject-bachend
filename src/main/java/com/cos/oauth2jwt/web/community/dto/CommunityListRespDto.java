package com.cos.oauth2jwt.web.community.dto;

import java.math.BigInteger;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommunityListRespDto {
	private BigInteger id;
	private String title;
	private String categoryTitle;
	private Timestamp createDate;
	private BigInteger userId;
	private String name;
	private String imageUrl;
	private BigInteger replyCount;
	private BigInteger likeCount;
	private String likeCheck;
}
