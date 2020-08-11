package com.SallyLee.KakaopayCoupon.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.SallyLee.KakaopayCoupon.dto.CouponDTO;
import com.SallyLee.KakaopayCoupon.entity.CouponInfo;


@SpringBootTest
@AutoConfigureMockMvc
public class CouponControllerTest {

	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@WithMockUser(username = "sally", roles = "USER")
	public void postCouponList() throws Exception {
		
		mockMvc.perform(post("/couponApi/couponList")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(CouponDTO.builder().cnt(50).build())))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(username = "sally", roles = "USER")
	public void getCouponList() throws Exception {
		
		mockMvc.perform(get("/couponApi/couponList"))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(username = "sally", roles = "USER")
	public void putCoupons() throws Exception {
		
		mockMvc.perform(post("/couponApi/couponList")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(CouponDTO.builder().cnt(20).build())));
		
		mockMvc.perform(put("/couponApi/couponList")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(CouponDTO.builder().Hp("010-1111-1111").build())))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(username = "sally", roles = "USER")
	public void putUseStatus() throws Exception {
		
		MvcResult result = mockMvc.perform(post("/couponApi/couponList")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(CouponDTO.builder().cnt(1).build())))
				.andExpect(status().isOk()).andReturn();
		
		ObjectMapper objectMapper = new ObjectMapper();
		// solution: Cannot construct instance of java.time.LocalDate
		objectMapper.registerModule(new JavaTimeModule()); 
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		List<CouponInfo> resp = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<CouponInfo>>() {});
		String cpNum = resp.get(0).getCpNum();
		mockMvc.perform(put("/couponApi/couponList/"+cpNum+"/usetrue")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(CouponDTO.builder().useYn(true).build())))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(username = "sally", roles = "USER")
	public void expiredCoupons() throws Exception {
		
		mockMvc.perform(post("/couponApi/couponList")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(CouponDTO.builder().cnt(10).build())));
		
		mockMvc.perform(get("/couponApi/couponList/expiredCouponList"))
				.andDo(print())
				.andExpect(status().isOk());
		
	}
	
}
