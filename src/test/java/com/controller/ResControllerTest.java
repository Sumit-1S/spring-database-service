package com.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.controller.ResController;
import com.entity.Policy;
import com.example.service.PolicyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.entity.PolicyType;

@ExtendWith(MockitoExtension.class)
public class ResControllerTest {

    @InjectMocks
    private ResController resController;

    @Mock
    private PolicyService policyService;

    private MockMvc mockMvc;

    @Test
    public void testGetAllPolicy() throws Exception {
        List<Policy> expectedPolicyList = Arrays.asList(
                new Policy(1, "Health Policy", "Health insurance", 1, PolicyType.HEALTH, 100.0),
                new Policy(2, "Life Policy", "Life insurance", 2, PolicyType.LIFE, 200.0)
        );

        when(policyService.getAllPolicy()).thenReturn(expectedPolicyList);

        mockMvc = MockMvcBuilders.standaloneSetup(resController).build();

        mockMvc.perform(get("/policyApp/getAllPolicy"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"policyId\":1,\"policyName\":\"Health Policy\",\"policyDesc\":\"Health insurance\",\"duration\":1,\"policyType\":\"HEALTH\",\"amount\":100.0},{\"policyId\":2,\"policyName\":\"Life Policy\",\"policyDesc\":\"Life insurance\",\"duration\":2,\"policyType\":\"LIFE\",\"amount\":200.0}]"));
    }

    @Test
    public void testGetAllPolicyByPolicyId() throws Exception {
        List<Integer> policyIdList = Arrays.asList(1, 2);

        List<Policy> expectedPolicyList = Arrays.asList(
                new Policy(1, "Health Policy", "Health insurance", 1, PolicyType.HEALTH, 100.0),
                new Policy(2, "Life Policy", "Life insurance", 2, PolicyType.LIFE, 200.0)
        );

        when(policyService.getPolicyByIds(policyIdList)).thenReturn(expectedPolicyList);

        mockMvc = MockMvcBuilders.standaloneSetup(resController).build();

        mockMvc.perform(get("/policyApp/getAllPolicyByList/{policyIdArr}", "1,2"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"policyId\":1,\"policyName\":\"Health Policy\",\"policyDesc\":\"Health insurance\",\"duration\":1,\"policyType\":\"HEALTH\",\"amount\":100.0},{\"policyId\":2,\"policyName\":\"Life Policy\",\"policyDesc\":\"Life insurance\",\"duration\":2,\"policyType\":\"LIFE\",\"amount\":200.0}]"));
    }

//    @Test
//    public void testAddPolicy() throws Exception {
//        Policy policy = new Policy(3, "Accidental Policy", "Accidental insurance", 3, PolicyType.ACCIDENTAL, 150.0);
//
//        // Use doReturn() for methods that return a value
//        doReturn("Policy Added!!").when(policyService).registerPolicy(any(Policy.class));
//        mockMvc = MockMvcBuilders.standaloneSetup(resController).build();
//        // Perform the request and assert the response
//        mockMvc.perform(post("/addPolicy")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(policy)))
//                .andExpect(status().isOk())
//                .andExpect(content().string("Policy Added!!"));
//    }
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
