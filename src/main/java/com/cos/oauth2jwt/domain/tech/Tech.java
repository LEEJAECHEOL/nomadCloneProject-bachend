package com.cos.oauth2jwt.domain.tech;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.cos.oauth2jwt.domain.TechCourses.TechCourses;
import com.cos.oauth2jwt.domain.courses.Courses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Tech {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String imageUrl;
	
	@OneToMany(mappedBy = "tech")
	private List<TechCourses> courses;
	
	@CreationTimestamp
	private Timestamp createDate;
}
