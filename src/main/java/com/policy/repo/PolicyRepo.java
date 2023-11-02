package com.policy.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.entity.Policy;

@Repository
public interface PolicyRepo extends CrudRepository<Policy, Integer> {

}
