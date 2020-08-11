package com.SallyLee.KakaopayCoupon.service;

import java.util.Optional;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.SallyLee.KakaopayCoupon.config.JwtTokenProvider;
import com.SallyLee.KakaopayCoupon.dto.CustomerDTO;
import com.SallyLee.KakaopayCoupon.entity.CustomerInfo;
import com.SallyLee.KakaopayCoupon.repository.CustomerRepository;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public CustomerInfo signUp(CustomerDTO customerDTO) { 	//패스워드 안전한 방법으로 계정생성
		if (customerRepository.existsByCustmId((customerDTO.getCustmID()))) {
            throw new EntityExistsException("duplicated");
        }
		
		CustomerInfo customer = CustomerInfo.builder()
								.id(customerDTO.getCustmID())
								.pw(passwordEncoder.encode(customerDTO.getCustmpw()))
								.build();
		
		return customerRepository.save(customer);
	}
	
	@Override
	public String signIn(String id, String pw) { 	//로그인하여 토큰발급
		Optional<CustomerInfo> customer = customerRepository.findByCustmId(id);
		if(!passwordEncoder.matches(pw, customer.get().getCustmPw())) {
			throw new BadCredentialsException("Password incorrect!");
		} else {
			return jwtTokenProvider.createToken(customer.get().getCustmId(), null);
		}
	}
	
}
