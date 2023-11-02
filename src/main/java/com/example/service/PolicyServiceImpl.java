package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Policy;
import com.policy.repo.PolicyRepo;

@Service
public class PolicyServiceImpl implements PolicyService {
	@Autowired
	PolicyRepo policyRepo;

	@Override
	public String registerPolicy(Policy policy) {
		// TODO Auto-generated method stub
		policyRepo.save(policy);
		return "Policy Added!!";
	}

	@Override
	public List<Policy> getAllPolicy() {
		// TODO Auto-generated method stub
		return (List<Policy>)policyRepo.findAll();
	}

	@Override
	public Optional<Policy> getPolicyById(int policyId) {
		// TODO Auto-generated method stub
		Optional<Policy> p=policyRepo.findById(policyId);
		if(p.isPresent()) {
			return p;
		}
		return Optional.empty();
	}

}
