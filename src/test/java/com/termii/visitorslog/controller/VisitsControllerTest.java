package com.termii.visitorslog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.termii.visitorslog.constants.AppConstants;
import com.termii.visitorslog.domain.Staff;
import com.termii.visitorslog.domain.Visits;
import com.termii.visitorslog.service.StaffService;
import com.termii.visitorslog.service.VisitsService;
import com.termii.visitorslog.service.VisitsServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author: Nathan
 */
@SpringBootTest
@AutoConfigureMockMvc
public class VisitsControllerTest {

    @MockBean
    private VisitsService visitsService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreateVisit() throws Exception {
        //given
        Visits visits = Visits.builder()
                .id(1l)
                .reasonForVisit("meeting")
                .build();
        //when //then
        mockMvc.perform(MockMvcRequestBuilders
                        .post(AppConstants.VISITS_URL)
                        .content(asJsonString(visits))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
