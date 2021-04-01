package com.cos.oauth2jwt.web.community;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import com.cos.oauth2jwt.web.community.dto.CommunityListRespDto;
import com.cos.oauth2jwt.web.community.dto.CommunitySaveReqDto;
import com.cos.oauth2jwt.web.community.dto.CommunityUpdateReqDto;
import com.cos.oauth2jwt.web.dto.CMRespDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class CommunityRestController {

	private final CommunityService communityService;
	
	@GetMapping("/community")
	public CMRespDto<?> findAll(String sort, Long categoryId,
								@PageableDefault(size = 10)Pageable pageable, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		long principalId = 0;
		if (principalDetails != null) {
			principalId = principalDetails.getUser().getId();
		} 
		List<CommunityListRespDto> communityEntity = communityService.전체찾기(sort, categoryId, principalId, pageable);
		return new CMRespDto<>(HttpStatus.OK.value(), "성공", communityEntity);
	}
	
//	@GetMapping("/community")
//	public CMRespDto<?> findAll(@AuthenticationPrincipal PrincipalDetails principalDetails) {
//		long principalId = 0;
//		if (principalDetails != null) {
//			principalId = principalDetails.getUser().getId();
//		} 
//		List<Community> communityEntity = communityService.전체찾기();
//		return new CMRespDto<>(HttpStatus.OK.value(), "성공", communityEntity);
//	}

	@GetMapping("/community/popular/{id}")
	public CMRespDto<?> findAllByCount(@PathVariable long id) {
		List<Community> communityEntity = communityService.카테고리별인기순으로찾기(id);
		return new CMRespDto<>(HttpStatus.OK.value(), "성공", communityEntity);
	}

	@GetMapping("/community/new/{id}")
	public CMRespDto<?> findAllByCreateDate(@PathVariable long id) {
		List<Community> communityEntity = communityService.카테고리별최신순으로찾기(id);
		return new CMRespDto<>(HttpStatus.OK.value(), "성공", communityEntity);
	}

	@PostMapping("/community")
	public CMRespDto<?> save(@RequestBody CommunitySaveReqDto communitySaveReqDto,
			@AuthenticationPrincipal PrincipalDetails principalDetails) {
		Community community = communitySaveReqDto.toEntity();
		community.setUser(principalDetails.getUser());
		Community communityEntity = communityService.글저장(community);
		return new CMRespDto<>(HttpStatus.OK.value(), "성공", communityEntity);
	}

	@GetMapping("/community/{id}")
	public CMRespDto<?> findById(@PathVariable long id) {
		Community communityEntity = communityService.한건찾기(id);
		return new CMRespDto<>(HttpStatus.OK.value(), "성공", communityEntity);
	}

	@GetMapping("/community/category/{id}")
	public CMRespDto<?> findByCategoryId(@PathVariable long id) {
		System.out.println("카테고리아이디는?" + id);
		List<Community> communityEntity = communityService.카테고리로찾기(id);
		return new CMRespDto<>(HttpStatus.OK.value(), "성공", communityEntity);
	}

	@PutMapping("/community/{id}")
	public CMRespDto<?> update(@PathVariable long id, @RequestBody CommunityUpdateReqDto communityUpdateReqDto) {
		Community communityEntity = communityService.수정하기(id, communityUpdateReqDto);
		return new CMRespDto<>(HttpStatus.OK.value(), "성공", communityEntity);
	}

	@DeleteMapping("/community/{id}")
	public CMRespDto<?> delete(@PathVariable long id) {
		communityService.삭제하기(id);
		return new CMRespDto<>(HttpStatus.OK.value(), "성공", null);
	}
}
