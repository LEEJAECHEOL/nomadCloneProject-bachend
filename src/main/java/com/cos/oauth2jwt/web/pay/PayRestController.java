package com.cos.oauth2jwt.web.pay;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.oauth2jwt.config.auth.PrincipalDetails;
import com.cos.oauth2jwt.domain.pay.Pay;
import com.cos.oauth2jwt.domain.user.User;
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
	
	@GetMapping("/pay/{id}")
	public CMRespDto<?> findByUserId(@PathVariable long id) {
		System.out.println("=======");
		System.out.println("회원 결제조회");
		System.out.println("=======");
		return new CMRespDto<>(HttpStatus.OK.value(), "" , payService.유저아이디로찾기(id));
	}
	
	@PostMapping("/pay")
	public CMRespDto<?> save(@RequestBody PaySaveReqDto paySaveReqDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		User user = principalDetails.getUser();
		Pay pay = paySaveReqDto.toEntity();
		pay.setUser(user);
		Pay payEntity = payService.저장하기(pay);
		
		if(payEntity != null) {
			UserIdRespDto dto = new UserIdRespDto();
			dto.setId(principalDetails.getId());
			return new CMRespDto<>(HttpStatus.CREATED.value(), "" , dto);
		}else {
			return new CMRespDto<>(HttpStatus.BAD_REQUEST.value(), "fail" , null);
		}
	}

}
