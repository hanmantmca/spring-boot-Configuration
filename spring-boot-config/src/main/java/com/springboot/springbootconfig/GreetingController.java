package com.springboot.springbootconfig;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.springbootconfig.beans.DBSettings;

@RestController
@RefreshScope
public class GreetingController {

	@Value("${my.greeting: Default Value}")
	private String greetingMsg;
	
	@Value("Static Message")
	private String staticMsg;
	
	@Value("${my.list.values}")
	private List<String> myList;
	
	@Value("#{${dbValues}}")
	private Map<String, String> mapProps;
	
	@Autowired
	private DBSettings dbSettings;
	
	@Autowired
	private Environment env;
	
	@GetMapping("/greeting")
	public String getGreeting() {
		return greetingMsg + ", "+ staticMsg +", "+ myList +", "+ mapProps;
		//return dbSettings.toString();
	}
	
	@GetMapping("/envdetails")
	private String getEnvironmentValues() {
		return env.toString();
	}
}

