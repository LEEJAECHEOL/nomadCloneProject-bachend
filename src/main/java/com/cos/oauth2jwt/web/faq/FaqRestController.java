package com.cos.oauth2jwt.web.faq;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.oauth2jwt.domain.faq.Faq;
import com.cos.oauth2jwt.service.FaqService;
import com.cos.oauth2jwt.web.dto.CMRespDto;
import com.cos.oauth2jwt.web.faq.dto.FaqSaveReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class FaqRestController {
	
	private final FaqService faqService;
	@GetMapping("/faq")
	public CMRespDto<?> findAll() {
		List<Faq> faqs = faqService.전체찾기();
		return new CMRespDto<>(HttpStatus.OK.value(),"성공",faqs);
	}
	
	@GetMapping("/faq/{id}")
	public CMRespDto<?> findById(@PathVariable long id) {
		Faq faqEntity = faqService.상세보기(id);
		return new CMRespDto<>(HttpStatus.OK.value(),"성공",faqEntity);
	}
	
	@PostMapping("/faq")
	public CMRespDto<?> save(@RequestBody FaqSaveReqDto faqSaveReqDto) {
		Faq faq = faqSaveReqDto.toEntity();
		Faq faqEntity = faqService.저장하기(faq);
		return new CMRespDto<>(HttpStatus.OK.value(),"성공",faqEntity);
		
	}
	
	@PutMapping("/faq/{id}")
	public CMRespDto<?> update(@PathVariable long id, @RequestBody FaqSaveReqDto dto) {
		faqService.수정하기(id, dto);
		return new CMRespDto<>(HttpStatus.OK.value(),"성공",null);
	}
	
	@DeleteMapping("/faq/{id}")
	public CMRespDto<?> deleteById(@PathVariable long id) {
		faqService.삭제하기(id);
		return new CMRespDto<>(HttpStatus.OK.value(),"성공",null);
	}
}
