/**
 *
 */
package com.featuriz.sbm.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.featuriz.sbm.entity.Role;
import com.featuriz.sbm.entity.User;
import com.featuriz.sbm.repository.RoleRepository;
import com.featuriz.sbm.repository.UserRepository;

/**
 * @author Sudhakar Krishnan <featuriz@gmail.com>
 * @Copyright 2009 - 2021 Featuriz
 * @DateTime 24-Nov-20218:35:14 pm
 */
@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	/**
	 * THIS IS ONLY FOR TESTING. SHORTCUT.
	 * @param mockUserRepository
	 * @param mockRoleRepository
	 * @param mockBCryptPasswordEncoder
	 */
	public UserService(UserRepository mockUserRepository, RoleRepository mockRoleRepository,
			BCryptPasswordEncoder mockBCryptPasswordEncoder) {
		this.userRepository = mockUserRepository;
		this.roleRepository = mockRoleRepository;
		this.bCryptPasswordEncoder = mockBCryptPasswordEncoder;
	}

	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public User findUserByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	public User saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(true);
		Role userRole = roleRepository.findByRole("ROLE_USER");
		user.setRoles(new HashSet<>(Arrays.asList(userRole)));
		return userRepository.save(user);
	}
}
