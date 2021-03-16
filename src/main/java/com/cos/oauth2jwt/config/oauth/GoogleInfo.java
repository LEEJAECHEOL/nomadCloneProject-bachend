package com.cos.oauth2jwt.config.oauth;

import java.util.Map;

public class GoogleInfo extends OAuth2UserInfo {

	public GoogleInfo(Map<String, Object> attributes) {
		super(attributes);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getId() {
		return attributes.get("googleId").toString();
	}
	
	@Override
	public String getName() {
		return attributes.get("name").toString();
	}

	@Override
	public String getEmail() {
		return attributes.get("email").toString();
	}

	@Override
	public String getImageUrl() {
		return attributes.get("imageUrl").toString();
	}
	

}
