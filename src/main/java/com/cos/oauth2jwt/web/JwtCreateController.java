package com.cos.oauth2jwt.web;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.cos.oauth2jwt.config.jwt.JwtProperties;
import com.cos.oauth2jwt.config.oauth.GoogleInfo;
import com.cos.oauth2jwt.config.oauth.OAuth2UserInfo;
import com.cos.oauth2jwt.domain.user.User;
import com.cos.oauth2jwt.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class JwtCreateController {
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@PostMapping("/oauth/jwt/google")
	public String jwtCreate(@RequestBody Map<String, Object> data) {
		System.out.println("jwtCreate 실행됨");
		System.out.println("Google" + data.get("profileObj"));
		OAuth2UserInfo googleInfo = new GoogleInfo((Map<String, Object>)data.get("profileObj"));
		
		User userEntity = userRepository.findByUsername("Google_" + googleInfo.getId());
		UUID uuid = UUID.randomUUID();
		String encPassword = new BCryptPasswordEncoder().encode(uuid.toString());
		if(userEntity == null) {
			User userRequest = User.builder()
					.username("Google_"+googleInfo.getId())
					.password(encPassword)
					.email(googleInfo.getEmail())
					.name(googleInfo.getName())
					.imageUrl(googleInfo.getImageUrl())
					.roles("ROLE_USER")
					.build();
			
			userEntity = userRepository.save(userRequest);
		}
		
		String jwtToken = JWT.create()
				.withSubject(userEntity.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
				.withClaim("id", userEntity.getId())
				.withClaim("name", userEntity.getName())
				.sign(Algorithm.HMAC512(JwtProperties.SECRET));
		
		return jwtToken;
	}
}
