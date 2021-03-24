package com.cos.oauth2jwt.domain.video;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import com.cos.oauth2jwt.util.JsonToListListConverter;
import com.cos.oauth2jwt.util.JsonToListStringConverter;

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

	@Column(nullable = false)
	private Long folderId;	// 폴더아이디
	
	@Column(name = "contents", columnDefinition = "json")
	@Convert(converter = JsonToListStringConverter.class)
	private List<String> contents = new ArrayList<>();
	
	@Column(name = "contentList", columnDefinition = "json")
	@Convert(converter = JsonToListListConverter.class)
	private List<List<Map<String, Object>>> contentList = new ArrayList<>();
    
    @CreationTimestamp
    private Timestamp createDate;			
    
    
}
