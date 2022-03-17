package it.epicode.be.energy;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@WithAnonymousUser
	public void loginNoBody() throws Exception {
		this.mockMvc.perform(post("/auth/login")).andExpect(status().isBadRequest());
	}

	@Test
	@WithAnonymousUser
	public void loginNoBody2() throws Exception {
		this.mockMvc.perform(post("/auth/signup")).andExpect(status().isBadRequest());
	}
}
