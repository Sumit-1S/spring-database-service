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
		policyRepo.save(policy);
		return "Policy Added!!";
	}

	@Override
	public List<Policy> getAllPolicy() {
		return (List<Policy>)policyRepo.findAll();
	}

	@Override
	public Optional<Policy> getPolicyById(int policyId) {
		Optional<Policy> p=policyRepo.findById(policyId);
		if(p.isPresent()) {
			return p;
		}
		return Optional.empty();
	}

	@Override
	public List<Policy> getPolicyByIds(List<Integer> policyLs) {
		return policyRepo.findByPolicyIdIn(policyLs);
	}

}
