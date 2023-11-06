package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public List<Policy> getAllPolicy(){
		return pService.getAllPolicy();
	}
	
	@GetMapping("/getAllPolicyByList/{policyIdArr}")
	public List<Policy> getAllPolicyByPolicyId(@PathVariable List<Integer> policyIdArr){
		List<Policy>ls = pService.getPolicyByIds(policyIdArr);
		System.out.println(ls);
		return ls;
	}
	
	@PostMapping("/addPolicy")
	public Policy addPolicy(@RequestBody Policy policy) {
		pService.registerPolicy(policy);
		return policy;
	}
	
//	@GetMapping("/getAllPolicyByClientUsername/{clientUsername}")
//	public List<Policy>
	
	

}
