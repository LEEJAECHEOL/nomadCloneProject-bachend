package com.cos.oauth2jwt.domain.pay;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.cos.oauth2jwt.domain.courses.Courses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pay {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String pay_method;  	// 결제수단
	private String merchant_uid;	// 고유 주문번호
	private int amount;				// 가격
	private String name;			// 주문명
	private String buyer_name;		// 구매자명
	private String buyer_tel;		// 구매자 번호
	private String buyer_email;		// 구매자 이메일
	
	@ManyToOne
    @JoinColumn(name = "couseId")
	private Courses couse;

    @CreationTimestamp
    private Timestamp createDate;
}
