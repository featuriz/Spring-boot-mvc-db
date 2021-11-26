/**
 *
 */
package com.featuriz.sbm;

//import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import com.featuriz.sbm.service.MyUserDetailsService;
import com.featuriz.sbm.service.UserService;

/**
 * @author Sudhakar Krishnan <featuriz@gmail.com>
 * @Copyright 2009 - 2021 Featuriz
 * @DateTime 24-Nov-202112:03:43 pm
 */
@WebMvcTest
public class WebLayerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private UserService userServiceUnderTest;
	@MockBean
	private BCryptPasswordEncoder mockBCryptPasswordEncoder;
	@MockBean
    private MyUserDetailsService userDetailsService;

	@Test
	public void shouldReturnDefaultMessage() throws Exception {
		this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk());
//				.andExpect(content().string(containsString("Hello, World")));
	}
}
