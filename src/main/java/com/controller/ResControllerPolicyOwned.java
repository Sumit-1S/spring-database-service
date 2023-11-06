package com.controller;


import java.util.List;

import javax.transaction.Transactional;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Policy;
import com.entity.PolicyOwned;
import com.example.model.UpdatePolicyRequest;
import com.example.service.PolicyOwnedService;


@RestController
@RequestMapping("/policyOwnedApp")
public class ResControllerPolicyOwned {
	@Autowired
	PolicyOwnedService policyOwnedService;
	
	@PostMapping("/applyPolicy")
	@ResponseBody
	public String applyPolicy(@RequestBody PolicyOwned policyOwned) {
		policyOwnedService.applyPolicy(policyOwned);
		return "Policy Applied";
	}
	
	@PostMapping("/applyPolicyList")
	@ResponseBody
	public String applyPolicyList(@RequestBody List<PolicyOwned> policyOwned) {
		policyOwnedService.applyPolicyList(policyOwned);
		return "Policy Applied";
	}
	
	@GetMapping("/getAllAppliedPolicy")
	public List<PolicyOwned> getAllAppliedPolicy() {
		return policyOwnedService.getAllAppliedPolicy();
		
	}

	@GetMapping("/getAllPolicyByClientUsername/{clientUsername}")
	public List<PolicyOwned> getAllPolicyByClientUsername(@PathVariable String clientUsername){
		return policyOwnedService.getAllPolicyByClientUsername(clientUsername);
	}
	
	@PostMapping("/getAllPolicyByPolicyId")
	public List<PolicyOwned> getAllPolicyByPolicyId(Integer policyId){
		return policyOwnedService.getAllPolicyByPolicyId(policyId);
	}
	
	@PutMapping("/updateAppliedPolicy")
	@Transactional
	public String updateAppliedPolicy(@ModelAttribute UpdatePolicyRequest policy) {
		return policyOwnedService.updateAppliedPolicy(policy);
	}
	
	@GetMapping("/updateAppliedPolicy/{policyId}")
	@ResponseBody
	public PolicyOwned getUpdateAppliedPolicy(@PathVariable String policyId) {
		return policyOwnedService.getPolicyById(policyId);
	}
	
}
