/**
 * 
 */
package com.featuriz.sbm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Sudhakar Krishnan <featuriz@gmail.com>
 * @Copyright 2009 - 2021 Featuriz
 * @DateTime 21-Nov-202111:29:37 pm
 */
@Controller
public class IndexController {

	@RequestMapping("/")
	public String homePage() {
		return "home";
	}

	@RequestMapping("/login")
	public String loginPage() {
		return "login";
	}

	@RequestMapping("/user")
	public String userPage() {
		return "user";
	}

	@RequestMapping("/admin")
	public String adminPage() {
		return "admin";
	}

	@RequestMapping("/all")
	public String allPage() {
		return "all";
	}

	@RequestMapping("/anonymous")
	public String anonymousPage() {
		return "anonymous";
	}

	@RequestMapping("/accessDenied")
	public String deniedPage() {
		return "denied";
	}
}
