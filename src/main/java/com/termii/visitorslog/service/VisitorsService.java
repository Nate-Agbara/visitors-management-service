package com.termii.visitorslog.service;

import com.termii.visitorslog.domain.Visitors;
import com.termii.visitorslog.dto.VisitorsDto;

import java.util.List;
import java.util.Optional;

/**
 * @author: Nathan
 */
public interface VisitorsService {

    void addVisitor(VisitorsDto visitorsDto);

    Optional<Visitors> findVisitorById(long id);

    List<Visitors> findAllVisitor();
}
