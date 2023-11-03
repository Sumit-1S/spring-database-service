package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.entity.Policy;
import com.example.service.PolicyService;


@RestController
@RequestMapping("/policyApp")

public class ResController {
	
	@Autowired
	PolicyService pService;
	
	@GetMapping("/getAllPolicy")
	public ResponseEntity<List<Policy>> getAllPolicy() {
		return new ResponseEntity<List<Policy>>(pService.getAllPolicy(), HttpStatus.OK);
	}
	
	@PostMapping(value = "/addPolicy", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Policy> addPolicy(@RequestBody Policy policy) {
		System.out.println(policy);
		return new ResponseEntity<Policy>(pService.addPolicy(policy), HttpStatus.OK);
	}
	

}
