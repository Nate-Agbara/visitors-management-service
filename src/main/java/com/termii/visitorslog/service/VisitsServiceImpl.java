package com.termii.visitorslog.service;

import com.termii.visitorslog.constants.AppConstants;
import com.termii.visitorslog.domain.Staff;
import com.termii.visitorslog.domain.Visitors;
import com.termii.visitorslog.domain.Visits;
import com.termii.visitorslog.dto.VisitsDto;
import com.termii.visitorslog.exception.ApiRequestException;
import com.termii.visitorslog.repository.StaffRepository;
import com.termii.visitorslog.repository.VisitorsRepository;
import com.termii.visitorslog.repository.VisitsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

/**
 * @author: Nathan
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class VisitsServiceImpl implements VisitsService{

    private final VisitsRepository visitsRepository;

    private final StaffRepository staffRepository;

    private final VisitorsRepository visitorsRepository;

    /**
     * logs a new visit
     *
     * @param visitsDto
     */
    @Override
    public Visits logNewVisit(VisitsDto visitsDto) {

      return visitsRepository.save(Visits.builder()
                .visitorId(verifyVisitor(visitsDto.getVisitorsId()))
                .staffId(verifyStaff(visitsDto.getStaffIdId()))
                .dateOfVisit(visitsDto.getDateOfVisit())
                .reasonForVisit(visitsDto.getReasonForVisit())
                .build());

    }

    /**
     * Verify and return the staff given a staffId.
     *
     * @param staffId
     * @return the found staff
     * @throws ApiRequestException if no staff found.
     */
    private Staff verifyStaff(long staffId) {
        return staffRepository.findById(staffId).orElseThrow(() ->
                new ApiRequestException(AppConstants.NO_SUCH_STAFF + staffId)
        );
    }

    /**
     * Verify and return the visitor given a visitorId.
     *
     * @param visitorId
     * @return the found visitor
     * @throws ApiRequestException if no visitor found.
     */
    private Visitors verifyVisitor(long visitorId) {
        return visitorsRepository.findById(visitorId).orElseThrow(() ->
                new ApiRequestException(AppConstants.NO_SUCH_VISITOR + visitorId)
        );
    }
}
