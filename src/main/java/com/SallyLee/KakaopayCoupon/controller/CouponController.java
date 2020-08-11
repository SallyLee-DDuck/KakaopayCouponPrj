package com.SallyLee.KakaopayCoupon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SallyLee.KakaopayCoupon.dto.CouponDTO;
import com.SallyLee.KakaopayCoupon.entity.CouponInfo;
import com.SallyLee.KakaopayCoupon.service.CouponService;

@RestController
@RequestMapping(value = "/couponApi")
public class CouponController {

	@Autowired
	private CouponService couponService;
	
	//	랜덤한 코드(16자리) N개 생성 후 데이터베이스에 보관: 만료일은 생성일 로부터 30일이후로 설정 
	@PostMapping(value = "/couponList")
	public List<CouponInfo> makeCoupon(@RequestBody CouponDTO couponDTO) {
		return couponService.makeCoupon(couponDTO);
	}
	//	생성된 쿠폰 중 하나를 사용자에게 지급
	@PutMapping(value = "/couponList")
	public String issueCoupon(@RequestBody CouponDTO couponDTO) {
		return couponService.updateIssueCoupon(couponDTO);
	}
	//	사용자에게 지급된 쿠폰을 조회
	@GetMapping(value = "/couponList")
	public List<CouponInfo> issueCouponList() {
		return couponService.getIssueCouponList();
	}
	//	사용자가 지급된 쿠폰 중 하나를 사용함
	@PutMapping(value = "/couponList/{cpNum}/usetrue")
	public CouponInfo useCoupon(
			@PathVariable String cpNum,
			@RequestBody CouponDTO couponDTO
			) {
		return couponService.setUseCoupon(cpNum, couponDTO);
	}
	//	사용자가 지급된 쿠폰 중 하나를 사용취소함
	@PutMapping(value = "/couponList/{cpNum}/usefalse")
	public CouponInfo useCancelCoupon(
			@PathVariable String cpNum,
			@RequestBody CouponDTO couponDTO
			) {
		return couponService.setUseCancelCoupon(cpNum, couponDTO);
	}
	//	발급된 쿠폰중 당일 만료된 전체 쿠폰 목록을 조회
	@GetMapping(value = "/couponList/expiredCouponList")
	public List<CouponInfo> expiredCouponList() {
		return couponService.getExpiredCouponList();
	}
}
