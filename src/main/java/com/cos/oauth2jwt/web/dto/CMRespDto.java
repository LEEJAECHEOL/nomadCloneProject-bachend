package com.cos.oauth2jwt.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CMRespDto<T> {
	private int statusCode; // 1은정상 -1은 실패
	private String msg; // 오류내용 Value too long for colum "Title varchar(60)"
	private T data;
}
