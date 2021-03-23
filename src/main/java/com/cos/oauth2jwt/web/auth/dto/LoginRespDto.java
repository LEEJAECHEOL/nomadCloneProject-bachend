package com.cos.oauth2jwt.web.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRespDto {
	private Long id;
	private String name;
	private String email;
	private String roles;
	private String token;
	private String provider;
}
