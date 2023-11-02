package com.policy.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.entity.PolicyOwned;
import com.entity.Status;

@Repository
public interface PolicyOwnedRepo extends CrudRepository<PolicyOwned, Integer> {
	@Modifying
	@Query("update PolicyOwned p set p.status = :status where p.clientUsername = :username and p.policyOwnedId = :policyOwnedId")
	void updatePolicyStatus(@Param("username")String clientUsername,@Param("policyOwnedId")Integer policyOwnedId,@Param("status")Status status);
	
	@Query("select p from PolicyOwned p where p.clientUsername = :username")
	List<PolicyOwned> getAllPolicyByClientUsername(@Param("username")String username);

	@Query("select p from PolicyOwned p where p.policy.policyId = :policyId")
	Optional<List<PolicyOwned>> getAllPolicyByPolicyId(@Param("policyId")Integer policyId);

//	void updatePolicyStatus(String clientUsername, Status status);

	
	
}
