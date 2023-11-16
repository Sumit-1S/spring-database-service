package com.example.service;


import static org.mockito.ArgumentMatchers.any;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.entity.Policy;
import com.entity.PolicyOwned;
import com.entity.PolicyType;
import com.entity.Status;
import com.example.service.PolicyOwnedService;
import com.example.service.PolicyOwnedServiceImpl;
import com.policy.repo.PolicyOwnedRepo;
import com.example.model.UpdatePolicyRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = PolicyOwned.class)
@ExtendWith(MockitoExtension.class)
public class PolicyOwnedServiceImplTest{

    @InjectMocks
    private PolicyOwnedService serviceClass = new PolicyOwnedServiceImpl();

    @Mock
    private PolicyOwnedRepo policyOwnedRepo;

    private PolicyOwned policyOwned1;
    private PolicyOwned policyOwned2;

    @BeforeEach
    public void beforeTest() {
        MockitoAnnotations.openMocks(this);

        policyOwned1 = new PolicyOwned();
        policyOwned1.setClientUsername("Samrat Rode");
        policyOwned1.setClientAge(23);
        policyOwned1.setClientCity("Bangalore");
        policyOwned1.setClientMedicalHistory("No major medical history");
        Date d1 = Date.valueOf("2023-11-01");
        policyOwned1.setDate(d1);
        policyOwned1.setPremium(1000.0);
        policyOwned1.setStatus(Status.ACTIVE);
        policyOwned1.setClientTobaccoUser(false);

        Policy policy1 = new Policy();
        policy1.setPolicyId(1);
        policy1.setPolicyName("P01");
        policy1.setPolicyDesc("Isko le liya to life jinga la la");
        policy1.setDuration(3);
        policy1.setPolicyType(PolicyType.ACCIDENTAL);
        policy1.setAmount(20000.00);

        policyOwned2 = new PolicyOwned();
        policyOwned2.setClientUsername("ABC");
        policyOwned2.setClientAge(23);
        policyOwned2.setClientCity("Bangalore");
        policyOwned2.setClientMedicalHistory("No major medical history");
        Date d2 = Date.valueOf("2023-11-01");
        policyOwned2.setDate(d2);
        policyOwned2.setPremium(1000.0);
        policyOwned2.setStatus(Status.ACTIVE);
        policyOwned2.setClientTobaccoUser(false);

        Policy policy2 = new Policy();
        policy2.setPolicyId(1);
        policy2.setPolicyName("P01");
        policy2.setPolicyDesc("Isko le liya to life jinga la la");
        policy2.setDuration(3);
        policy2.setPolicyType(PolicyType.ACCIDENTAL);
        policy2.setAmount(20000.00);
    }

    @Test
    public void testApplyPolicy() {
        Mockito.when(policyOwnedRepo.save(Mockito.any(PolicyOwned.class))).thenReturn(policyOwned1);

        String result = serviceClass.applyPolicy(policyOwned1);

        Mockito.verify(policyOwnedRepo).save(Mockito.any(PolicyOwned.class));

        assertEquals("Policy Owned Added!!", result);
    }
    @Test
    public void testGetAllPolicyByClientUsername() {
String sampleClientUsername = "Samrat";
List<PolicyOwned> policyList = new ArrayList<>();
policyList.add(policyOwned1);
policyList.add(policyOwned2);

Mockito.when(policyOwnedRepo.getAllPolicyByClientUsername(sampleClientUsername)).thenReturn(policyList);

List<PolicyOwned> result = serviceClass.getAllPolicyByClientUsername(sampleClientUsername);

Mockito.verify(policyOwnedRepo).getAllPolicyByClientUsername(sampleClientUsername);

assertEquals(policyList, result);
}
    @Test
    public void testGetAllAppliedPolicy() {
        List<PolicyOwned> policyList = new ArrayList<>();
        policyList.add(policyOwned1);
        policyList.add(policyOwned2);

        Mockito.when(policyOwnedRepo.findAll()).thenReturn(policyList);

        List<PolicyOwned> result = serviceClass.getAllAppliedPolicy();

        // Verify that findAll() was called exactly once
        Mockito.verify(policyOwnedRepo, Mockito.times(2)).findAll();


        assertEquals(policyList, result);
    }

@Test
public void testGetAllPolicyByPolicyIdEmptyList() {

Integer policyId = 1;

Mockito.when(policyOwnedRepo.getAllPolicyByPolicyId(policyId)).thenReturn(Optional.empty());

ArrayList<PolicyOwned> result = serviceClass.getAllPolicyByPolicyId(policyId);

Mockito.verify(policyOwnedRepo).getAllPolicyByPolicyId(policyId);

assertEquals(new ArrayList<>(), result);
}
//@Test
//public void testUpdateAppliedPolicy() {
//// Given
//UpdatePolicyRequest updatePolicyRequest = new UpdatePolicyRequest();
//updatePolicyRequest.setClientUsername("testUser");
//updatePolicyRequest.setPolicyOwnedId(1);
//updatePolicyRequest.setStatus(Status.ACTIVE); // Set the desired status
//
//// Mock the behavior of policyOwnedRepo.updatePolicyStatus
//Mockito.doNothing().when(policyOwnedRepo).updatePolicyStatus(any(), any(), any());
//
//// When
//String result = serviceClass.updateAppliedPolicy(updatePolicyRequest);
//
//// Then
//// Verify that policyOwnedRepo.updatePolicyStatus was called with the correct arguments
//Mockito.verify(policyOwnedRepo).updatePolicyStatus(
//        updatePolicyRequest.getClientUsername(),
//        updatePolicyRequest.getPolicyOwnedId(),
//        updatePolicyRequest.getStatus()
//);
//
//// Assert the result
//assertEquals("Policy Updated", result);
//}

@Test
public void testGetPolicyById() {

String policyId = "1";

PolicyOwned policyOwned = new PolicyOwned();

Mockito.when(policyOwnedRepo.findById(any(Integer.class))).thenReturn(Optional.of(policyOwned));


PolicyOwned result = serviceClass.getPolicyById(policyId);


Mockito.verify(policyOwnedRepo).findById(Integer.valueOf(policyId));

assertEquals(policyOwned, result);
}
@Test
public void testApplyPolicyList() {

List<PolicyOwned> policyList = Arrays.asList(policyOwned1,policyOwned2);

Mockito.when(policyOwnedRepo.saveAll(any())).thenReturn(policyList);


String result = serviceClass.applyPolicyList(policyList);


Mockito.verify(policyOwnedRepo).saveAll(policyList);

assertEquals("Policies Applied", result);
}


}