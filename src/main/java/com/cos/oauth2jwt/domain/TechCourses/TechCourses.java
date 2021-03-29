package com.cos.oauth2jwt.domain.TechCourses;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.cos.oauth2jwt.domain.courses.Courses;
import com.cos.oauth2jwt.domain.tech.Tech;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TechCourses {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "techId")
	private Tech tech;
	
	@ManyToOne
	@JoinColumn(name = "coursesId")
	private Courses courses;
}
