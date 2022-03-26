package com.termii.visitorslog.service;

import com.termii.visitorslog.domain.Visitors;
import com.termii.visitorslog.exception.ApiRequestException;
import com.termii.visitorslog.repository.VisitorsRepository;
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
public class VisitorsServiceImplTest {

    @Autowired
    private VisitorsService visitorsService;

    @Autowired
    private VisitorsRepository visitorsRepository;

    @DisplayName("Returning saved visitor from service layer")
    @Test
    public void getVisitorById_forSavedVisitor_isReturned(){
        //given
        Visitors visitors = visitorsRepository.save(Visitors.builder()
                .visitorName("visitor")
                .phoneNumber("12345677")
                .emailAddress("visitor@visitor.com")
                .address("house")
                .build());

        //when
        Optional<Visitors> retrievedVisitor = visitorsService.findVisitorById(visitors.getId());

        //then
        assertEquals("visitor@visitor.com", retrievedVisitor.get().getEmailAddress());
        assertNotNull(retrievedVisitor.get().getId());
    }

    @Test
    void getVisitorById_whenMissingVisitor_notFoundExceptionThrown()
    {
        //given
        Long id = 1234l;
        //when
        Optional<Visitors> visitor = visitorsService.findVisitorById(id);

        //then
        assertEquals(Optional.empty(), visitor);
    }

    @Test
    void getAllVisitor(){
        //given
        Visitors visitors = visitorsRepository.save(Visitors.builder()
                .visitorName("visitor")
                .phoneNumber("12345677")
                .emailAddress("visitor1@visitor.com")
                .address("house")
                .build());

        Visitors visitors2 = visitorsRepository.save(Visitors.builder()
                .visitorName("visitor")
                .phoneNumber("12345677")
                .emailAddress("visitor2@visitor.com")
                .address("house")
                .build());
        //when
        List<Visitors> visitorsList = visitorsService.findAllVisitor();

        //then
        assertNotEquals(0, visitorsList.size());
    }
}
