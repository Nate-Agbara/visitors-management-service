package com.termii.visitorslog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.termii.visitorslog.constants.AppConstants;
import com.termii.visitorslog.domain.Visitors;
import com.termii.visitorslog.exception.ApiRequestException;
import com.termii.visitorslog.service.VisitorsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author: Nathan
 */
@SpringBootTest
@AutoConfigureMockMvc
public class VisitorsControllerTest {

    @MockBean
    private VisitorsService visitorsService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreateVisitor() throws Exception {
        //given
        Visitors visitors = Visitors.builder()
                .id(1l)
                .visitorName("visitor")
                .emailAddress("visitor@visitors.com")
                .address("house")
                .build();
        //when //then
        mockMvc.perform(MockMvcRequestBuilders
                        .post(AppConstants.VISITORS_URL)
                        .content(asJsonString(visitors))
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

    @Test
    void getVisitor_ForSavedVisitor_Returned() throws Exception
    {
        //given
        given(visitorsService.findVisitorById(anyLong())).willReturn(
                Optional.of(Visitors.builder()
                        .id(1l)
                        .visitorName("visitor")
                        .emailAddress("visitor@visitors.com")
                        .address("house")
                        .build())
        );

        //when then
        mockMvc.perform(get(AppConstants.VISITORS_URL +"/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1l))
                .andExpect(jsonPath("visitorName").value("visitor"))
                .andExpect(jsonPath("address").value("house"));
    }

    @Test
    void getAllVisitors_ForSavedVisitors_Returned() throws Exception
    {
        //given
        given(visitorsService.findVisitorById(anyLong())).willReturn(
                Optional.of(Visitors.builder()
                        .id(1l)
                        .visitorName("visitor")
                        .emailAddress("visitor@visitors.com")
                        .address("house")
                        .build())
        );

        //when then
        mockMvc.perform(get(AppConstants.VISITORS_URL))
                .andExpect(status().isOk());
    }

    @Test
    void getVisitors_ForMissingVisitors_status400() throws Exception
    {
        //given
        given(visitorsService.findVisitorById(anyLong())).willThrow(ApiRequestException.class);

        //when then
        mockMvc.perform(get(AppConstants.VISITORS_URL+"/1"))
                .andExpect(status().is4xxClientError());

    }
}
