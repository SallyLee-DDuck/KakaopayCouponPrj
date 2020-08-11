package com.SallyLee.KakaopayCoupon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SallyLee.KakaopayCoupon.dto.CustomerDTO;
import com.SallyLee.KakaopayCoupon.entity.CustomerInfo;
import com.SallyLee.KakaopayCoupon.service.LoginService;

@RestController
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	//패스워드 안전한 방법으로 계정생성
	@PostMapping(value = "/sign-up")
	public CustomerInfo signUp(@RequestBody CustomerDTO customerDTO) {
		return loginService.signUp(customerDTO);
	}
	
	//로그인하여 토큰발급
	@GetMapping(value = "/sign-in")
	public String signIn(@RequestParam String id, @RequestParam String pw) {
		return loginService.signIn(id, pw);
	}

}
