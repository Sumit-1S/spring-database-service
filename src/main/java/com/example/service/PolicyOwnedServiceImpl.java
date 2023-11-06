package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Policy;
import com.entity.PolicyOwned;
import com.example.model.UpdatePolicyRequest;
import com.policy.repo.PolicyOwnedRepo;

@Service
public class PolicyOwnedServiceImpl implements PolicyOwnedService {
	@Autowired
	PolicyOwnedRepo policyOwnedRepo;

	@Override
	public String applyPolicy(PolicyOwned policyOwned) {
		// TODO Auto-generated method stub
		policyOwnedRepo.save(policyOwned);
		return "Policy Owned Added!!";
	}

	@Override
	public List<PolicyOwned> getAllAppliedPolicy() {
		// TODO Auto-generated method stub
		System.out.println((List<PolicyOwned>)policyOwnedRepo.findAll());
		return (List<PolicyOwned>)policyOwnedRepo.findAll();
	}

	@Override
	public List<PolicyOwned> getAllPolicyByClientUsername(String clientUsername) {
		return policyOwnedRepo.getAllPolicyByClientUsername(clientUsername);
	}

	@Override
	public ArrayList<PolicyOwned> getAllPolicyByPolicyId(Integer policyId) {
		// TODO Auto-generated method stub
		Optional<List<PolicyOwned>> l=policyOwnedRepo.getAllPolicyByPolicyId(policyId);
		if(l.isPresent()) {
			return new ArrayList<PolicyOwned>(l.get());
		}
		return new ArrayList<>();
	}

	@Override
	@Transactional
	public String updateAppliedPolicy(UpdatePolicyRequest policyOwned) {
		policyOwnedRepo.updatePolicyStatus(policyOwned.getClientUsername(),policyOwned.getPolicyOwnedId() , policyOwned.getStatus());
		return "Policy Updated";
	}

	@Override
	public PolicyOwned getPolicyById(String policyId) {
		return policyOwnedRepo.findById(Integer.valueOf(policyId)).get();
	}

	@Override
	public String applyPolicyList(List<PolicyOwned> policyOwned) {
		policyOwnedRepo.saveAll(policyOwned);
		return "Policies Applied";
	}

}
