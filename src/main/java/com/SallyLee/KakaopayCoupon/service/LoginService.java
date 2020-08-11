package com.SallyLee.KakaopayCoupon.service;

import java.util.List;

import com.SallyLee.KakaopayCoupon.dto.CustomerDTO;
import com.SallyLee.KakaopayCoupon.entity.CustomerInfo;

public interface LoginService {
	
	
	//패스워드 안전한 방법으로 계정생성
	CustomerInfo signUp(CustomerDTO userDTO);
	
	//로그인하여 토큰발급
	String signIn(String id, String pw);
}
