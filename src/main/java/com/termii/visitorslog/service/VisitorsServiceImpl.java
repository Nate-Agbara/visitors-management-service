package com.termii.visitorslog.service;

import com.termii.visitorslog.domain.Visitors;
import com.termii.visitorslog.dto.VisitorsDto;
import com.termii.visitorslog.repository.VisitorsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author: Nathan
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class VisitorsServiceImpl implements VisitorsService{

    private final VisitorsRepository visitorsRepository;

    /**
     * add a new visitor
     *
     * @param visitorsDto
     */
    @Override
    public void addVisitor(VisitorsDto visitorsDto) {
        log.info("Inside create new visitor");
        visitorsRepository.save(Visitors.builder()
                .visitorName(visitorsDto.getVisitorName())
                .phoneNumber(visitorsDto.getPhoneNumber())
                .emailAddress(visitorsDto.getEmailAddress())
                .address(visitorsDto.getAddress())
                .build());
    }

    /**
     * find visitor given visitorId
     * @param id
     * @return Visitor or empty if no visitor with the Id
     */
    @Override
    @Cacheable("visitor")
    public Optional<Visitors> findVisitorById(long id) {
        log.info("Inside findVisitorById");
        Optional<Visitors> result = visitorsRepository.findById(id);
        return result.isPresent() ?
                result:
                Optional.empty();
    }

    /**
     * find all visitors
     *
     * @return List of visitors
     */
    @Override
    public List<Visitors> findAllVisitor() {
        log.info("Inside getAll visitors");
        return visitorsRepository.findAll();
    }
}
