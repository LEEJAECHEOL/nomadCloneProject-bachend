package com.cos.oauth2jwt.web.pay.dto;

import com.cos.oauth2jwt.domain.courses.Courses;
import com.cos.oauth2jwt.domain.pay.Pay;

import lombok.Data;

@Data
public class FreeSaveReqDto {
	private String name;
	private Long courseId;
	private int paid_amount;
	
	public Pay toEntity() {
		return Pay.builder()
				.paid_amount(paid_amount)
				.name(name)
				.course(Courses.builder().id(courseId).build())
				.build();
	}
}
