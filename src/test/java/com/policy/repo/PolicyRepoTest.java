package com.policy.repo;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyList;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.policy.repo.PolicyRepo;
import com.entity.Policy;
import com.entity.PolicyType;
import com.example.service.PolicyServiceImpl;

@ExtendWith(MockitoExtension.class)
public class PolicyRepoTest {

    @InjectMocks
    private PolicyServiceImpl policyRepo;

    @Mock
    private PolicyRepo policyRepoMock;

    @Test
    public void testFindByPolicyIdIn() {
        List<Integer> policyIdList = Arrays.asList(1, 2);
        List<Policy> expectedPolicyList = Arrays.asList(
                new Policy(1, "Health Policy", "Health insurance", 1, PolicyType.HEALTH, 100.0),
                new Policy(2, "Life Policy", "Life insurance", 2, PolicyType.LIFE, 200.0)
        );

        when(policyRepoMock.findByPolicyIdIn(anyList())).thenReturn(expectedPolicyList);

        List<Policy> actualPolicyList = policyRepo.getPolicyByIds(policyIdList);

        assertEquals(expectedPolicyList, actualPolicyList);
    }
}

