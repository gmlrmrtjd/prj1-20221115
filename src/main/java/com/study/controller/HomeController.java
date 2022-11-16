package com.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

	@RequestMapping("")
	@ResponseBody
	public String home() {
		return "welcome home!";
	}
	
	@RequestMapping("index")
	public void index() {
		// forward to /WEB=INF/views/index.jsp
	}
}
