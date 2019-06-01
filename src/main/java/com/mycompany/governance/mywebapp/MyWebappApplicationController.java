package com.mycompany.governance.mywebapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyWebappApplicationController {

	/**
	 * FIXME something to do for PRJ-2
	 * @return
	 */
	@GetMapping("/")
	public String home(){
		return "Hello governed world";
	}
}
