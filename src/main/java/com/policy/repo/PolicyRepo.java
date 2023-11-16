package com.policy.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.entity.Policy;
import com.entity.PolicyType;

@Repository
public interface PolicyRepo extends CrudRepository<Policy, Integer> {
	List<Policy> findByPolicyIdIn(List<Integer> policyLs);
}
