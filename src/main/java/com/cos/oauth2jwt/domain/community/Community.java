package com.cos.oauth2jwt.domain.community;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.cos.oauth2jwt.domain.likes.Likes;
import com.cos.oauth2jwt.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Community {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false,length = 100)
	@NotBlank
	private String title;
	
	@Lob
	@NotBlank
	private String content;	
	
	@ManyToOne // 자동으로 Eager 전략
	@JoinColumn(name = "userId")
	private User user;
	
	@ManyToOne // 자동으로 Eager 전략
	@JoinColumn(name = "categoryId")
	private Category category;
	
    @CreationTimestamp
    private Timestamp createDate;	
	
    @JsonIgnoreProperties({"community"})
	@OneToMany(mappedBy = "community", fetch = FetchType.LAZY, cascade = CascadeType.ALL)  // mappedBy : reply의 변수명
	@OrderBy("id desc")
	private List<CReply> replys;
    
    @JsonIgnoreProperties({"community"})
    @OneToMany(mappedBy = "community",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Likes> likes; 
    
    @Transient
    private boolean likeCheck;
    
    @Transient
	private int likeCount; // 추천 카운트
}
