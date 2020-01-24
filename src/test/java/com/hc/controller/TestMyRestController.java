package com.hc.controller;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.hc.model.Member;
import com.hc.service.MemberService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = MyRestController.class)
public class TestMyRestController {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MemberService memberService;
	
	@SuppressWarnings("unchecked")
	List<Member> mockMember = (List<Member>) new Member(1,"Address 1",new Date(),"pandet@aetna.com");

	String memberJson = "{\"id\":\"1\",\"address\":\"10 Lane\",\"dob\":\"12/12/12\",\"emailAddress\":\"12/12/12\"}";

	@Test
	public void retrieveAllMemberDetails() throws Exception {

		Mockito.when(
				memberService.getAllMembers()).thenReturn(mockMember);
		
		

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/members/1").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{id:1,address:Address 1,dob:12/12/2012,emailAddress:pandet@aetna.com}";

		

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}

	/*
	@Test
	public void createMember() throws Exception {
		Member mockMember = new Member(1,"Address 1",new Date(),"pandet@aetna.com");

		
		Mockito.when(
				memberService.saveOrUpdate(Mockito.anyString(),
						Mockito.any(Member.class))).thenReturn(mockMember);

		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/students/Student1/courses")
				.accept(MediaType.APPLICATION_JSON).content(memberJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

		assertEquals("http://localhost/members/1",
				response.getHeader(HttpHeaders.LOCATION));

	}
	 */

}
