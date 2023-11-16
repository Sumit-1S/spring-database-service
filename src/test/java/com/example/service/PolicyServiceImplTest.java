package com.example.service;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

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
import com.entity.PolicyType;
import com.example.service.PolicyService;
import com.example.service.PolicyServiceImpl;
import com.policy.repo.PolicyRepo;

@SpringBootTest(classes = Policy.class)
@ExtendWith(MockitoExtension.class)
public class PolicyServiceImplTest{

    @InjectMocks
    private PolicyService serviceClass = new PolicyServiceImpl();

    @Mock
    private PolicyRepo policyRepo;

    private Policy policy1;
    private Policy policy2;
    private List<Policy> mockPolicyList;
    
    @BeforeEach
    public void beforeTest() {
        MockitoAnnotations.openMocks(this);

        policy1 = new Policy();
        policy1.setPolicyId(1);
        policy1.setPolicyName("P01");
        policy1.setPolicyDesc("Isko le liya to life jinga la la");
        policy1.setDuration(3);
        policy1.setPolicyType(PolicyType.ACCIDENTAL);
        policy1.setAmount(20000.00);

        policy2 = new Policy();
        policy2.setPolicyId(2);
        policy2.setPolicyName("P02");
        policy2.setPolicyDesc("Isko le liya to life jinga la la la");
        policy2.setDuration(4);
        policy2.setPolicyType(PolicyType.HEALTH);
        policy2.setAmount(200000.00);

        // Mocking policyRepo.findAll() behavior
        mockPolicyList = Arrays.asList(policy1, policy2);
        when(policyRepo.findAll()).thenReturn(mockPolicyList);
    }
    @Test
    public void testGetAllPolicy() {
        // When
        List<Policy> result = serviceClass.getAllPolicy();

        // Verify that policyRepo.findAll() was called
        Mockito.verify(policyRepo).findAll();

        // Assert the result
        assertEquals(mockPolicyList, result);
    }
}