package com.cos.oauth2jwt.web.courses;


import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.oauth2jwt.config.auth.PrincipalDetails;
import com.cos.oauth2jwt.domain.courses.Courses;
import com.cos.oauth2jwt.domain.pay.Pay;
import com.cos.oauth2jwt.domain.user.User;
import com.cos.oauth2jwt.domain.video.Video;
import com.cos.oauth2jwt.service.CoursesService;
import com.cos.oauth2jwt.service.PayService;
import com.cos.oauth2jwt.web.courses.dto.CoursesFilterPreviewRespDto;
import com.cos.oauth2jwt.web.courses.dto.CoursesPreviewRespDto;
import com.cos.oauth2jwt.web.courses.dto.CoursesSaveReqDto;
import com.cos.oauth2jwt.web.dto.CMRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class CoursesRestController {
	
	private final CoursesService coursesService;
	private final PayService payService;
	
	@GetMapping("/courses")
	public CMRespDto<?> findAllPreview() {
		List<CoursesPreviewRespDto> entity = coursesService.미리보기전체가져오기();
		
		return new CMRespDto<>(HttpStatus.OK.value(), "" , entity);
	}
	
	@GetMapping("/courses/filter")
	public CMRespDto<?> findFilterPreview(String level, String isFree, Long techId) {
		List<CoursesPreviewRespDto> resp = coursesService.미리보기필터링하기(level, isFree, techId);
		return new CMRespDto<>(HttpStatus.OK.value(), "" , resp);
	}
	
	
	@GetMapping("/homeCourses")
	public CMRespDto<?> findSixPreview() {
		List<CoursesPreviewRespDto> entity = coursesService.미리보기6개가져오기();
		
		return new CMRespDto<>(HttpStatus.OK.value(), "" , entity);
	}
	
	@GetMapping("/courses/{id}")
	public CMRespDto<?> findById(@PathVariable long id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		Courses entity = coursesService.한건가져오기(id);
		boolean isPay = false; // 결제안함.
		if(principalDetails != null) { // 로그인 한 경우
			User user = principalDetails.getUser(); // 유저정보
			if((user.getRoles()).equals("ROLE_ADMIN")) { // 관리자일때
				isPay = true;
			}else { // 유저 일때
				Pay pay = payService.결제체크(id, user.getId()); // 코스 아이디, 유저아이디를 가지고있는 pay정보
				if(pay != null) {
					if(pay.getStatus().equals("paid")) {
						isPay = true;
					}
				} 
			}
		}  // 로그인 안한 경우 -> false 
		
		if(!isPay) {
			List<Map<String, Object>> contents = entity.getVideo().getContents();
			contents.stream().forEach((content)->{ // 강의 리스트
				List<Map<String, Object>> contentItem = (List<Map<String, Object>>) content.get("list");
				contentItem.stream().forEach((item)->{ // 영상 리스트
					Map<String, Object> originItem = item;
					if(!(boolean) originItem.get("isFree")) {
						originItem.replace("vimeoId", ""); // 
					}
				});
			});
		}
		return new CMRespDto<>(HttpStatus.OK.value(), "" , entity);
	}
	
//	관리자가 등록함.
	@PostMapping("/admin/courses")
	public CMRespDto<?> save(@RequestBody CoursesSaveReqDto coursesSaveReqDto) {
		Courses coursesEntity =  coursesService.저장하기(coursesSaveReqDto.toEntity());
		if(coursesEntity != null){
			return new CMRespDto<>(HttpStatus.CREATED.value(), "" , null);
		}else {
			return new CMRespDto<>(HttpStatus.BAD_REQUEST.value(), "fail" , null);
		}
	}
	
	@DeleteMapping("/admin/courses/{id}")
	public CMRespDto<?> delete(@PathVariable Long id){
		coursesService.삭제하기(id);
		return new CMRespDto<>(HttpStatus.OK.value(), "" , null);
	}
	
//	@PutMapping("/admin/courses/{id}")
//	public CMRespDto<?> update(@PathVariable Long id, @RequestBody CoursesSaveReqDto coursesSaveReqDto){
//		coursesService.수정하기(id, coursesSaveReqDto.toEntity());
//		return new CMRespDto<>(HttpStatus.OK.value(), "" , null);
//	}
}
