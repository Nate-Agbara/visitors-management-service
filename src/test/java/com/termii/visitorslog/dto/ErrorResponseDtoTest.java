package com.termii.visitorslog.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * @author: Nathan
 */
public class ErrorResponseDtoTest {
    //Given //when
    ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder().status(400).build();

    @Test
    public void errorResponseTest_happyPath(){
        //then
        assertEquals(400, errorResponseDTO.getStatus());
    }

    @Test
    public void errorResponseTest_errorPath(){
        //then
        assertNotEquals(400, errorResponseDTO.getStatus());
    }
}
