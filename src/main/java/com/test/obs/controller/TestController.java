package com.test.obs.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/test")
public class TestController {

	@GetMapping(value = "/version")
	public Map<String, Object> version() {
		Map<String, Object> result = new HashMap<>();
		result.put("version", "1.0.1");
		result.put("Date", new Date());
		result.put("Currency", Currency.getInstance(Locale.getDefault()));
		result.put("Timezone", TimeZone.getDefault());
		return result;
	}
}