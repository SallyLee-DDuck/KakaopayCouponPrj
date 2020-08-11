package com.SallyLee.KakaopayCoupon.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SallyLee.KakaopayCoupon.entity.CouponInfo;

public interface CouponRepository extends JpaRepository<CouponInfo, Long>{
	
	CouponInfo findByCpNum(String cpNum);
	List<CouponInfo> findByExpireDt(LocalDate expireDt);
	List<CouponInfo> findByIssueYn(boolean issueYn);
	List<CouponInfo> findByExpireDtAndUseYn(boolean useYn, LocalDate expireDt);
}
