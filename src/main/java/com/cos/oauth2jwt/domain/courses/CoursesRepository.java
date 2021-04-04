package com.cos.oauth2jwt.domain.courses;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CoursesRepository extends JpaRepository<Courses, Long> {

	@Query(value="SELECT * FROM courses limit 6", nativeQuery=true)
	public List<Courses> homeCourses();
	
	@Query(value="SELECT * FROM courses WHERE videoId = :videoId", nativeQuery=true)
	public Courses findByVideoId(long videoId);

}
