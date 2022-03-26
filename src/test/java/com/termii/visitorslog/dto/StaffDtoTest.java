package com.termii.visitorslog.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * @author: Nathan
 */
public class StaffDtoTest {
    //Given //when
    StaffDto staffDto = StaffDto.builder().staffName("Nathan").build();

    @Test
    public void staffDtoTest_happyPath(){
        //then
        assertEquals("Nathan", staffDto.getStaffName());
    }

    @Test
    public void staffDtoTest_errorPath(){
        //then
        assertNotEquals("Nathan", staffDto.getStaffName());
    }
}
