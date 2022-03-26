package com.termii.visitorslog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.termii.visitorslog.constants.AppConstants;
import com.termii.visitorslog.domain.Staff;
import com.termii.visitorslog.exception.ApiRequestException;
import com.termii.visitorslog.service.StaffService;
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
public class StaffControllerTest {

    @MockBean
    private StaffService staffService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreateStaff() throws Exception {
        //given
        Staff staff = Staff.builder()
                .id(1l)
                .staffName("Nathan")
                .emailAddress("nathan@termii.com")
                .address("house")
                .build();
        //when //then
        mockMvc.perform(MockMvcRequestBuilders
                        .post(AppConstants.STAFF_URL)
                        .content(asJsonString(staff))
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
    void getStaff_ForSavedStaff_Returned() throws Exception
    {
        //given
        given(staffService.findStaffById(anyLong())).willReturn(
                Optional.of(Staff.builder()
                        .id(1l)
                        .staffName("Nathan")
                        .emailAddress("nathan@termii.com")
                        .address("house")
                        .build())
        );

        //when then
        mockMvc.perform(get(AppConstants.STAFF_URL +"/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1l))
                .andExpect(jsonPath("staffName").value("Nathan"))
                .andExpect(jsonPath("address").value("house"));
    }

    @Test
    void getAllStaff_ForSavedStaff_Returned() throws Exception
    {
        //given
        given(staffService.findStaffById(anyLong())).willReturn(
                Optional.of(Staff.builder()
                        .id(1l)
                        .staffName("Nathan")
                        .emailAddress("nathan@termii.com")
                        .address("house")
                        .build())
        );

        //when then
        mockMvc.perform(get(AppConstants.STAFF_URL))
                .andExpect(status().isOk());
    }

    @Test
    void getStaff_ForMissingStaff_status400() throws Exception
    {
        //given
        given(staffService.findStaffById(anyLong())).willThrow(ApiRequestException.class);

        //when then
        mockMvc.perform(get(AppConstants.STAFF_URL+"/1"))
                .andExpect(status().is4xxClientError());

    }
}
