package com.cos.oauth2jwt.web.likes.dto;

import java.math.BigInteger;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LikeClickRespDto {
	private BigInteger id;
	private BigInteger likeCount;
	private String likeCheck;
}
