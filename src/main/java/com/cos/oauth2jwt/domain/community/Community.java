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

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

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
	private String title;
	
	@Lob
	private String content;
	
	private String category;
	
	@ColumnDefault("0")
	private int count; // 추천 카운트

	@ManyToOne // 자동으로 Eager 전략
	@JoinColumn(name = "userId")
	private User user;
	
    @CreationTimestamp
    private Timestamp createDate;
	
    @JsonIgnoreProperties({"community"})
	@OneToMany(mappedBy = "community", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)  // mappedBy : reply의 변수명
	@OrderBy("id desc")
	private List<CReply> replys;

}
