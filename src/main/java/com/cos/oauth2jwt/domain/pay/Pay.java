package com.cos.oauth2jwt.domain.pay;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.cos.oauth2jwt.domain.courses.Courses;
import com.cos.oauth2jwt.domain.file.MyFile;
import com.cos.oauth2jwt.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pay {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
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
	
	@ManyToOne
    @JoinColumn(name = "couseId")
	private Courses course;

    @CreationTimestamp
    private Timestamp createDate;
    
    @OneToOne // 자동으로 Eager 전략
	@JoinColumn(name = "userId")
	private User user;
}
