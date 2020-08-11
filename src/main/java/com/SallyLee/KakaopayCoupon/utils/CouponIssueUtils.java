package com.SallyLee.KakaopayCoupon.utils;

import java.time.LocalDate;
import java.util.Random;

public class CouponIssueUtils {

	private final char[] value = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
			'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
	
	private final char[] result;
	
	public CouponIssueUtils(int length) {
		if (length < 1)
			throw new IllegalArgumentException("length < 1: " + length);
		result = new char[length];
	}
	
	public String createCoupon() {
		Random random = new Random();
		for (int i=0; i<result.length; i++) {
			char ch = value[random.nextInt(value.length)];
			result[i] = ch;
		}
		System.out.println(result);
		return new String(result);
	}
	
	//쿠폰발행일로 부터 30일 이후 만료날짜 셋팅(임의)
	public LocalDate getExpireDt() {
		return LocalDate.now().plusDays(30);
	}
}
