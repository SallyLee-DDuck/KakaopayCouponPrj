package com.SallyLee.KakaopayCoupon.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SallyLee.KakaopayCoupon.dto.CouponDTO;
import com.SallyLee.KakaopayCoupon.entity.CouponInfo;
import com.SallyLee.KakaopayCoupon.repository.CouponRepository;
import com.SallyLee.KakaopayCoupon.utils.CouponIssueUtils;

@Service
public class CouponServiceImpl implements CouponService{
	
	@Autowired
	private CouponRepository couponRepository;
	
	@Override
	@Transactional
	public List<CouponInfo> makeCoupon(CouponDTO couponDTO) { //랜덤한 코드(16자리)-CouponIssueUtils사용; N개 생성 후 데이터베이스에 보관
		List<CouponInfo> results = new ArrayList<CouponInfo>();
		CouponIssueUtils couponIssue = new CouponIssueUtils(16); //16자리 쿠폰 생성
		for(int i=0; i<couponDTO.getTotCnt(); i++) {
			CouponInfo coupon = CouponInfo.builder()
						.number(couponIssue.createCoupon())
						.useYn(false)
						.issueYn(false)
						.expireDt(couponIssue.getExpireDt())
						.build();
			
			CouponInfo result = couponRepository.save(coupon);
			results.add(result);
		}
		return results;
	}
	
	@Override
	public String updateIssueCoupon(CouponDTO couponDTO) { 	//생성된 쿠폰 중 하나를 사용자에게 지급(생성된 데이터베이스의 데이터를 update)
		List<CouponInfo> noIssueCouponList = couponRepository.findByIssueYn(false);
		//미발행쿠폰을 찾은 후, 발행하고 true로 업데이트
		if(noIssueCouponList.size() > 0) {
			CouponInfo issueCoupon = noIssueCouponList.get(0);
			issueCoupon.setIssueYn(true);
			issueCoupon.setCustmHp(couponDTO.getCustmHp());
			return couponRepository.save(issueCoupon).getCpNum();
		} else {
			return "쿠폰을 추가발행해주시기 바랍니다";
		}
	}
	
	@Override
	public List<CouponInfo> getIssueCouponList() {	//사용자에게 지급된 쿠폰을 조회
		return couponRepository.findByIssueYn(true);
	}
	
	@Override
	public CouponInfo setUseCoupon(String cpNum, CouponDTO couponDTO) { 	//사용자가 지급된 쿠폰 중 하나를 사용함
		CouponInfo useCoupon = couponRepository.findByCpNum(cpNum);
		useCoupon.setUseYn(true);
		return couponRepository.save(useCoupon);
	}
	
	@Override
	public CouponInfo setUseCancelCoupon(String cpNum, CouponDTO couponDTO) { 	//사용자가 지급된 쿠폰 중 하나를 사용 취소함
		CouponInfo useCancelCoupon = couponRepository.findByCpNum(cpNum);
		useCancelCoupon.setUseYn(false);
		return couponRepository.save(useCancelCoupon);
	}
	
	@Override
	//발급된 쿠폰 중 당일 만료된 전체 쿠폰목록을 조회
	public List<CouponInfo> getExpiredCouponList() {
		LocalDate sysdate = LocalDate.now();
		return couponRepository.findByExpireDt(sysdate);
	}
}
