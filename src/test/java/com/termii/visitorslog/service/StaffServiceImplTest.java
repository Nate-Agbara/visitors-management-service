package com.termii.visitorslog.service;

import com.termii.visitorslog.domain.Staff;
import com.termii.visitorslog.exception.ApiRequestException;
import com.termii.visitorslog.repository.StaffRepository;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author: Nathan
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
public class StaffServiceImplTest {

    @Autowired
    private StaffService staffService;

    @Autowired
    private StaffRepository staffRepository;

    @DisplayName("Returning saved staff from service layer")
    @Test
    public void getStaffById_forSavedStaff_isReturned(){
        //given
        Staff staff = staffRepository.save(Staff.builder()
                .staffName("Nathan")
                .phoneNumber("12345677")
                .emailAddress("nathan@termii.com")
                .address("house")
                .build());

        //when
        Optional<Staff> retrievedStaff = staffService.findStaffById(staff.getId());

        //then
        assertEquals("nathan@termii.com", retrievedStaff.get().getEmailAddress());
        assertNotNull(retrievedStaff.get().getId());
    }

    @Test
    void getStaffById_whenMissingStaff_notFoundExceptionThrown()
    {
        //given
        Long id = 0l;
        //when
        Optional<Staff> staff = staffService.findStaffById(id);

        //then
        assertEquals(Optional.empty(), staff);
    }

    @Test
    void getAllStaff(){
        //given
        Staff staff1 = staffRepository.save(Staff.builder()
                .staffName("Nathan")
                .phoneNumber("12345677")
                .emailAddress("nathan@termii.com")
                .address("house")
                .build());

        Staff staff2 = staffRepository.save(Staff.builder()
                .staffName("Nathan")
                .phoneNumber("12345677")
                .emailAddress("nathan2@termii.com")
                .address("house")
                .build());
        //when
        List<Staff> staffList = staffService.findAllStaff();

        //then
        assertNotEquals(0, staffList.size());
    }
}
