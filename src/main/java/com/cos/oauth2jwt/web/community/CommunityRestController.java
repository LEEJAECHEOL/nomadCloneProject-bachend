package com.cos.oauth2jwt.web.community;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

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
import com.cos.oauth2jwt.domain.community.Community;
import com.cos.oauth2jwt.domain.community.dto.CommunitySaveReqDto;
import com.cos.oauth2jwt.domain.community.dto.CommunityUpdateReqDto;
import com.cos.oauth2jwt.domain.user.User;
import com.cos.oauth2jwt.service.CommunityService;
import com.cos.oauth2jwt.web.dto.CMRespDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class CommunityRestController {
	
	private final CommunityService communityService;
	
	@GetMapping("/com")
	public CMRespDto<?> findAll(String category) {
		return new CMRespDto<>(HttpStatus.OK.value(), "성공", communityService.전체찾기());
	}
	
	@PostMapping("/com")
	public CMRespDto<?> save(@RequestBody CommunitySaveReqDto communitySaveReqDto, HttpSession session) {
		System.out.println("세션 : "+session.getAttribute("principal"));
		Community community = communitySaveReqDto.toEntity();
//		community.setUser(principalDetails.getUser());
		Community communityEntity = communityService.글저장(community);
//		communityEntity.setUser(new User(1L,"ssar","1234","test@naver.com","cos","USER","testImage",new Timestamp(System.currentTimeMillis())));
		return new CMRespDto<>(HttpStatus.OK.value(),"성공", communityEntity);
	} 
	
	@GetMapping("/com/{id}")
	public CMRespDto<?> findById(@PathVariable long id){
		Community communityEntity = communityService.한건찾기(id);
		return new CMRespDto<>(HttpStatus.OK.value(),"성공",communityEntity);
	}
	
	@PutMapping("/com/{id}")
	public CMRespDto<?> update(@PathVariable long id, @RequestBody CommunityUpdateReqDto communityUpdateReqDto){
		Community communityEntity = communityService.수정하기(id,communityUpdateReqDto);
		return new CMRespDto<>(HttpStatus.OK.value(),"성공",communityEntity);
	}
	
	@DeleteMapping("/com/{id}")
	public CMRespDto<?> delete(@PathVariable long id){
		communityService.삭제하기(id);
		return new CMRespDto<>(HttpStatus.OK.value(),"성공",null);
	}
}
