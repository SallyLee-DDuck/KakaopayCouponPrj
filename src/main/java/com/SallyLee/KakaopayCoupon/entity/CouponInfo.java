package com.SallyLee.KakaopayCoupon.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class CouponInfo {
	
	// KEY
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "key")
	private Long key;
	
	// 쿠폰번호
	@Column(name = "coupon_number", nullable = false, unique = true)
	private String cpNum;

	// 사용여부
	@Column(name = "use_yn")
	private boolean useYn = false;
	
	// 발행여부
	@Column(name = "issue_yn")
	private boolean issueYn = false;
	
	// 핸드폰번호
	@Column(name = "customer_hp")
	private String custmHp;
	
	// 만료일자
	@Column(name = "expire_date", nullable = false, columnDefinition = "DATE")
	private LocalDate expireDt;
	
	@Builder
	public CouponInfo(String number, boolean useYn, boolean issueYn, String hp, LocalDate expireDt) {
		this.cpNum = number;
		this.useYn = useYn;
		this.issueYn = issueYn;
		this.custmHp = hp;
		this.expireDt = expireDt;
	}
}
