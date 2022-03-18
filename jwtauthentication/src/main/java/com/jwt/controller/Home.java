package com.jwt.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class Home {

	@RequestMapping(value = "/welcome")
	public String welcome() {
		String text = "It is a private page ";
		text += "It is not allowed for un-authenticated users";
		return text;
	}

	@RequestMapping(value = "/getUser")
	public String getUser() {
		return "{\"name\":\"Faisal\"}";
	}

}
