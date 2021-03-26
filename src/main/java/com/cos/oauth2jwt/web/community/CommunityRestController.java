package com.cos.oauth2jwt.web.community;

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
import com.cos.oauth2jwt.service.CommunityService;
import com.cos.oauth2jwt.web.community.dto.CommunitySaveReqDto;
import com.cos.oauth2jwt.web.community.dto.CommunityUpdateReqDto;
import com.cos.oauth2jwt.web.dto.CMRespDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class CommunityRestController {
	
	private final CommunityService communityService;

	@GetMapping("/community")
	public CMRespDto<?> findAll() {
		return new CMRespDto<>(HttpStatus.OK.value(), "성공", communityService.전체찾기());
	}
	
	@PostMapping("/community")
	public CMRespDto<?> save(@RequestBody CommunitySaveReqDto communitySaveReqDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		System.out.println("들어오는데이터는?"+ communitySaveReqDto);
		System.out.println(principalDetails);
		Community community = communitySaveReqDto.toEntity();
		System.out.println(community);
		community.setUser(principalDetails.getUser());
		Community communityEntity = communityService.글저장(community);
		return new CMRespDto<>(HttpStatus.OK.value(),"성공", communityEntity);
	} 
	
	@GetMapping("/community/{id}")
	public CMRespDto<?> findById(@PathVariable long id){
		Community communityEntity = communityService.한건찾기(id);
		return new CMRespDto<>(HttpStatus.OK.value(),"성공",communityEntity);
	}
	
	@PutMapping("/community/{id}")
	public CMRespDto<?> update(@PathVariable long id, @RequestBody CommunityUpdateReqDto communityUpdateReqDto){
		Community communityEntity = communityService.수정하기(id,communityUpdateReqDto);
		return new CMRespDto<>(HttpStatus.OK.value(),"성공",communityEntity);
	}
	
	@DeleteMapping("/community/{id}")
	public CMRespDto<?> delete(@PathVariable long id){
		communityService.삭제하기(id);
		return new CMRespDto<>(HttpStatus.OK.value(),"성공",null);
	}
}
