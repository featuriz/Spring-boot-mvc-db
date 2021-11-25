/**
 * 
 */
package com.featuriz.sbm.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.featuriz.sbm.entity.User;
import com.featuriz.sbm.service.UserService;

/**
 * @author Sudhakar Krishnan <featuriz@gmail.com>
 * @Copyright 2009 - 2021 Featuriz
 * @DateTime 25-Nov-202110:31:27 am
 */
@Controller
public class LoginController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("msg", "Your username and password are invalid.");

		if (logout != null)
			model.addAttribute("msg", "You have been logged out successfully.");

		return "login";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registration(Model model) {
		model.addAttribute("user", new User());
		return "registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String createNewUser(@Valid User user, BindingResult bindingResult, Model model) {
		User userExists = userService.findUserByUserName(user.getUserName());
		if (userExists != null) {
			bindingResult.rejectValue("userName", "error.user",
					"There is already a user registered with the user name provided");
		}
		if (bindingResult.hasErrors()) {
			return "registration";
		}
		userService.saveUser(user);
		model.addAttribute("successMessage", "User has been registered successfully");
		model.addAttribute("user", new User());
		return "registration";
	}

	@RequestMapping(value = "/admin/home", method = RequestMethod.GET)
	public String home(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByUserName(auth.getName());
		model.addAttribute("userName", "Welcome " + user.getUserName() + "/" + user.getName() + " " + user.getLastName()
				+ " (" + user.getEmail() + ")");
		model.addAttribute("adminMessage", "Content Available Only for Users with Admin Role");
		return "admin/home";
	}
}
