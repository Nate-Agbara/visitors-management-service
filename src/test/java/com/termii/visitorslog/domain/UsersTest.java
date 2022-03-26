package com.termii.visitorslog.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * @author: Nathan
 */
public class UsersTest {

    //Given //when
    Users users1 = Users.builder().username("user").password("pass").build();

    @Test
    public void userDomainTest_happyPath(){
        //then
        assertEquals("user", users1.getUsername());
    }

    @Test
    public void userDomainTest_wrongPath(){
        //then
        assertNotEquals("userName", users1.getUsername());
    }
}
