package com.cos.oauth2jwt.web.tech;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cos.oauth2jwt.domain.file.MyFile;
import com.cos.oauth2jwt.domain.tech.Tech;
import com.cos.oauth2jwt.service.MyFileService;
import com.cos.oauth2jwt.service.TechService;
import com.cos.oauth2jwt.web.dto.CMRespDto;
import com.cos.oauth2jwt.web.tech.dto.TechSaveReqDto;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
public class TechRestController {

	private final TechService techService;
	private final MyFileService myFileService;

	@PostMapping("/admin/tech")
	public CMRespDto<?> save(TechSaveReqDto techSaveReqDto, HttpServletRequest request){
		MyFile fileEntity =  myFileService.이미지업로드(techSaveReqDto.getFile(), request);
		Tech tech = Tech.builder().title(techSaveReqDto.getTitle())
									.isFilter(techSaveReqDto.isFilter())
									.file(MyFile.builder().id(fileEntity.getId()).build())
									.build();
		Tech techEntity = techService.테크저장(tech); 
		MyFile image = myFileService.한건찾기(techEntity.getFile().getId());
		image.setImageFilePath("");
		techEntity.setFile(image);
		return new CMRespDto<>(HttpStatus.CREATED.value(),"성공", techEntity);
	}

	// 코스 테크 선택시 사용
	@GetMapping("/tech")
	public CMRespDto<?> findAll(){
		List<Tech> techEntity = techService.테크전체찾기();
		List<Tech> techResp = new ArrayList<>();
		techEntity.stream().forEach((list) -> {
			Tech tech = list;
			tech.getFile().setImageFilePath("");
			techResp.add(tech);
		});
		return new CMRespDto<>(HttpStatus.OK.value(),"성공", techResp);
	}

	@DeleteMapping("/admin/tech/{id}")
	public CMRespDto<?> deleteById(@PathVariable long id){
		techService.테크삭제(id);
		return new CMRespDto<>(HttpStatus.OK.value(),"성공",null);
	}

//	
//	@PutMapping("/tech/{id}")
//	public CMRespDto<?> update(@PathVariable long id, @RequestBody TechSaveReqDto techSaveReqDto){
//		techService.테크수정(id, techSaveReqDto);
//		return new CMRespDto<>(HttpStatus.OK.value(),"성공",null);
//	}
	
}
