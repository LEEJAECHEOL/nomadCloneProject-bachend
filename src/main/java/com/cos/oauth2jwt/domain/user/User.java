package com.cos.oauth2jwt.domain.user;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.cos.oauth2jwt.domain.community.Community;
import com.cos.oauth2jwt.domain.file.MyFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String username;
	private String password;
	private String email;
	private String name;
	private String provider;
	private String roles; // USER, ADMIN
	private String imageUrl;
	
	@OneToOne // 자동으로 Eager 전략
	@JoinColumn(name = "fileId")
	private MyFile file;
	
    @CreationTimestamp
    private Timestamp createDate;
	
	public List<String> getRoleList(){
		if(this.roles.length() > 0) {
			return Arrays.asList(this.roles.split(","));
		}
		return new ArrayList<>();
	}
}
