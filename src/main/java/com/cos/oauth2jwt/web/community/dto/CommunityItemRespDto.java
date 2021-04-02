package com.cos.oauth2jwt.web.community.dto;

import java.math.BigInteger;

import com.cos.oauth2jwt.domain.community.Community;

import lombok.Data;

@Data
public class CommunityItemRespDto {
	private Community community;
	private BigInteger id;
	private BigInteger likeCount;
	private String likeCheck;
}
