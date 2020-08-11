package com.SallyLee.KakaopayCoupon.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.SallyLee.KakaopayCoupon.dto.CustomerDTO;
import com.SallyLee.KakaopayCoupon.entity.CustomerInfo;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void signUp() throws Exception {
		mockMvc.perform(post("/sign-up")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(CustomerDTO.builder().id("james").pw("aqswde").build())))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	public void signIn() throws Exception {
		String id = "sally";
		String pw = "sally12345";
		
		MvcResult result = mockMvc.perform(post("/sign-up")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(CustomerDTO.builder().id(id).pw(pw).build())))
				.andExpect(status().isOk()).andReturn();
		
		ObjectMapper objectMapper = new ObjectMapper();
		CustomerInfo resp = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<CustomerInfo>() {});
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.set("ID", resp.getCustmId());
		params.set("PW", "sally12345");
		
		mockMvc.perform(get("/sign-in")
				.params(params))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
}
