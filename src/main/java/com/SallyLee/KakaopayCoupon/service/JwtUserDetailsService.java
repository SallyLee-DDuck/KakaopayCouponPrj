package com.SallyLee.KakaopayCoupon.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.SallyLee.KakaopayCoupon.entity.CustomerInfo;
import com.SallyLee.KakaopayCoupon.repository.CustomerRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		Optional<CustomerInfo> customer = customerRepository.findByCustmId(id);
		
		return new org.springframework.security.core.userdetails.User(customer.get().getCustmId(), customer.get().getCustmPw(), getAuthorities());
	}

	private Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
	}
}
