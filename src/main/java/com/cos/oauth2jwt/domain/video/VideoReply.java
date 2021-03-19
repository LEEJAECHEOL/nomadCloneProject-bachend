package com.cos.oauth2jwt.domain.video;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.cos.oauth2jwt.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class VideoReply {		// 비디오 댓글
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false, length = 300)
	private String content;
	
	@Column(nullable=false, name="recommand")
	@ColumnDefault("0")
	private int recommand;
	
	
	@Column(nullable=false, name="depth")
	@ColumnDefault("0")
	private int depth;		//대댓글깊이
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;	
	
	@ManyToOne
	@JoinColumn(name="videoId")
	private Video video;		//비디오 아이디 (연관관계 ManytoOne) 하나의 비디오에 여러개의 댓글
	
	@CreationTimestamp
    private Timestamp createDate;
}
