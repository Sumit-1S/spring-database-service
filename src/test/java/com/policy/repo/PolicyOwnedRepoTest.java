package com.policy.repo;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.entity.PolicyOwned;
import com.entity.Status;
import com.example.model.UpdatePolicyRequest;
import com.example.service.PolicyOwnedServiceImpl;

@ExtendWith(MockitoExtension.class)
public class PolicyOwnedRepoTest {

    @InjectMocks
    private PolicyOwnedServiceImpl policyOwnedService;

    @Mock
    private PolicyOwnedRepo policyOwnedRepoMock;

    @Test
    public void testUpdateAppliedPolicy() {
        Status status = Status.INACTIVE;
        String clientUsername = "user1";
        Integer policyOwnedId = 1;

        // Mocking the method
        doNothing().when(policyOwnedRepoMock).updatePolicyStatus(anyString(), any(), any());

        // Calling the actual method
        policyOwnedService.updateAppliedPolicy(new UpdatePolicyRequest( policyOwnedId,clientUsername, status));

        // Add assertions if needed
    }

    @Test
    public void testGetAllPolicyByClientUsername() {
        String clientUsername = "user1";
        List<PolicyOwned> expectedPolicyList = Arrays.asList(
                new PolicyOwned(1, "user1", 25, "City1", "Healthy", null, 150.0, Status.ACTIVE, false, null),
                new PolicyOwned(3, "user1", 28, "City3", "Medical Condition", null, 180.0, Status.INACTIVE, true, null)
        );

        when(policyOwnedRepoMock.getAllPolicyByClientUsername(anyString())).thenReturn(expectedPolicyList);

        List<PolicyOwned> actualPolicyList = policyOwnedService.getAllPolicyByClientUsername(clientUsername);

        assertEquals(expectedPolicyList, actualPolicyList);
    }

    @Test
    public void testGetAllPolicyByPolicyId() {
        Integer policyId = 1;
        List<PolicyOwned> expectedPolicyList = Arrays.asList(
                new PolicyOwned(1, "user1", 25, "City1", "Healthy", null, 150.0, Status.ACTIVE, false, null),
                new PolicyOwned(2, "user2", 30, "City2", "Medical History", null, 200.0, Status.ACTIVE, true, null)
        );

        when(policyOwnedRepoMock.getAllPolicyByPolicyId(policyId)).thenReturn(Optional.of(expectedPolicyList));

        List<PolicyOwned> actualPolicyList = policyOwnedService.getAllPolicyByPolicyId(policyId);

        assertTrue(!actualPolicyList.isEmpty());
        assertEquals(expectedPolicyList, actualPolicyList);
    }
    @Test
    public void testApplyPolicy() {
        PolicyOwned policyOwned = new PolicyOwned(); // Create a valid PolicyOwned object

        // Mocking the method
        when(policyOwnedRepoMock.save(policyOwned)).thenReturn(policyOwned);

        // Calling the actual method
        String result = policyOwnedService.applyPolicy(policyOwned);

        assertEquals("Policy Owned Added!!", result);
    }

    // Add more test methods as needed for other functionalities

}
