package com.SallyLee.KakaopayCoupon.dto;

import java.util.Date;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

@Getter 
@Setter 
@NoArgsConstructor
public class CouponDTO {
	private int totCnt; //총개수
	private String cpNum; //쿠폰번호 
	private String custmHp; //핸드폰번호
	private boolean useYn; //사용여부
	private boolean IssueYn; //발행여부
	private Date expireDt; //만료일자
	
	@Builder
	public CouponDTO(int cnt, String Hp, boolean useYn, boolean IssueYn) {
		this.totCnt = cnt;
		this.custmHp = Hp;
		this.useYn = useYn;
		this.IssueYn = IssueYn;
	}
}
