package com.SallyLee.KakaopayCoupon.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor
public class CustomerDTO {
	
	private String custmID; //고객ID
	private String custmpw; //고객패스워드
	
	@Builder
	public CustomerDTO(String id, String pw) {
		this.custmID = id;
		this.custmpw = pw;
	}
}
