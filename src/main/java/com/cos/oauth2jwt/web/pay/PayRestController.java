package com.cos.oauth2jwt.web.pay;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.oauth2jwt.config.auth.PrincipalDetails;
import com.cos.oauth2jwt.domain.pay.Pay;
import com.cos.oauth2jwt.domain.user.User;
import com.cos.oauth2jwt.service.PayService;
import com.cos.oauth2jwt.web.dto.CMRespDto;
import com.cos.oauth2jwt.web.pay.dto.FreeSaveReqDto;
import com.cos.oauth2jwt.web.pay.dto.PayCheckReqDto;
import com.cos.oauth2jwt.web.pay.dto.PaySaveReqDto;
import com.cos.oauth2jwt.web.pay.dto.RefundReqDto;
import com.cos.oauth2jwt.web.user.dto.UserIdRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class PayRestController {

	private final PayService payService;

	@GetMapping("/admin/pay")
	public CMRespDto<?> findAll(@AuthenticationPrincipal PrincipalDetails principalDetails) {
		return new CMRespDto<>(HttpStatus.OK.value(), "", payService.전체찾기());
	}

	// 회원 결제조회	
	@GetMapping("/pay/{id}")
	public CMRespDto<?> findByUserId(@PathVariable long id) {
		return new CMRespDto<>(HttpStatus.OK.value(), "", payService.유저아이디로찾기(id));
	}

	@PostMapping("/pay")
	public CMRespDto<?> save(@RequestBody PaySaveReqDto paySaveReqDto,
			@AuthenticationPrincipal PrincipalDetails principalDetails) {
		User user = principalDetails.getUser();
		Pay pay = paySaveReqDto.toEntity();
		pay.setUser(user);
		Pay payEntity = payService.저장하기(pay);

		if (payEntity != null) {
			UserIdRespDto dto = new UserIdRespDto();
			dto.setId(principalDetails.getId());
			return new CMRespDto<>(HttpStatus.CREATED.value(), "", dto);
		} else {
			return new CMRespDto<>(HttpStatus.BAD_REQUEST.value(), "fail", null);
		}
	}

	@PostMapping("/pay/free")
	public CMRespDto<?> freeSave(@RequestBody FreeSaveReqDto paySaveReqDto,
			@AuthenticationPrincipal PrincipalDetails principalDetails) {
		User user = principalDetails.getUser();
		Pay pay = paySaveReqDto.toEntity();

		// 무료 강의이기 때문에 강제로 값넣기.
		pay.setStatus("paid");
		pay.setUser(user);
		pay.setBuyer_name(user.getName());
		pay.setBuyer_email(user.getEmail());

		// 페이테이블 저장
		Pay payEntity = payService.저장하기(pay);

		if (payEntity != null) {
			UserIdRespDto dto = new UserIdRespDto();
			dto.setId(principalDetails.getId());
			return new CMRespDto<>(HttpStatus.CREATED.value(), "", dto);
		} else {
			return new CMRespDto<>(HttpStatus.BAD_REQUEST.value(), "fail", null);
		}
	}

	@GetMapping("/pay/check/{id}")
	public CMRespDto<?> paidCheck(@PathVariable Long id,
			@AuthenticationPrincipal PrincipalDetails principalDetails) {
		Pay payEntity = payService.결제체크(id, principalDetails.getUser().getId());
		if(payEntity != null) {
			return new CMRespDto<>(HttpStatus.CREATED.value(), "", payEntity);
		}
		else {
			return new CMRespDto<>(HttpStatus.CREATED.value(), "", null);
		}
		
	}
	
	// 환불신청
	@PutMapping("/pay/refund")
	public CMRespDto<?> refunding(@RequestBody RefundReqDto refundReqDto,
			@AuthenticationPrincipal PrincipalDetails principalDetails) {
		long payId = refundReqDto.getPayId();
		Pay payEntity = payService.환불신청(payId);
		return new CMRespDto<>(HttpStatus.OK.value(),"성공",payEntity);
	}
	
	// 환불하기
		@PutMapping("/pay/refunded")
		public CMRespDto<?> refunded(@RequestBody RefundReqDto refundReqDto,
				@AuthenticationPrincipal PrincipalDetails principalDetails) {
			System.out.println("환불들어오나요?");
			long payId = refundReqDto.getPayId();
			Pay payEntity = payService.환불하기(payId);
			return new CMRespDto<>(HttpStatus.OK.value(),"성공",payEntity);
		}
	
	// 환불신청 취소
	@PutMapping("/pay/refund/cancle")
	public CMRespDto<?> refundCancle(@RequestBody RefundReqDto refundReqDto,
			@AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		long payId = refundReqDto.getPayId(); // 페이아이디 가져오는거 
		
		Pay payEntity = payService.환불신청취소(payId); 
		
		return new CMRespDto<>(HttpStatus.OK.value(),"성공",payEntity);
	}
}
