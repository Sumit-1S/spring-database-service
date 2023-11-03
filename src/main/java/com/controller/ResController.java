package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.service.PolicyService;

@RestController
@RequestMapping("/policyApp")

public class ResController {
	
	@Autowired
	PolicyService pService;
	
	
	

}
