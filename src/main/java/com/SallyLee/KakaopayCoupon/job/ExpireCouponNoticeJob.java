package com.SallyLee.KakaopayCoupon.job;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.SallyLee.KakaopayCoupon.entity.CouponInfo;
import com.SallyLee.KakaopayCoupon.repository.CouponRepository;

@Component
public class ExpireCouponNoticeJob {

	@Autowired
	private CouponRepository couponRepository;
	
	// 발급된쿠폰 중 3일 전 만료 쿠폰을 오전 10시에 전송(배치작업으로 실행)
	@Scheduled(cron= "0 0 10 * * *")
	public void expiredCouponJobSch() {

 		List<CouponInfo> couponList = couponRepository.findByExpireDtAndUseYn(false,LocalDate.now().minusDays(3));
		
		for (int i=0; i< couponList.size(); i++) {
			
			CouponInfo coupon = new CouponInfo();
			coupon = couponList.get(i);
			System.out.println("쿠폰번호 :"+coupon.getCpNum()+"의 만료일자가 3일 남았습니다. 전송번호 핸드폰번호는 "+coupon.getCustmHp()+ "입니다.");
			
		}
		
	}
}
