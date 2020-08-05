package com.example.kubernetesdemo.kubernetesdemo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class IndexController {

	@Value("${userservice.url}")
	private String userServiceUrl;
	
	@GetMapping("/users")
	public User[] users() {
		
		RestTemplate restTemplate = new RestTemplate();
		String url = userServiceUrl+"/users";
		System.out.println("url:"+url);
		ResponseEntity<User[]> response = restTemplate
		  .getForEntity(url, User[].class);
		
		return response.getBody();
	}
	
	
	@GetMapping("/user/{id}")
	public UserDetails user(@PathVariable String id){
		
		RestTemplate restTemplate = new RestTemplate();
		String url = userServiceUrl+"/user/"+id;
		System.out.println("url:"+url);
		return restTemplate
		  .getForObject(url, UserDetails.class);
		
	}
	
}
