/**
 * 
 */
package com.featuriz.sbm.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.featuriz.sbm.entity.User;
import com.featuriz.sbm.repository.RoleRepository;
import com.featuriz.sbm.repository.UserRepository;

/**
 * @author Sudhakar Krishnan <featuriz@gmail.com>
 * @Copyright 2009 - 2021 Featuriz
 * @DateTime 24-Nov-20219:14:45 pm
 */
@WebMvcTest(UserService.class)
public class UserServiceTest {
	@MockBean
	private UserRepository mockUserRepository;
	@MockBean
	private RoleRepository mockRoleRepository;
	@MockBean
	private BCryptPasswordEncoder mockBCryptPasswordEncoder;

	@MockBean
    private MyUserDetailsService userDetailsService;

	@MockBean
	private UserService userServiceUnderTest;
	private User user;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		userServiceUnderTest = new UserService(mockUserRepository, mockRoleRepository, mockBCryptPasswordEncoder);
		user = User.builder().id(1).name("Gustavo").lastName("Ponce").email("test@test.com").build();

		Mockito.when(mockUserRepository.save(any())).thenReturn(user);
		Mockito.when(mockUserRepository.findByEmail(anyString())).thenReturn(user);
	}

	@Test
	public void testFindUserByEmail() {
		// Setup
		final String email = "test@test.com";

		// Run the test
		final User result = userServiceUnderTest.findUserByEmail(email);

		// Verify the results
		assertEquals(email, result.getEmail());
	}

	@Test
	public void testSaveUser() {
		// Setup
		final String email = "test@test.com";

		// Run the test
		User result = userServiceUnderTest.saveUser(User.builder().build());

		// Verify the results
		assertEquals(email, result.getEmail());
	}
}
