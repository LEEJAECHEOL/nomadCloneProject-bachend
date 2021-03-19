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

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Video {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private int folderId;			//폴더	
	private int order;				//순서
	private String vimeoId;				//
	private String title;				//강의 제목
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	@ColumnDefault("true")
    private boolean isFree;				//무료 여부
    
    @CreationTimestamp
    private Timestamp createDate;		//날짜
}
