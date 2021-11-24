/**
 * 
 */
package com.featuriz.sbm;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import com.featuriz.sbm.controller.IndexController;

/**
 * @author Sudhakar Krishnan <featuriz@gmail.com>
 * @Copyright 2009 - 2021 Featuriz 
 * @DateTime 24-Nov-202111:40:29 am
 */
@WebMvcTest
public class SmokeTest {
	@Autowired
	private IndexController controller;

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
}
