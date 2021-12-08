package com.mypoc.userdetailtracker.user.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mypoc.userdetailtracker.repo.UserRepository;
import com.mypoc.userdetailtracker.user.bean.Users;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
class UserControllerMockMVCTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserRepository userRepository;

	@Test
	void getAllUserAPI() throws Exception {
		Users checkuser = new Users();
		checkuser.setName("Himu");
		checkuser.setSurname("Shinde");
		checkuser.setPincode(452011);
		checkuser.setBirthDate(new Date());
		checkuser.setDateOfJoining(new Date());
		checkuser.setDeleted(false);

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String resultAll = ow.writeValueAsString(checkuser);
		System.out.println("usersObjectDetailsTest() : \n" + resultAll);

		when(userRepository.findAll()).thenReturn(Arrays.asList(checkuser));

		mockMvc.perform(MockMvcRequestBuilders.get("/users").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isFound()).andExpect(MockMvcResultMatchers.jsonPath("$[*]").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].id").isNotEmpty());
	}

	@Test
	void getUserDetailsAPITest() throws Exception {
		Users checkuser = new Users();
		checkuser.setName("Himu");
		checkuser.setSurname("Shinde");
		checkuser.setPincode(452011);
		checkuser.setBirthDate(new Date());
		checkuser.setDateOfJoining(new Date());
		checkuser.setDeleted(false);

		ObjectWriter ow = new ObjectMapper().writer();
		String resultAll = ow.writeValueAsString(checkuser);
		System.out.println("usersObjectDetailsTest() : \n" + resultAll);

		when(userRepository.findAll()).thenReturn(Arrays.asList(checkuser));

		mockMvc.perform(MockMvcRequestBuilders.get("/users").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isFound()).andExpect(content().string(containsString(resultAll)));
	}
}