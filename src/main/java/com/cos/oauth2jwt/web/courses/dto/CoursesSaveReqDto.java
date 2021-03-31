package com.cos.oauth2jwt.web.courses.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.cos.oauth2jwt.domain.courses.Courses;
import com.cos.oauth2jwt.domain.video.Video;
import com.cos.oauth2jwt.util.JsonToListConverter;
import com.cos.oauth2jwt.util.JsonToMapConverter;

import lombok.Data;

@Data
public class CoursesSaveReqDto {
	
	private Map<String, Object> previewImage = new HashMap<>();
	private Map<String, Object> mainImage = new HashMap<>();
	private String title;
	private String subTitle;
	private String backgroundColor;
	private String textColor;
	private String level;
	private List<Map<String, Object>> tech = new ArrayList<>();
	private Map<String, Object> videoInfo = new HashMap<>();
	private List<Map<String, Object>> simpleInfo = new ArrayList<>();
	private List<Map<String, Object>> levelContent = new ArrayList<>();
	private List<Map<String, Object>> concept = new ArrayList<>();
	private Map<String, Object> skill = new HashMap<>();
	private List<Map<String, Object>> lectureAfter = new ArrayList<>();
	private Long videoId;
	private String price;

	public Courses toEntity() {
		return Courses.builder()
		.previewImage(previewImage)
		.mainImage(mainImage)
		.title(title)
		.subTitle(subTitle)
		.backgroundColor(backgroundColor)
		.textColor(textColor)
		.level(level)
		.tech(tech)
		.videoInfo(videoInfo)
		.simpleInfo(simpleInfo)
		.levelContent(levelContent)
		.concept(concept)
		.skill(skill)
		.lectureAfter(lectureAfter)
		.video(Video.builder().id(videoId).build())
		.price(price)
		.build();
	}
	
}
