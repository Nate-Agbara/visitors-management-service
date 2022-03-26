package com.termii.visitorslog.service;

import com.termii.visitorslog.domain.Visits;
import com.termii.visitorslog.dto.VisitsDto;
import com.termii.visitorslog.exception.ApiRequestException;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author: Nathan
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
public class VisitsServiceImplTest {

    @Autowired
    private VisitsService visitsService;

    @DisplayName("Returning saved visits from service layer")
    @Test
    void verifyVisitorTest(){
        //given
        VisitsDto visitsDto = VisitsDto.builder()
                .staffIdId(1)
                .visitorsId(1)
                .dateOfVisit(LocalDateTime.parse("2022-03-25T15:53:16"))
                .reasonForVisit("meeting").build();
        //when
        Visits visits = visitsService.logNewVisit(visitsDto);

        //then
        assertNotNull(visits );

    }

    @Test
    void logVisits_whenMissingVisitorAndStaff_notFoundExceptionThrown()
    {
        //given
        VisitsDto visitsDto = VisitsDto.builder()
                .staffIdId(123l)
                .visitorsId(123l)
                .dateOfVisit(LocalDateTime.parse("2022-03-26T15:53:16"))
                .reasonForVisit("meeting").build();
        //when
        Throwable throwable = catchThrowable(() -> visitsService.logNewVisit(visitsDto));

        //then
        BDDAssertions.then(throwable).isInstanceOf(ApiRequestException.class);
    }

}
