package com.cos.oauth2jwt.service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.cos.oauth2jwt.domain.community.Category;
import com.cos.oauth2jwt.domain.community.CategoryRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CategoryService {

	private final CategoryRepository categoryRepository;
	
	@Transactional
	public Category 카테고리저장(Category category) {
		Category categoryEntity = categoryRepository.save(category);
		return categoryEntity;
	} 
	
	@Transactional(readOnly = true)
	public List<Category> 전체찾기(){
		List<Category> categoryEntity = categoryRepository.findAll();
		return categoryEntity;
	}
	
}
