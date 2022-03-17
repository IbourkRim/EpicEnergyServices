package it.epicode.be.energy;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ClienteControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@WithAnonymousUser
	public void listaClientiWhenUtenteIsAnonymous() throws Exception {
		this.mockMvc.perform(get("/api/clienti")).andExpect(status().isUnauthorized());
	}

	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "USER")
	public void deleteClienteByUser() throws Exception {
		this.mockMvc.perform(delete("/api/cliente/1"))
			.andExpect(status().isForbidden());
	}


}
