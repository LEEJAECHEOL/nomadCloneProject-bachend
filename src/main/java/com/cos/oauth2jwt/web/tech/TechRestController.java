package com.cos.oauth2jwt.web.tech;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.oauth2jwt.domain.tech.Tech;
import com.cos.oauth2jwt.service.TechService;
import com.cos.oauth2jwt.web.dto.CMRespDto;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
public class TechRestController {

	private final TechService techService;
	
//	
//	// 코스 테크 선택시 사용
//	@GetMapping("/tech")
//	public CMRespDto<?> findAll(){
//		List<Tech> techList = techService.테크전체찾기();
//		return new CMRespDto<>(HttpStatus.OK.value(),"성공",techList);
//	}
//	
//	@PostMapping("/tech")
//	public CMRespDto<?> save(@RequestBody TechSaveReqDto techSaveReqDto){
//		techService.테크저장(techSaveReqDto);
//		return new CMRespDto<>(HttpStatus.CREATED.value(),"성공",null);\
//		return null;
//	}
//	
//	@DeleteMapping("/tech/{id}")
//	public CMRespDto<?> deleteById(@PathVariable long id){
//		techService.테크삭제(id);
//		return new CMRespDto<>(HttpStatus.OK.value(),"성공",null);
//	}
//	
//	
//	@PutMapping("/tech/{id}")
//	public CMRespDto<?> update(@PathVariable long id, @RequestBody TechSaveReqDto techSaveReqDto){
//		techService.테크수정(id, techSaveReqDto);
//		return new CMRespDto<>(HttpStatus.OK.value(),"성공",null);
//	}
	
}
