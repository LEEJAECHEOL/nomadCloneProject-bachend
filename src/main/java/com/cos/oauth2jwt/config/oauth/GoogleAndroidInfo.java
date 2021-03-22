package com.cos.oauth2jwt.config.oauth;
import java.util.Map;

public class GoogleAndroidInfo extends OAuth2UserInfo {

	public GoogleAndroidInfo(Map<String, Object> attributes) {
		super(attributes);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getId() {
		return attributes.get("sub").toString(); // googleId
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
		return attributes.get("picture").toString(); // imageUrl
	}
	
}
