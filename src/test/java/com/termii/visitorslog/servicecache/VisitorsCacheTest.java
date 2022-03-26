package com.termii.visitorslog.servicecache;

import com.termii.visitorslog.domain.Visitors;
import com.termii.visitorslog.repository.VisitorsRepository;
import com.termii.visitorslog.service.VisitorsService;
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
public class VisitorsCacheTest {

    @Autowired
    private VisitorsService visitorsService;

    @MockBean
    private VisitorsRepository visitorsRepository;

    @Test
    void getVisitorById_forMultipleRequests_isRetrievedFromCache()
    {
        //given
        Long id = 123l;
        given(visitorsRepository.findById(id)).willReturn(Optional.of(Visitors.builder()
                .id(id)
                .visitorName("visitor")
                .build()));

        //when
        visitorsService.findVisitorById(id);
        visitorsService.findVisitorById(id);
        visitorsService.findVisitorById(id);

        //then
        then(visitorsRepository).should(times(1)).findById(id);
    }
}
