package com.cos.oauth2jwt.web.community;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.oauth2jwt.domain.community.Category;
import com.cos.oauth2jwt.service.CategoryService;
import com.cos.oauth2jwt.web.community.dto.CategorySaveReqDto;
import com.cos.oauth2jwt.web.dto.CMRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class CategoryRestController {

	private final CategoryService categoryService;
	
	@PostMapping("/admin/category")
	public CMRespDto<?> save(@RequestBody CategorySaveReqDto categorySaveReqDto){
		Category categoryEntity = categoryService.카테고리저장(categorySaveReqDto.toEntity());
		return new CMRespDto<>(HttpStatus.CREATED.value(), "성공", categoryEntity);
	}
	
	@GetMapping("/category")
	public CMRespDto<?> findAll(){
		List<Category> categoryEntity = categoryService.전체찾기();
		return new CMRespDto<>(HttpStatus.OK.value(), "성공", categoryEntity);
	}
	
}
