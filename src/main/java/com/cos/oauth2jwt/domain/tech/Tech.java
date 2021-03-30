package com.cos.oauth2jwt.domain.tech;

<<<<<<< HEAD
=======
import java.sql.Timestamp;
import java.util.List;

>>>>>>> 413aacb08dc45ce3b90edd1775b122ac3ed717a8
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
<<<<<<< HEAD
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.cos.oauth2jwt.domain.file.Files;
=======
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.cos.oauth2jwt.domain.TechCourses.TechCourses;
import com.cos.oauth2jwt.domain.courses.Courses;

>>>>>>> 413aacb08dc45ce3b90edd1775b122ac3ed717a8
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

<<<<<<< HEAD
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
=======
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
>>>>>>> 413aacb08dc45ce3b90edd1775b122ac3ed717a8
@Entity
public class Tech {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
<<<<<<< HEAD
	private String title;
	
	@ManyToOne // 자동으로 Eager 전략
	@JoinColumn(name = "fileId")
	private Files file;
=======
	private String name;
	private String imageUrl;
	
	@OneToMany(mappedBy = "tech")
	private List<TechCourses> courses;
	
	@CreationTimestamp
	private Timestamp createDate;
>>>>>>> 413aacb08dc45ce3b90edd1775b122ac3ed717a8
}
