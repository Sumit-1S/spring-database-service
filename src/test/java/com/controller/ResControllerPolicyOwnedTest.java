package com.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doNothing;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.doReturn;
import com.controller.ResControllerPolicyOwned;
import com.entity.PolicyOwned;
import com.entity.PolicyType;
import com.entity.Status;
import com.example.model.UpdatePolicyRequest;
import com.example.service.PolicyOwnedService;

@ExtendWith(MockitoExtension.class)
public class ResControllerPolicyOwnedTest {

    @InjectMocks
    private ResControllerPolicyOwned resControllerPolicyOwned;

    @Mock
    private PolicyOwnedService policyOwnedService;

    private MockMvc mockMvc;
    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(resControllerPolicyOwned).build();
    }

    @Test
    public void testApplyPolicy() throws Exception {
        PolicyOwned policyOwned = new PolicyOwned(1, "user1", 25, "City1", "Healthy", null, 150.0, Status.ACTIVE, false, null);

        when(policyOwnedService.applyPolicy(any(PolicyOwned.class))).thenReturn("Policy Applied");

        mockMvc = MockMvcBuilders.standaloneSetup(resControllerPolicyOwned).build();

        mockMvc.perform(post("/policyOwnedApp/applyPolicy")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"clientUsername\":\"user1\",\"clientAge\":25,\"clientCity\":\"City1\",\"clientMedicalHistory\":\"Healthy\",\"premium\":150.0,\"status\":\"ACTIVE\",\"clientTobaccoUser\":false}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Policy Applied"));
    }

    @Test
    public void testGetAllAppliedPolicy() throws Exception {
        List<PolicyOwned> expectedPolicyList = Arrays.asList(
                new PolicyOwned(1, "user1", 25, "City1", "Healthy", null, 150.0, Status.ACTIVE, false, null),
                new PolicyOwned(2, "user2", 30, "City2", "Medical History", null, 200.0, Status.ACTIVE, true, null)
        );

        when(policyOwnedService.getAllAppliedPolicy()).thenReturn(expectedPolicyList);

        mockMvc = MockMvcBuilders.standaloneSetup(resControllerPolicyOwned).build();

        mockMvc.perform(get("/policyOwnedApp/getAllAppliedPolicy"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"policyOwnedId\":1,\"clientUsername\":\"user1\",\"clientAge\":25,\"clientCity\":\"City1\",\"clientMedicalHistory\":\"Healthy\",\"date\":null,\"premium\":150.0,\"status\":\"ACTIVE\",\"clientTobaccoUser\":false,\"policy\":null},{\"policyOwnedId\":2,\"clientUsername\":\"user2\",\"clientAge\":30,\"clientCity\":\"City2\",\"clientMedicalHistory\":\"Medical History\",\"date\":null,\"premium\":200.0,\"status\":\"ACTIVE\",\"clientTobaccoUser\":true,\"policy\":null}]"));
    }

    @Test
    public void testGetAllPolicyByClientUsername() throws Exception {
        String clientUsername = "user1";

        List<PolicyOwned> expectedPolicyList = Arrays.asList(
                new PolicyOwned(1, "user1", 25, "City1", "Healthy", null, 150.0, Status.ACTIVE, false, null),
                new PolicyOwned(3, "user1", 28, "City3", "Medical Condition", null, 180.0, Status.INACTIVE, true, null)
        );

        when(policyOwnedService.getAllPolicyByClientUsername(clientUsername)).thenReturn(expectedPolicyList);

        mockMvc = MockMvcBuilders.standaloneSetup(resControllerPolicyOwned).build();

        mockMvc.perform(get("/policyOwnedApp/getAllPolicyByClientUsername/{clientUsername}", clientUsername))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"policyOwnedId\":1,\"clientUsername\":\"user1\",\"clientAge\":25,\"clientCity\":\"City1\",\"clientMedicalHistory\":\"Healthy\",\"date\":null,\"premium\":150.0,\"status\":\"ACTIVE\",\"clientTobaccoUser\":false,\"policy\":null},{\"policyOwnedId\":3,\"clientUsername\":\"user1\",\"clientAge\":28,\"clientCity\":\"City3\",\"clientMedicalHistory\":\"Medical Condition\",\"date\":null,\"premium\":180.0,\"status\":\"INACTIVE\",\"clientTobaccoUser\":true,\"policy\":null}]"));
    }
    @Test
    public void testGetAllPolicyByPolicyId() throws Exception {
        Integer policyId = 1;
        List<PolicyOwned> expectedPolicyList = Arrays.asList(
                new PolicyOwned(1, "user1", 25, "City1", "Healthy", null, 150.0, Status.ACTIVE, false, null),
                new PolicyOwned(2, "user2", 30, "City2", "Medical History", null, 200.0, Status.ACTIVE, true, null)
        );

        // Update the stubbing to return an ArrayList
        when(policyOwnedService.getAllPolicyByPolicyId(any(Integer.class))).thenReturn(new ArrayList<>(expectedPolicyList));

        mockMvc.perform(post("/policyOwnedApp/getAllPolicyByPolicyId")
                .contentType(MediaType.APPLICATION_JSON)
                .param("policyId", policyId.toString()))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(expectedPolicyList)));
    }

    @Test
    public void testUpdateAppliedPolicy() throws Exception {
        // Create a sample UpdatePolicyRequest for stubbing (you can customize this based on your needs)
        UpdatePolicyRequest sampleUpdateRequest = new UpdatePolicyRequest(1, "user1", Status.INACTIVE);

        // Stub the updateAppliedPolicy method to return a success message
        when(policyOwnedService.updateAppliedPolicy(any(UpdatePolicyRequest.class))).thenReturn("Policy Updated");

        // Perform the request and assert the response
        mockMvc.perform(put("/policyOwnedApp/updateAppliedPolicy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(sampleUpdateRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string("Policy Updated"));
    }

    @Test
    public void testGetUpdateAppliedPolicy() throws Exception {
        String policyId = "1";
        PolicyOwned expectedPolicy = new PolicyOwned(1, "user1", 25, "City1", "Healthy", null, 150.0, Status.ACTIVE, false, null);

        when(policyOwnedService.getPolicyById(policyId)).thenReturn(expectedPolicy);

        mockMvc.perform(get("/policyOwnedApp/updateAppliedPolicy/{policyId}", policyId))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(expectedPolicy)));
    }
    @Test
    public void testApplyPolicyList() throws Exception {
        List<PolicyOwned> policyList = Arrays.asList(
                new PolicyOwned(1, "user1", 25, "City1", "Healthy", null, 150.0, Status.ACTIVE, false, null),
                new PolicyOwned(2, "user2", 30, "City2", "Medical History", null, 200.0, Status.ACTIVE, true, null)
        );

        when(policyOwnedService.applyPolicyList(anyList())).thenReturn("Policies Applied");

        mockMvc.perform(post("/policyOwnedApp/applyPolicyList")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(policyList))) // Use the asJsonString method here
                .andExpect(status().isOk())
                .andExpect(content().string("Policy Applied"));
    }



    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Add similar test methods for other controller endpoints
}
