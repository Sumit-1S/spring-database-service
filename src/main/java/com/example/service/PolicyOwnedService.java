package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.entity.Policy;
import com.entity.PolicyOwned;
import com.example.model.UpdatePolicyRequest;

public interface PolicyOwnedService {
	public String applyPolicy(PolicyOwned policyOwned);
	public List<PolicyOwned> getAllAppliedPolicy();
	public List<PolicyOwned> getAllPolicyByClientUsername(String clientUsername);
	public ArrayList<PolicyOwned> getAllPolicyByPolicyId(Integer policyId);
	public String updateAppliedPolicy(UpdatePolicyRequest policyOwned);
	public PolicyOwned getPolicyById(String policyId);
	public String applyPolicyList(List<PolicyOwned> policyOwned);

}
