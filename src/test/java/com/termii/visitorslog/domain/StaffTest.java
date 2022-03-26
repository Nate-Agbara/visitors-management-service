package com.termii.visitorslog.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * @author: Nathan
 */
public class StaffTest {

    //Given //when
    Staff staff1 = Staff.builder().staffName("Nathan").build();

    @Test
    public void staffTest_happyPath(){
        //then
        assertEquals("Nathan", staff1.getStaffName());
    }

    @Test
    public void staffTest_errorPath(){
        //then
        assertNotEquals("John", staff1.getStaffName());
    }
}
