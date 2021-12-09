package com.mypoc.userdetailtracker.user.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

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
	void getAllUserAPITest() throws Exception {
		Users checkuser = new Users();
		checkuser.setName("Himu");
		checkuser.setSurname("Shinde");
		checkuser.setPincode(452011);
		checkuser.setBirthDate(new Date());
		checkuser.setDateOfJoining(new Date());
		checkuser.setDeleted(false);

		// ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		// String resultAll = ow.writeValueAsString(checkuser);
		// System.out.println("usersObjectDetailsTest() : \n" + resultAll);

		when(userRepository.findAll()).thenReturn(Arrays.asList(checkuser));

		mockMvc.perform(MockMvcRequestBuilders.get("/users").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isFound()).andExpect(MockMvcResultMatchers.jsonPath("$[*]").exists());
	}
	
	@Test
	void getUserByIdAPITest() throws Exception {
		Users checkuser = new Users();
		checkuser.setName("Himu");
		checkuser.setSurname("Shinde");
		checkuser.setPincode(452011);
		checkuser.setBirthDate(new Date());
		checkuser.setDateOfJoining(new Date());
		checkuser.setDeleted(false);

		when(userRepository.findById(0)).thenReturn( Optional.of(checkuser));

		mockMvc.perform(MockMvcRequestBuilders.get("/users/findbyid")
				.param("id","0")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isFound())
				.andExpect(MockMvcResultMatchers.jsonPath("$[*]").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id", is(checkuser.getId())))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").isNotEmpty())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name", is(checkuser.getName())))
				.andExpect(MockMvcResultMatchers.jsonPath("$.surname").isNotEmpty())
				.andExpect(MockMvcResultMatchers.jsonPath("$.surname", is(checkuser.getSurname())))
				.andExpect(MockMvcResultMatchers.jsonPath("$.pincode").isNotEmpty())
				.andExpect(MockMvcResultMatchers.jsonPath("$.pincode").value(checkuser.getPincode()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.pincode").isNotEmpty())
				.andExpect(MockMvcResultMatchers.jsonPath("$.birthDate")
						.value(new SimpleDateFormat("yyyy-MM-dd").format(checkuser.getBirthDate())))
				.andExpect(MockMvcResultMatchers.jsonPath("$.dateOfJoining").isNotEmpty())
				.andExpect(MockMvcResultMatchers.jsonPath("$.dateOfJoining")
						.value(new SimpleDateFormat("yyyy-MM-dd").format(checkuser.getDateOfJoining())));
	}

	@Test
	void getUserByNameAPITest() throws Exception {
		Users checkuser = new Users();
		checkuser.setName("Himu");
		checkuser.setSurname("Shinde");
		checkuser.setPincode(452011);
		checkuser.setBirthDate(new Date());
		checkuser.setDateOfJoining(new Date());
		checkuser.setDeleted(false);

		ObjectWriter ow = new ObjectMapper().writer();
		String resultAll = ow.writeValueAsString(checkuser);

		when(userRepository.findByName(checkuser.getName())).thenReturn(Arrays.asList(checkuser));

		mockMvc.perform(MockMvcRequestBuilders.get("/users/findbyname").param("name", checkuser.getName())
				.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isFound())
				.andExpect(content().string(containsString(resultAll)));
	}
	
	
	@Test
	void createUserAPITest() throws Exception 
	{	
		Users checkuser = new Users();
		checkuser.setName("Himi");
		checkuser.setSurname("Shinde");
		checkuser.setPincode(452011);
		checkuser.setBirthDate(new Date());
		checkuser.setDateOfJoining(new Date());
		checkuser.setDeleted(false);

		ObjectWriter ow = new ObjectMapper().writer();
		String resultAll = ow.writeValueAsString(checkuser);
		
		when(userRepository.save(checkuser)).thenReturn(checkuser);

		mockMvc.perform( MockMvcRequestBuilders
	      .post("/users")
	      .content(resultAll)
	      .contentType(MediaType.APPLICATION_JSON)
	      .accept(MediaType.APPLICATION_JSON))
	      .andExpect(status().isCreated())
	      .andExpect(content().string(containsString(resultAll)))
	      .andDo(print());
	}
	
}