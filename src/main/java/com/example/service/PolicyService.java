package com.example.service;

import java.util.List;
import java.util.Optional;

import com.entity.Policy;

public interface PolicyService {
	public String registerPolicy(Policy policy);
	public List<Policy> getAllPolicy();
	public Optional<Policy> getPolicyById(int policyId);
	
	

}
