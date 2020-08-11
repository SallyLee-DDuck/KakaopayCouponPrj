package com.SallyLee.KakaopayCoupon.service;

import java.util.List;

import com.SallyLee.KakaopayCoupon.dto.CouponDTO;
import com.SallyLee.KakaopayCoupon.entity.CouponInfo;

public interface CouponService {
	
	//랜덤한 코드(16자리) N개 생성 후 데이터베이스에 보관
	List<CouponInfo> makeCoupon(CouponDTO couponDTO);
	
	//생성된 쿠폰 중 하나를 사용자에게 지급
	String updateIssueCoupon(CouponDTO couponDTO);
	
	//사용자에게 지급된 쿠폰을 조회
	List<CouponInfo> getIssueCouponList();
	
	//사용자가 지급된 쿠폰 중 하나를 사용함
	CouponInfo setUseCoupon(String cpNum, CouponDTO couponDTO);
	
	//사용자가 지급된 쿠폰 중 하나를 사용 취소함
	CouponInfo setUseCancelCoupon(String cpNum, CouponDTO couponDTO);
	
	//발급된 쿠폰 중 당일 만료된 전체 쿠폰목록을 조회
	List<CouponInfo> getExpiredCouponList();

}
