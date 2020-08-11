package com.SallyLee.KakaopayCoupon.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SallyLee.KakaopayCoupon.entity.CustomerInfo;

public interface CustomerRepository extends JpaRepository<CustomerInfo, Long>{
	
	Optional<CustomerInfo> findByCustmId(String id);
	Boolean existsByCustmId(String id);
}

