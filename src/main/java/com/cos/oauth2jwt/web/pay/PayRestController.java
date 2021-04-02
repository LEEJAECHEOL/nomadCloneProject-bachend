package com.cos.oauth2jwt.web.pay;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.oauth2jwt.config.auth.PrincipalDetails;
import com.cos.oauth2jwt.domain.pay.Pay;
import com.cos.oauth2jwt.service.PayService;
import com.cos.oauth2jwt.web.dto.CMRespDto;
import com.cos.oauth2jwt.web.pay.dto.PaySaveReqDto;
import com.cos.oauth2jwt.web.user.dto.UserIdRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class PayRestController {
	
	private final PayService payService;
	
	@GetMapping("/admin/pay")
	public CMRespDto<?> findAll(@AuthenticationPrincipal PrincipalDetails principalDetails) {
		return new CMRespDto<>(HttpStatus.OK.value(), "" , payService.전체찾기());
	}
	
	@PostMapping("/pay")
	public CMRespDto<?> save(@RequestBody PaySaveReqDto paySaveReqDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		Pay payEntity = payService.저장하기(paySaveReqDto.toEntity());
		if(payEntity != null) {
			UserIdRespDto dto = new UserIdRespDto();
			dto.setId(principalDetails.getId());
			return new CMRespDto<>(HttpStatus.CREATED.value(), "" , dto);
		}else {
			return new CMRespDto<>(HttpStatus.BAD_REQUEST.value(), "fail" , null);
		}
	}

}
