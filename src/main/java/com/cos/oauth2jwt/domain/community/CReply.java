package com.cos.oauth2jwt.domain.community;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import com.cos.oauth2jwt.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class CReply {
	@Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 200)
	@NotBlank
	private String content; 
	
	// 유저
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	
	// 포스트
	@ManyToOne
	@JoinColumn(name = "communityId")
	private Community community;
	
	@CreationTimestamp
	private Timestamp createDate;

	
}
