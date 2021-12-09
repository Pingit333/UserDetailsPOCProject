package com.mypoc.userdetailtracker.user.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = Replace.NONE)
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
class UserControllerMVCTest {

	@Autowired
	private UserController userController;

	@LocalServerPort
	private int port;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	 void contextLoads() throws Exception {
		assertThat(userController).isNotNull();
	}

	@Test
	 void randomPortTest() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/users", String.class)).contains("[]");
	}

	@Test
	 void retriveNoDataTest() throws Exception {
		this.mockMvc.perform(get("/users")).andDo(print()).andExpect(status().isFound())
				.andExpect(content().string(containsString("[]")));
	}
	
	@Test
	 void getAllEmployeesAPI() throws Exception 
	{
	mockMvc.perform( MockMvcRequestBuilders
		      .get("/users")
		      .accept(MediaType.APPLICATION_JSON))
		      .andDo(print())
		      .andExpect(status().isFound());
	}
	
}