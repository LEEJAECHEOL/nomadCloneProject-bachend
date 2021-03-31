package com.cos.oauth2jwt.web.faq;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.oauth2jwt.domain.community.Category;
import com.cos.oauth2jwt.domain.faq.FaqCategory;
import com.cos.oauth2jwt.service.FaqCategoryService;
import com.cos.oauth2jwt.web.community.dto.CategorySaveReqDto;
import com.cos.oauth2jwt.web.dto.CMRespDto;
import com.cos.oauth2jwt.web.faq.dto.FaqCategorySaveReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class FaqCategoryController {

	private final FaqCategoryService faqCategoryService;
	
	@PostMapping("/admin/faq/category")
	public CMRespDto<?> save(@RequestBody FaqCategorySaveReqDto faqCategorySaveReqDto){
		FaqCategory faqCategoryEntity = faqCategoryService.카테고리저장(faqCategorySaveReqDto.toEntity());
		faqCategoryEntity.setFaq(new ArrayList<>());
		return new CMRespDto<>(HttpStatus.OK.value(),"성공",faqCategoryEntity);
	}
	
	@GetMapping("/faq/category")
	public CMRespDto<?> findAll(){
		List<FaqCategory> faqCategoryEntity = faqCategoryService.전체찾기();
		return new CMRespDto<>(HttpStatus.OK.value(), "성공", faqCategoryEntity);
	}
	
	@DeleteMapping("/admin/faq/category/{id}")
	public CMRespDto<?> delete(@PathVariable long id){
		faqCategoryService.삭제하기(id);
		return new CMRespDto<>(HttpStatus.OK.value(), "성공", null);
	}
	
}
