package com.cos.oauth2jwt.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CMRespDto<T> {
	private int statusCode;
	private String msg;
	private T data;
}
