package com.example.service;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
public class PolicyTest {

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
//        when(policyRepo.findAll()).thenReturn(mockPolicyList);
    }

    @Test
    public void testRegisterPolicy() {
        // Mock the behavior of policyRepo.save
        Mockito.when(policyRepo.save(any(Policy.class))).thenReturn(policy1);

        // When
        String result = serviceClass.registerPolicy(policy1);

        // Then
        // Verify that policyRepo.save was called with the correct argument
        Mockito.verify(policyRepo).save(policy1);

        // Assert the result
        assertEquals("Policy Added!!", result);
    }


    @Test
    public void testGetPolicyById() {
        // Mock the behavior of policyRepo.findById
        when(policyRepo.findById(anyInt())).thenReturn(Optional.of(policy1));

        // When
        Optional<Policy> result = serviceClass.getPolicyById(1);

        // Assert the result
        assertEquals(Optional.of(policy1), result);
    }
    @Test
    public void testGetPolicyByIds() {
        // Mock the behavior of policyRepo.findByPolicyIdIn
        when(policyRepo.findByPolicyIdIn(anyList())).thenReturn(Arrays.asList(policy1, policy2));

        // When
        List<Policy> result = serviceClass.getPolicyByIds(Arrays.asList(1, 2));

        // Assert the result
        assertEquals(Arrays.asList(policy1, policy2), result);
    }
}