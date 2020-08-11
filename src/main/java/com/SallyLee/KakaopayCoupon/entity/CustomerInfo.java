package com.SallyLee.KakaopayCoupon.entity;

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
public class CustomerInfo{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "key")
	private Long key;
	
	@Column(name = "id", nullable = false, unique = true)
	private String custmId;
	
	@Column(name = "pw", nullable = false)
	private String custmPw;
	
	@Builder
	public CustomerInfo(String id, String pw) {
		this.custmId = id; 
		this.custmPw = pw; 
	}

}
