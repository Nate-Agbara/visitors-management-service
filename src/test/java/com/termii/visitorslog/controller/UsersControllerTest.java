package com.termii.visitorslog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.termii.visitorslog.constants.AppConstants;
import com.termii.visitorslog.domain.Staff;
import com.termii.visitorslog.domain.Users;
import com.termii.visitorslog.dto.UsersDto;
import com.termii.visitorslog.exception.ApiRequestException;
import com.termii.visitorslog.service.StaffService;
import com.termii.visitorslog.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author: Nathan
 */
@SpringBootTest
@AutoConfigureMockMvc
public class UsersControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void login_ForMissingUser_status400() throws Exception
    {
        //given
        Users users = Users.builder()
                .id(1l)
                .username("user")
                .password("pass")
                .build();

        //when //then
        mockMvc.perform(MockMvcRequestBuilders
                        .post(AppConstants.LOGIN_URL)
                        .content(asJsonString(users))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

    }

    @Test
    void testLogin_isSuccessful() throws Exception {
//        //given
//        UsersDto usersDto = UsersDto.builder()
//                .username("Nate")
//                .password("natepass")
//                .build();
//
//        //when //then
//        mockMvc.perform(MockMvcRequestBuilders
//                        .post(AppConstants.LOGIN_URL)
//                        .content(asJsonString(usersDto))
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().is2xxSuccessful());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
