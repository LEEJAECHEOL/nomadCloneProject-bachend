package com.cos.oauth2jwt.web.pay.dto;

import com.cos.oauth2jwt.domain.courses.Courses;
import com.cos.oauth2jwt.domain.pay.Pay;

import lombok.Data;

@Data
public class PaySaveReqDto {

	private String pay_method;  	// 결제수단
	private String imp_uid;			// 고유주문번호
	private String merchant_uid;	// 고유 주문번호
	private int paid_amount;			// 가격
	private String name;			// 주문명
	private String pg_tid;			// 카드사 승인번호
	private String buyer_name;		// 구매자명
	private String buyer_email;		// 구매자 이메일
	private String currency;		// 화폐단위
	private int card_quota;			// 할부
	private String status;			// 상태
	private Long courseId;
	
	public Pay toEntity() {
		return Pay.builder()
				.pay_method(pay_method)
				.imp_uid(imp_uid)
				.merchant_uid(merchant_uid)
				.paid_amount(paid_amount)
				.name(name)
				.pg_tid(pg_tid)
				.buyer_name(buyer_name)
				.buyer_email(buyer_email)
				.currency(currency)
				.card_quota(card_quota)
				.status(status)
				.course(Courses.builder().id(courseId).build())
				.build();
	}
}
