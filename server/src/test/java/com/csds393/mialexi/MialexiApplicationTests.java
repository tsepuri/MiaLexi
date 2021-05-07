package com.csds393.mialexi;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

class MialexiApplicationTests {

	@Test
	void hello() {
		assertTrue(true);
	}

	public static void main(String[] args) {
		MialexiApplicationTests m1 = new MialexiApplicationTests();
		m1.hello();
	}
	/*
	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}
	*/

}
