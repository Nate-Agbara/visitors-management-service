package com.termii.visitorslog.servicecache;

import com.termii.visitorslog.domain.Staff;
import com.termii.visitorslog.repository.StaffRepository;
import com.termii.visitorslog.service.StaffService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

/**
 * @author: Nathan
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class StaffCacheTest {

    @Autowired
    private StaffService staffService;

    @MockBean
    private StaffRepository staffRepository;

    @Test
    void getStaffById_forMultipleRequests_isRetrievedFromCache()
    {
        //given
        Long id = 123l;
        given(staffRepository.findById(id)).willReturn(Optional.of(Staff.builder()
                .id(id)
                .staffName("Nathan")
                .build()));

        //when
        staffService.findStaffById(id);
        staffService.findStaffById(id);
        staffService.findStaffById(id);

        //then
        then(staffRepository).should(times(1)).findById(id);
    }
}
