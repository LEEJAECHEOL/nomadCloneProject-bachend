package com.cos.oauth2jwt.domain.courses;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.cos.oauth2jwt.domain.TechCourses.TechCourses;
import com.cos.oauth2jwt.domain.tech.Tech;
import com.cos.oauth2jwt.domain.video.Video;
import com.cos.oauth2jwt.util.JsonToListConverter;
import com.cos.oauth2jwt.util.JsonToMapConverter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Courses {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "previewImage", columnDefinition = "json")
	@Convert(converter = JsonToMapConverter.class)
	private Map<String, Object> previewImage = new HashMap<>();
	
	@Column(name = "mainImage", columnDefinition = "json")
	@Convert(converter = JsonToMapConverter.class)
	private Map<String, Object> mainImage = new HashMap<>();
	
	private String title;
	private String subTitle;
	private String backgroundColor;
	private String textColor;
	private String level;
	
	@OneToMany(mappedBy = "courses")
	private List<TechCourses> techs;
	
	@Column(name = "simpleImage", columnDefinition = "json")
	@Convert(converter = JsonToListConverter.class)
	private List<Map<String, Object>> simpleImage = new ArrayList<>();
	
	@Column(name = "videoInfo", columnDefinition = "json")
	@Convert(converter = JsonToMapConverter.class)
	private Map<String, Object> videoInfo = new HashMap<>();
	
	@Column(name = "simpleInfo", columnDefinition = "json")
	@Convert(converter = JsonToListConverter.class)
	private List<Map<String, Object>> simpleInfo = new ArrayList<>();

	@Column(name = "levelContent", columnDefinition = "json")
	@Convert(converter = JsonToListConverter.class)
	private List<Map<String, Object>> levelContent = new ArrayList<>();

	@Column(name = "concept", columnDefinition = "json")
	@Convert(converter = JsonToListConverter.class)
	private List<Map<String, Object>> concept = new ArrayList<>();
	
	@Column(name = "skill", columnDefinition = "json")
	@Convert(converter = JsonToMapConverter.class)
	private Map<String, Object> skill = new HashMap<>();
		
	@Column(name = "lectureAfter", columnDefinition = "json")
	@Convert(converter = JsonToListConverter.class)
	private List<Map<String, Object>> lectureAfter = new ArrayList<>();
	
	@OneToOne
    @JoinColumn(name = "videoId")
	private Video video;
	
	private String price;
	
    @CreationTimestamp
    private Timestamp createDate;
	
}

